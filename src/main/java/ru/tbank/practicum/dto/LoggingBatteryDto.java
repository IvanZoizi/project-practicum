package ru.tbank.practicum.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tbank.practicum.dto.enums.Status;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LoggingBatteryDto {
    private Long newTemp;
    private LocalDateTime localDateTime;
}
