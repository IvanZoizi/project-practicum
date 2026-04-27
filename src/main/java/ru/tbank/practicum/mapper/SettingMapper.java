package ru.tbank.practicum.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.tbank.practicum.dto.BlindResponseDto;
import ru.tbank.practicum.dto.CoordsResponseDto;
import ru.tbank.practicum.dto.KeyResponseDto;
import ru.tbank.practicum.entity.Blinds;
import ru.tbank.practicum.entity.Keys;
import ru.tbank.practicum.entity.Users;
import ru.tbank.practicum.entity.UsersCoords;
import ru.tbank.practicum.repository.KeyRepository;
import ru.tbank.practicum.security.CustomUserDetail;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class SettingMapper {

    private final KeyRepository keyRepository;

    public CustomUserDetail getDto(Optional<Users> user, String login) {
        return user.map(CustomUserDetail::new)
                .orElseThrow(() -> new UsernameNotFoundException(login));
    }

    public KeyResponseDto getDto(Keys keys) {
        KeyResponseDto keyResponseDto = new KeyResponseDto();
        keyResponseDto.setId(keys.getId());
        keyResponseDto.setUserId(keys.getUser().getId());
        keyResponseDto.setRoom(keys.getRoom());
        keyResponseDto.setStatus(keys.getStatus());
        return keyResponseDto;
    }

    public CoordsResponseDto getDto(UsersCoords usersCoords) {
        CoordsResponseDto coordsResponseDto = new CoordsResponseDto();
        coordsResponseDto.setId(usersCoords.getId());
        coordsResponseDto.setLat(usersCoords.getLat());
        coordsResponseDto.setLon(usersCoords.getLon());
        return coordsResponseDto;
    }

    public BlindResponseDto getDto(Blinds blinds) {
        BlindResponseDto blindResponseDto = new BlindResponseDto();
        blindResponseDto.setId(blinds.getId());
        blindResponseDto.setRoom(blinds.getRoom());
        blindResponseDto.setStatus(blinds.getStatus());
        blindResponseDto.setTimeOpen(blinds.getTimeOpen());
        blindResponseDto.setTimeClose(blinds.getTimeClose());
        return blindResponseDto;
    }
}