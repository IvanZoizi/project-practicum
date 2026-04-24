package ru.tbank.practicum.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tbank.practicum.dto.enums.KeyStatus;

@Data
@NoArgsConstructor
public class KeyRequestDto {
    private String room;
    private KeyStatus status;
}
