package ru.tbank.practicum.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.tbank.practicum.controller.dto.*;
import ru.tbank.practicum.service.SettingService;
import ru.tbank.practicum.service.enums.ActionCurtainsEnum;
import ru.tbank.practicum.service.enums.StatusWindowBlindEnum;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

@RestController
@AllArgsConstructor
public class SettingController {

    private SettingService settingService;

    @GetMapping("/api/v1/temperature/{id}")
    public TemperatureDto getTemperature(@PathVariable long id) {
        return settingService.getTemperature(id);
    }
//
    @PutMapping("/api/v1/temperature/")
    public BatteryTempDto updateTemperature(@RequestBody TempBody tempBody) {
        System.out.println(tempBody);

        if (tempBody.getId() == null) {
            throw new IllegalArgumentException("ID must not be null");
        }

        return settingService.updateTemp(tempBody.getId(), tempBody.getTemp(), tempBody.getSetTemp());
    }
    @GetMapping("/api/v1/status/window/{id}")
    public StatusWindowBlindDto getStatus(@PathVariable long id) {
        return settingService.getStatusWindowBlind(id);
    }

    @PutMapping("/api/v1/status/window/")
    public WindowActionDto updateStatusWindow(@RequestBody WindowBody windowBody) {

        if (windowBody.getId() == null) {
            throw new IllegalArgumentException("ID must not be null");
        }

        return settingService.updateWindow(windowBody.getId(), windowBody.getTimeStart(), windowBody.getTimeEnd(), windowBody.getStatus());
    }



}
