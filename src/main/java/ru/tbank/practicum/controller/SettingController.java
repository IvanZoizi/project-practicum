package ru.tbank.practicum.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
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
@Slf4j
public class SettingController {

    private SettingService settingService;

    @GetMapping("/api/v1/temperature/{id}")
    public TemperatureDto getTemperature(@PathVariable long id) {

        MDC.put("id", String.valueOf(id));
        log.info("Get /api/v1/temperature/{id}");

        return settingService.getTemperature(id);
    }
//
    @PutMapping("/api/v1/temperature/")
    public BatteryTempDto updateTemperature(@Valid @RequestBody TempBody tempBody) {

        MDC.put("tempBody", String.valueOf(tempBody));
        log.info("Put /api/v1/temperature/");

        return settingService.updateTemp(tempBody.getId(), tempBody.getTemp(), tempBody.getSetTemp());
    }
    @GetMapping("/api/v1/status/window/{id}")
    public StatusWindowBlindDto getStatus(@PathVariable long id) {

        MDC.put("id", String.valueOf(id));
        log.info("Get /api/v1/status/window/{id}");

        return settingService.getStatusWindowBlind(id);
    }

    @PutMapping("/api/v1/status/window/")
    public WindowActionDto updateStatusWindow(@Valid @RequestBody WindowBody windowBody) {

        MDC.put("windowBody", String.valueOf(windowBody));
        log.info("Put /api/v1/status/window/");

        return settingService.updateWindow(windowBody.getId(), windowBody.getTimeStart(), windowBody.getTimeEnd(), windowBody.getStatus());
    }



}
