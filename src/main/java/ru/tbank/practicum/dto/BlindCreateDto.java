package ru.tbank.practicum.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tbank.practicum.dto.enums.Status;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class BlindCreateDto {
    private String room;
    private Status status;
    private LocalTime timeOpen;
    private LocalTime timeClose;
}
