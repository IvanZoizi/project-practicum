package ru.tbank.practicum.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tbank.practicum.controller.dto.BatteryTempDto;
import ru.tbank.practicum.controller.dto.StatusWindowBlindDto;
import ru.tbank.practicum.controller.dto.TemperatureDto;
import ru.tbank.practicum.controller.dto.WindowActionDto;
import ru.tbank.practicum.entity.ActionStatus;
import ru.tbank.practicum.entity.BatterySettings;
import ru.tbank.practicum.entity.BatteryTemp;
import ru.tbank.practicum.entity.WindowBlindAction;
import ru.tbank.practicum.entity.WindowBlindSettings;
import ru.tbank.practicum.mapper.SettingMapper;
import ru.tbank.practicum.repository.BatterySettingsRepository;
import ru.tbank.practicum.repository.BatteryTempRepository;
import ru.tbank.practicum.repository.WindowBlindActionRepository;
import ru.tbank.practicum.repository.WindowBlindSettingsRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SettingServiceImplTest {

    @Mock
    private SettingMapper settingMapper;

    @Mock
    private BatterySettingsRepository batterySettingsRepository;

    @Mock
    private BatteryTempRepository batteryTempRepository;

    @Mock
    private WindowBlindSettingsRepository windowBlindSettingsRepository;

    @Mock
    private WindowBlindActionRepository windowBlindActionRepository;

    @InjectMocks
    private SettingServiceImpl settingService;

    @Test
    @DisplayName("Тест getTemperature null")
    public void testGetTemperatureNotFound() {
        Long id = 9999L;

        when(batterySettingsRepository.findById(id)).thenReturn(Optional.empty());
        when(settingMapper.getDtoBatterySettings(Optional.empty())).thenReturn(null);

        TemperatureDto result = settingService.getTemperature(id);

        assertNull(result);
        verify(batterySettingsRepository).findById(id);
        verify(settingMapper).getDtoBatterySettings(Optional.empty());
    }

    @Test
    @DisplayName("Тест updateTemp")
    public void testUpdateTempSuccess() {
        Long id = 1L;
        Long temp = 25L;
        Long setTemp = 30L;
        BatteryTemp batteryTemp = new BatteryTemp();
        BatteryTempDto expectedDto = new BatteryTempDto(id, temp, setTemp);

        when(batteryTempRepository.updateTempById(id, temp, setTemp)).thenReturn(1);
        when(batteryTempRepository.findById(id)).thenReturn(Optional.of(batteryTemp));
        when(settingMapper.getDtoBatteryTemp(Optional.of(batteryTemp))).thenReturn(expectedDto);

        BatteryTempDto result = settingService.updateTemp(id, temp, setTemp);

        assertNotNull(result);
        assertEquals(expectedDto, result);
        assertEquals(id, result.getId());
        assertEquals(temp, result.getTemp());
        assertEquals(setTemp, result.getSetTemp());
        verify(batteryTempRepository).updateTempById(id, temp, setTemp);
        verify(batteryTempRepository).findById(id);
        verify(settingMapper).getDtoBatteryTemp(Optional.of(batteryTemp));
    }

    @Test
    @DisplayName("Тест updateTemp null")
    public void testUpdateTempNotFound() {
        Long id = 9999L;
        Long temp = 25L;
        Long setTemp = 30L;

        when(batteryTempRepository.updateTempById(id, temp, setTemp)).thenReturn(0);
        when(batteryTempRepository.findById(id)).thenReturn(Optional.empty());
        when(settingMapper.getDtoBatteryTemp(Optional.empty())).thenReturn(null);

        BatteryTempDto result = settingService.updateTemp(id, temp, setTemp);

        assertNull(result);
        verify(batteryTempRepository).updateTempById(id, temp, setTemp);
        verify(batteryTempRepository).findById(id);
        verify(settingMapper).getDtoBatteryTemp(Optional.empty());
    }

    @Test
    @DisplayName("Тест getStatusWindowBlind")
    public void testGetStatusWindowBlindSuccess() {
        Long id = 1L;
        WindowBlindSettings windowBlindSettings = new WindowBlindSettings();
        StatusWindowBlindDto expectedDto = new StatusWindowBlindDto();

        when(windowBlindSettingsRepository.findById(id)).thenReturn(Optional.of(windowBlindSettings));
        when(settingMapper.getDtoWindowStatus(Optional.of(windowBlindSettings))).thenReturn(expectedDto);

        StatusWindowBlindDto result = settingService.getStatusWindowBlind(id);

        assertNotNull(result);
        assertEquals(expectedDto, result);
        verify(windowBlindSettingsRepository).findById(id);
        verify(settingMapper).getDtoWindowStatus(Optional.of(windowBlindSettings));
    }

    @Test
    @DisplayName("Тест getStatusWindowBlind null")
    public void testGetStatusWindowBlindNotFound() {
        Long id = 9999L;

        when(windowBlindSettingsRepository.findById(id)).thenReturn(Optional.empty());
        when(settingMapper.getDtoWindowStatus(Optional.empty())).thenReturn(null);

        StatusWindowBlindDto result = settingService.getStatusWindowBlind(id);

        assertNull(result);
        verify(windowBlindSettingsRepository).findById(id);
        verify(settingMapper).getDtoWindowStatus(Optional.empty());
    }


    @Test
    @DisplayName("Тест updateWindow null")
    public void testUpdateWindowNotFound() {
        Long id = 9999L;
        LocalDateTime timeStart = LocalDateTime.now();
        LocalDateTime timeEnd = timeStart.plusHours(2);
        ActionStatus status = ActionStatus.PENDING;

        when(windowBlindActionRepository.updateWindowById(id, timeStart, timeEnd, status)).thenReturn(1);
        when(windowBlindActionRepository.findById(id)).thenReturn(Optional.empty());
        when(settingMapper.getDtoWindowAction(Optional.empty())).thenReturn(null);

        WindowActionDto result = settingService.updateWindow(id, timeStart, timeEnd, status);

        assertNull(result);
        verify(windowBlindActionRepository).updateWindowById(id, timeStart, timeEnd, status);
        verify(windowBlindActionRepository).findById(id);
        verify(settingMapper).getDtoWindowAction(Optional.empty());
    }
}