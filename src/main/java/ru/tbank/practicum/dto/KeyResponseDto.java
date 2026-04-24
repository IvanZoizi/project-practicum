package ru.tbank.practicum.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tbank.practicum.dto.enums.KeyStatus;

@Data
@NoArgsConstructor
public class KeyResponseDto {
    private Long id;
    private Long userId;
    private String room;
    private KeyStatus status;
}
