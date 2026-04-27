package ru.tbank.practicum.dto.enums;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class BlindUpdateTimeDto {
    private Long id;
    private LocalTime time;
}
