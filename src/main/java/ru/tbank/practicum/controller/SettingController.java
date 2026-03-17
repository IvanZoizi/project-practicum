package ru.tbank.practicum.controller;

import ru.tbank.practicum.controller.dto.ActionCurtainsDto;
import ru.tbank.practicum.controller.dto.StatusWindowBlindDto;
import ru.tbank.practicum.controller.dto.TemperatureDto;
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

    @GetMapping("/get/temperature/{id}")
    public TemperatureDto getTemperature(@PathVariable long id) {
        return settingService.getTemperature(id);
    }

    @PostMapping("/new/temperature/")
    public TemperatureDto newTemperature(@RequestParam int temperature) {
        return settingService.newTemperature(temperature);
    }

    @DeleteMapping("/delete/temperature/{id}")
    public void deleteTemperature(@PathVariable long id) {
        settingService.deleteTemperature(id);
    }

    @GetMapping("/get/status/window/{id}")
    public StatusWindowBlindDto getStatus(@PathVariable long id) {
        return settingService.getStatusWindowBlind(id);
    }

    @PostMapping("/new/status/window")
    public StatusWindowBlindDto newStatus(@RequestParam StatusWindowBlindEnum status) {
        return settingService.newStatusWindowBlind(status);
    }

    @DeleteMapping("/delete/status/window/{id}")
    public void deleteStatus(@PathVariable long id) {
        settingService.deleteStatus(id);
    }

    @GetMapping("/get/action/{id}")
    public ActionCurtainsDto getAction(@PathVariable long id) {
        return settingService.getTimeActionCurtains(id);
    }

    @PostMapping("/new/action")
    public ActionCurtainsDto newAction(@RequestParam String time, @RequestParam ActionCurtainsEnum actionCurtainsEnum) {
        try {
            LocalTime localTime = LocalTime.parse(time);
            return settingService.newTimeActionCurtains(localTime, actionCurtainsEnum);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Неверный формат времени. Ожидается формат HH:mm (например, 14:30)");
        }
    }

    @DeleteMapping("/delete/action/{id}")
    public void deleteAction(@PathVariable long id) {
        settingService.deleteTimeActionCurtains(id);
    }

}
