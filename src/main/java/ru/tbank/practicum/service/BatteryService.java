package ru.tbank.practicum.service;

import ru.tbank.practicum.dto.BatteryRequest;
import ru.tbank.practicum.dto.BatteryResponse;
import ru.tbank.practicum.dto.TempSettingRequest;

import java.util.List;

public interface BatteryService {
    public BatteryResponse newBattery(String jwtToken, BatteryRequest batteryRequest);
    public List<BatteryResponse> getAllBatteries(String jwtToken);
    public BatteryResponse getBatteryById(String jwtToken, Long id);
    public Long getTempNow(String jwtToken, Long id);
    public BatteryResponse updateTempSetting(String jwtToken, Long id, TempSettingRequest tempSettingRequest);
}
