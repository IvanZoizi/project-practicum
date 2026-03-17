package ru.tbank.practicum.job;

import ru.tbank.practicum.controller.dto.WeatherDto;
import ru.tbank.practicum.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ScheduledTasks {

    private final double lon = 51.507545;
    private final double lat = -0.127794;

    private WeatherService weatherService;

    @Scheduled(fixedDelay = 5000)
    public void reportCurrentTime() {
        WeatherDto weatherResponse = weatherService.getWeatherData(lon, lat);
        System.out.println(weatherResponse);
    }
}
