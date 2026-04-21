package ru.tbank.practicum.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledTasks {
//
//    private final double lon = 51.507545;
//    private final double lat = -0.127794;
//
//    private final WeatherService weatherService;
//    private final AmbientTempRepository ambientTempRepository;
//
//    @Scheduled(fixedDelay = 5000)
//    public void reportCurrentTime() {
//        WeatherDto weatherResponse = weatherService.getWeatherData(lon, lat);
//
//        MDC.put("weatherResponse", String.valueOf(weatherResponse));
//        log.info("Get new weather");
//
//        AmbientTemp ambientTemp = new AmbientTemp();
//        ambientTemp.setTemp(weatherResponse.getTemperature());
//        ambientTemp.setTimeStart(LocalDateTime.now());
//        ambientTempRepository.save(ambientTemp);
//    }
}
