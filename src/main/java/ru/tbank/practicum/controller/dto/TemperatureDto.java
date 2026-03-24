package ru.tbank.practicum.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TemperatureDto {
    private Long id;
    private Integer temperature;
}
