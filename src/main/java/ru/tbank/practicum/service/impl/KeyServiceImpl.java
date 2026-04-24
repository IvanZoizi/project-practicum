package ru.tbank.practicum.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tbank.practicum.dto.KeyRequestDto;
import ru.tbank.practicum.dto.KeyResponseDto;
import ru.tbank.practicum.dto.enums.KeyStatus;
import ru.tbank.practicum.entity.Keys;
import ru.tbank.practicum.entity.Users;
import ru.tbank.practicum.mapper.SettingMapper;
import ru.tbank.practicum.repository.KeyRepository;
import ru.tbank.practicum.repository.UsersRepository;
import ru.tbank.practicum.security.jwt.JwtService;
import ru.tbank.practicum.service.KeyService;
import ru.tbank.practicum.service.UserService;

import javax.xml.crypto.KeySelectorResult;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class KeyServiceImpl implements KeyService {

    private JwtService jwtService;
    private SettingMapper settingMapper;
    private UsersRepository usersRepository;
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
    public KeyStatus getStatusKey(String token, Long id) {

        KeyResponseDto keyResponseDto = getKey(token, id);

        return keyResponseDto.getStatus();
    }
}
