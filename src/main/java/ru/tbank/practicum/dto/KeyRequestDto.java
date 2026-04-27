package ru.tbank.practicum.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tbank.practicum.dto.enums.Status;

@Data
@NoArgsConstructor
public class KeyRequestDto {
    private String room;
    private Status status;
}
