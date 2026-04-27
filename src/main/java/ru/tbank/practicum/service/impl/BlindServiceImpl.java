package ru.tbank.practicum.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tbank.practicum.dto.BlindCreateDto;
import ru.tbank.practicum.dto.BlindResponseDto;
import ru.tbank.practicum.dto.enums.BlindUpdateTimeDto;
import ru.tbank.practicum.dto.enums.Status;
import ru.tbank.practicum.entity.Blinds;
import ru.tbank.practicum.entity.Users;
import ru.tbank.practicum.mapper.SettingMapper;
import ru.tbank.practicum.repository.BlindRepository;
import ru.tbank.practicum.service.BlindService;
import ru.tbank.practicum.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
@Slf4j
@AllArgsConstructor
public class BlindServiceImpl implements BlindService {

    private SettingMapper settingMapper;
    private UserService userService;
    private BlindRepository blindRepository;

    @Override
    public BlindResponseDto newBlind(String jwtToken, BlindCreateDto blindCreateDto) {
        Users user = userService.getUserByToken(jwtToken);
        Blinds blind = new Blinds();
        blind.setUser(user);
        blind.setRoom(blindCreateDto.getRoom());
        blind.setStatus(blindCreateDto.getStatus());
        blind.setTimeClose(blindCreateDto.getTimeClose());
        blind.setTimeOpen(blindCreateDto.getTimeOpen());
        return settingMapper.getDto(blindRepository.save(blind));
    }

    @Override
    @Transactional
    public List<BlindResponseDto> getBlinds(String jwtToken) {
        Users user = userService.getUserByToken(jwtToken);

        return blindRepository.findAllByUser_Id(user.getId())
                .map(x -> settingMapper.getDto(x))
                .toList();
    }

    @Override
    public BlindResponseDto getBlind(String jwtToken, Long id) {
        Users user = userService.getUserByToken(jwtToken);

        Optional<Blinds> optionalBlinds = blindRepository.findByUser_IdAndId(user.getId(), id);
        if (optionalBlinds.isEmpty()) {
            throw new IllegalArgumentException("Blind with this id not found");
        }

        return settingMapper.getDto(optionalBlinds.get());

    }

    @Override
    public BlindResponseDto updateTimeOpenBlind(String jwtToken, BlindUpdateTimeDto blindUpdateTimeDto) {
        Users user = userService.getUserByToken(jwtToken);

        Optional<Blinds> optionalBlinds = blindRepository.findByUser_IdAndId(user.getId(),
                blindUpdateTimeDto.getId());
        if (optionalBlinds.isEmpty()) {
            throw new IllegalArgumentException("Blind with this id not found");
        }
        Blinds blind = optionalBlinds.get();
        blind.setTimeOpen(blindUpdateTimeDto.getTime());
        return settingMapper.getDto(blindRepository.save(blind));
    }

    @Override
    public BlindResponseDto updateTimeCloseBlind(String jwtToken, BlindUpdateTimeDto blindUpdateTimeDto) {
        Users user = userService.getUserByToken(jwtToken);

        Optional<Blinds> optionalBlinds = blindRepository.findByUser_IdAndId(user.getId(),
                blindUpdateTimeDto.getId());
        if (optionalBlinds.isEmpty()) {
            throw new IllegalArgumentException("Blind with this id not found");
        }
        Blinds blind = optionalBlinds.get();
        blind.setTimeClose(blindUpdateTimeDto.getTime());
        return settingMapper.getDto(blindRepository.save(blind));
    }

    @Override
    public Status getStatusBlind(String jwtToken, Long id) {
        return getBlind(jwtToken, id).getStatus();
    }
}
