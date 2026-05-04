package ru.tbank.practicum.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tbank.practicum.dto.KeyRequestDto;
import ru.tbank.practicum.dto.KeyResponseDto;
import ru.tbank.practicum.dto.LoggingKeyDto;
import ru.tbank.practicum.dto.enums.Status;
import ru.tbank.practicum.entity.Keys;
import ru.tbank.practicum.entity.LoggingKeys;
import ru.tbank.practicum.entity.Users;
import ru.tbank.practicum.mapper.SettingMapper;
import ru.tbank.practicum.repository.KeyRepository;
import ru.tbank.practicum.repository.KeysLoggingRepository;
import ru.tbank.practicum.repository.UsersRepository;
import ru.tbank.practicum.security.jwt.JwtService;
import ru.tbank.practicum.service.KeyService;
import ru.tbank.practicum.service.UserService;

import javax.xml.crypto.KeySelectorResult;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class KeyServiceImpl implements KeyService {

    private SettingMapper settingMapper;
    private KeysLoggingRepository keysLoggingRepository;
    private KeyRepository keyRepository;
    private UserService userService;

    @Override
    public KeyResponseDto createNewKey(String token, KeyRequestDto keyRequestDto) {

        Keys key = new Keys();
        key.setUser(userService.getUserByToken(token));
        key.setRoom(keyRequestDto.getRoom());
        key.setStatus(keyRequestDto.getStatus());

        return settingMapper.getDto(keyRepository.save(key));
    }

    @Override
    @Transactional(readOnly = true)
    public List<KeyResponseDto> getMyKeys(String token) {

        Users users = userService.getUserByToken(token);

        return keyRepository.findAllByUser_Id(users.getId())
                .map(x -> settingMapper.getDto(x))
                .collect(Collectors.toList());
    }

    @Override
    public KeyResponseDto getKey(String token, Long id) {

        Users users = userService.getUserByToken(token);

        Optional<Keys> keys = keyRepository.findByUser_IdAndId(users.getId(), id);

        if (keys.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return settingMapper.getDto(keys.get());
    }

    @Override
    public Status getStatusKey(String token, Long id) {

        KeyResponseDto keyResponseDto = getKey(token, id);

        return keyResponseDto.getStatus();
    }

    @Override
    public Status lockOrUnlockKey(String token, Long id) {
        Users user = userService.getUserByToken(token);
        Keys keys = keyRepository.findByUser_IdAndId(user.getId(), id)
                .orElseThrow(() -> new IllegalArgumentException());
        Status status;
        if (keys.getStatus().equals(Status.CLOSED)) {
            status = Status.OPENED;
        } else {
            status = Status.CLOSED;
        }

        LoggingKeys loggingKeys = new LoggingKeys();
        loggingKeys.setKeys(keys);
        loggingKeys.setTime(LocalDateTime.now());
        loggingKeys.setNewStatus(status);
        keysLoggingRepository.save(loggingKeys);

        keyRepository.updateStatusKey(id, status);

        return status;
    }

    @Override
    @Transactional
    public List<LoggingKeyDto> getHistoryKey(String jwtToken, Long id) {
        Users user = userService.getUserByToken(jwtToken);
        return keysLoggingRepository.findAllByKeys_IdAndKeys_User_Id(id, user.getId())
                .map(settingMapper::getDto)
                .toList();
    }


}
