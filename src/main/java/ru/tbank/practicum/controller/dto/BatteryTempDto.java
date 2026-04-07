package ru.tbank.practicum.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BatteryTempDto {
    private Long id;
    private Long temp;
    private Long setTemp;
}
