package ru.tbank.practicum.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.tbank.practicum.entity.BatterySettings;
import ru.tbank.practicum.entity.Users;
import ru.tbank.practicum.entity.WindowBlindSettings;

@Data
@AllArgsConstructor
public class DevicesDto {
    private Long id;
    private Users user;
    private BatterySettings battery;
    private WindowBlindSettings windowBlind;
}
