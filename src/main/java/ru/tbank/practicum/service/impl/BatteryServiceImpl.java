package ru.tbank.practicum.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tbank.practicum.dto.BatteryRequest;
import ru.tbank.practicum.dto.BatteryResponse;
import ru.tbank.practicum.dto.TempSettingRequest;
import ru.tbank.practicum.entity.Batteries;
import ru.tbank.practicum.entity.BatteriesSetting;
import ru.tbank.practicum.entity.Users;
import ru.tbank.practicum.mapper.SettingMapper;
import ru.tbank.practicum.repository.BatteryRepository;
import ru.tbank.practicum.repository.BatterySettingRepository;
import ru.tbank.practicum.service.BatteryService;
import ru.tbank.practicum.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BatteryServiceImpl implements BatteryService {

    private UserService userService;
    private BatteryRepository batteryRepository;
    private BatterySettingRepository batterySettingRepository;
    private SettingMapper settingMapper;

    @Override
    public BatteryResponse newBattery(String jwtToken, BatteryRequest batteryRequest) {
        Users user = userService.getUserByToken(jwtToken);

        Batteries batteries = new Batteries();
        batteries.setRoom(batteryRequest.getRoom());
        batteries.setUser(user);
        batteries.setTempNow(batteryRequest.getTempNow());

        Batteries battery = batteryRepository.save(batteries);

        BatteriesSetting batteriesSetting = new BatteriesSetting();
        batteriesSetting.setBatteries(battery);
        batteriesSetting.setTempOn(batteryRequest.getTempSettingRequest().getTempOn());
        batteriesSetting.setTempOff(batteryRequest.getTempSettingRequest().getTempOff());
        batteriesSetting.setTempSet(batteryRequest.getTempSettingRequest().getTempSet());

        return settingMapper.getDto(batteries, batterySettingRepository.save(batteriesSetting));
    }

    @Override
    @Transactional
    public List<BatteryResponse> getAllBatteries(String jwtToken) {
        Users user = userService.getUserByToken(jwtToken);
        return batteryRepository.findAllByUser_Id(user.getId())
                .map(x -> settingMapper.getDto(x, x.getBatteriesSetting()))
                .toList();
    }

    @Override
    public BatteryResponse getBatteryById(String jwtToken, Long id) {
        Users user = userService.getUserByToken(jwtToken);
        Batteries batteries = batteryRepository.findByUser_IdAndId(user.getId(), id)
                .orElseThrow(() -> new IllegalArgumentException());
        return settingMapper.getDto(batteries, batteries.getBatteriesSetting());
    }

    @Override
    public Long getTempNow(String jwtToken, Long id) {
        return getBatteryById(jwtToken, id).getTempNow();
    }

    @Override
    public BatteryResponse updateTempSetting(String jwtToken, Long id, TempSettingRequest tempSettingRequest) {
        Users user = userService.getUserByToken(jwtToken);
        Batteries batteries = batteryRepository.findByUser_IdAndId(user.getId(), id)
                .orElseThrow(() -> new IllegalArgumentException());
        BatteriesSetting batteriesSetting = batteries.getBatteriesSetting();
        batteriesSetting.setTempSet(tempSettingRequest.getTempSet());
        batteriesSetting.setTempOn(tempSettingRequest.getTempOn());
        batteriesSetting.setTempOff(tempSettingRequest.getTempOff());
        return settingMapper.getDto(batteries, batterySettingRepository.save(batteriesSetting));
    }
}
