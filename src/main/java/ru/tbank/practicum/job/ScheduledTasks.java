package ru.tbank.practicum.job;

import lombok.RequiredArgsConstructor;
import ru.tbank.practicum.controller.dto.WeatherDto;
import ru.tbank.practicum.entity.AmbientTemp;
import ru.tbank.practicum.repository.AmbientTempRepository;
import ru.tbank.practicum.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private final double lon = 51.507545;
    private final double lat = -0.127794;

    private final WeatherService weatherService;
    private final AmbientTempRepository ambientTempRepository;

    @Scheduled(fixedDelay = 5000)
    public void reportCurrentTime() {
        WeatherDto weatherResponse = weatherService.getWeatherData(lon, lat);
        System.out.println(weatherResponse);
        AmbientTemp ambientTemp = new AmbientTemp();
        ambientTemp.setTemp(weatherResponse.getTemperature());
        ambientTemp.setTimeStart(LocalDateTime.now());
        ambientTempRepository.save(ambientTemp);
    }
}
