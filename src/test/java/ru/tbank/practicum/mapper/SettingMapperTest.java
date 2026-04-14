package ru.tbank.practicum.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tbank.practicum.base.BaseTestcontainers;
import ru.tbank.practicum.controller.dto.BatteryTempDto;
import ru.tbank.practicum.controller.dto.StatusWindowBlindDto;
import ru.tbank.practicum.controller.dto.TemperatureDto;
import ru.tbank.practicum.controller.dto.WindowActionDto;
import ru.tbank.practicum.entity.ActionStatus;
import ru.tbank.practicum.repository.BatterySettingsRepository;
import ru.tbank.practicum.repository.BatteryTempRepository;
import ru.tbank.practicum.repository.WindowBlindActionRepository;
import ru.tbank.practicum.repository.WindowBlindSettingsRepository;

import static org.assertj.core.api.Assertions.assertThat;



public class SettingMapperTest extends BaseTestcontainers {

    @Autowired
    public WindowBlindActionRepository windowBlindActionRepository;

    @Autowired
    public WindowBlindSettingsRepository windowBlindSettingsRepository;

    @Autowired
    public BatteryTempRepository batteryTempRepository;

    @Autowired
    public BatterySettingsRepository batterySettingsRepository;

    @Autowired
    public SettingMapper settingMapper;

    @Test
    @DisplayName("Проверка мапинга WindowBlindAction в WindowActionDto")
    public void testMappingWindowBlindActionToWindowActionDto() {
        WindowActionDto windowActionDto = settingMapper.getDtoWindowAction(windowBlindActionRepository.findById(1L));

        assertThat(windowActionDto).isNotNull();
        assertThat(windowActionDto.getId()).isEqualTo(1L);
        assertThat(windowActionDto.getStatus()).isEqualTo(ActionStatus.COMPLETED);
        assertThat(windowActionDto.getStatus().name()).isEqualTo("COMPLETED");
    }

    @Test
    @DisplayName("Проверка мапинга WindowBlindAction (null) в WindowActionDto")
    public void testMappingNullWindowBlindActionToWindowActionDto() {
        WindowActionDto windowActionDto = settingMapper.getDtoWindowAction(windowBlindActionRepository.findById(9999L));

        assertThat(windowActionDto).isNotNull();
        assertThat(windowActionDto.getId()).isNull();
        assertThat(windowActionDto.getTimeStart()).isNull();
        assertThat(windowActionDto.getTimeEnd()).isNull();
        assertThat(windowActionDto.getStatus()).isNull();
    }

    @Test
    @DisplayName("Проверка мапинга WindowBlindSettings в StatusWindowBlindDto")
    public void testMappingWindowBlindSettingsToStatusWindowBlindDto() {
        StatusWindowBlindDto windowActionDto = settingMapper.getDtoWindowStatus(windowBlindSettingsRepository.findById(1L));

        assertThat(windowActionDto).isNotNull();
        assertThat(windowActionDto.getId()).isEqualTo(1L);
        assertThat(windowActionDto.getOpenAction().getId()).isEqualTo(1L);
        assertThat(windowActionDto.getCloseAction().getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Проверка мапинга WindowBlindSettings (null) в StatusWindowBlindDto")
    public void testMappingNullWindowBlindSettingsToStatusWindowBlindDto() {
        StatusWindowBlindDto windowActionDto = settingMapper.getDtoWindowStatus(windowBlindSettingsRepository.findById(9999L));

        assertThat(windowActionDto).isNotNull();
        assertThat(windowActionDto.getId()).isNull();
        assertThat(windowActionDto.getOpenAction()).isNull();
        assertThat(windowActionDto.getCloseAction()).isNull();
    }

    @Test
    @DisplayName("Проверка мапинга BatteryTemp в BatteryTempDto")
    public void testMappingWindowBatteryTempToBatteryTempDto() {
        BatteryTempDto windowActionDto = settingMapper.getDtoBatteryTemp(batteryTempRepository.findById(1L));

        assertThat(windowActionDto).isNotNull();
        assertThat(windowActionDto.getId()).isEqualTo(1L);
        assertThat(windowActionDto.getTemp()).isEqualTo(20L);
        assertThat(windowActionDto.getSetTemp()).isEqualTo(23L);
    }

    @Test
    @DisplayName("Проверка мапинга BatteryTemp (null) в BatteryTempDto")
    public void testMappingNullBatteryTempToBatteryTempDto() {
        BatteryTempDto windowActionDto = settingMapper.getDtoBatteryTemp(batteryTempRepository.findById(9999L));

        assertThat(windowActionDto).isNotNull();
        assertThat(windowActionDto.getId()).isNull();
        assertThat(windowActionDto.getTemp()).isNull();
        assertThat(windowActionDto.getSetTemp()).isNull();
    }


    @Test
    @DisplayName("Проверка мапинга BatterySettings в TemperatureDto")
    public void testMappingBatterySettingsTempToTemperatureDto() {
        TemperatureDto windowActionDto = settingMapper.getDtoBatterySettings(batterySettingsRepository.findById(1L));

        assertThat(windowActionDto).isNotNull();
        assertThat(windowActionDto.getId()).isEqualTo(1L);
        assertThat(windowActionDto.getMaxTemp().getId()).isEqualTo(2L);
        assertThat(windowActionDto.getMinTemp().getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Проверка мапинга BatterySettings (null) в TemperatureDto")
    public void testMappingNullBatterySettingsToTemperatureDto() {
        TemperatureDto windowActionDto = settingMapper.getDtoBatterySettings(batterySettingsRepository.findById(9999L));

        assertThat(windowActionDto).isNotNull();
        assertThat(windowActionDto.getId()).isNull();
        assertThat(windowActionDto.getMaxTemp()).isNull();
        assertThat(windowActionDto.getMinTemp()).isNull();
    }


}
