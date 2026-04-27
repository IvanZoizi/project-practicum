package ru.tbank.practicum.service;

import ru.tbank.practicum.dto.BlindCreateDto;
import ru.tbank.practicum.dto.BlindResponseDto;
import ru.tbank.practicum.dto.enums.BlindUpdateTimeDto;
import ru.tbank.practicum.dto.enums.Status;

import java.util.List;

public interface BlindService {
    public BlindResponseDto newBlind(String jwtToken, BlindCreateDto blindCreateDto);
    public List<BlindResponseDto> getBlinds(String jwtToken);
    public BlindResponseDto getBlind(String jwtToken, Long id);
    public BlindResponseDto updateTimeOpenBlind(String jwtToken, BlindUpdateTimeDto blindUpdateTimeDto);
    public BlindResponseDto updateTimeCloseBlind(String jwtToken, BlindUpdateTimeDto blindUpdateTimeDto);
    public Status getStatusBlind(String jwtToken, Long id);
}
