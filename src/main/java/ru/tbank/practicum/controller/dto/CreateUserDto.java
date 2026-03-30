package ru.tbank.practicum.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class CreateUserDto {
    private Long userId;
    private Long deviceId;
    private Long windowSettingsId;
    private Long batterySettingsId;
    private Long openActionId;
    private Long closeActionId;
    private Long minTempId;
    private Long maxTempId;
}
