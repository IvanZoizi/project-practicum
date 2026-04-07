package ru.tbank.practicum.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tbank.practicum.entity.BatteryTemp;

@AllArgsConstructor
@Data
public class TemperatureDto {
    private Long id;
    private BatteryTemp minTemp;
    private BatteryTemp maxTemp;
}
