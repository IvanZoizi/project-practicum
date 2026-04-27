package ru.tbank.practicum.service;

import ru.tbank.practicum.dto.KeyRequestDto;
import ru.tbank.practicum.dto.KeyResponseDto;
import ru.tbank.practicum.dto.enums.Status;

import java.util.List;

public interface KeyService {
    public KeyResponseDto createNewKey(String token, KeyRequestDto keyRequestDto);
    public List<KeyResponseDto> getMyKeys(String token);
    public KeyResponseDto getKey(String token, Long id);
    public Status getStatusKey(String token, Long id);
}
