package ru.tbank.practicum.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.tbank.practicum.dto.BatteryEvent;
import ru.tbank.practicum.dto.WeatherDto;
import ru.tbank.practicum.entity.Batteries;
import ru.tbank.practicum.entity.UsersCoords;
import ru.tbank.practicum.repository.BatteryRepository;
import ru.tbank.practicum.repository.UsersCoordsRepository;
import ru.tbank.practicum.service.impl.WeatherService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledTasks {
//
//    private final double lon = 51.507545;
//    private final double lat = -0.127794;
//
    private final WeatherService weatherService;
    private final UsersCoordsRepository usersCoordsRepository;
    private final BatteryRepository batteryRepository;
    private final KafkaTemplate<String, LocalTime> kafkaTemplateTime;
    private final KafkaTemplate<String, BatteryEvent> kafkaTemplateWeather;

    @Scheduled(fixedDelay = 60000)
    public void reportCurrentTime() {

//        kafkaTemplateTime.send("topic-time", LocalTime.now());

        List<UsersCoords> usersCoordsList = usersCoordsRepository.findAll();

        for (UsersCoords usersCoord : usersCoordsList) {

            WeatherDto weatherDto = weatherService.getWeatherData(usersCoord);
            log.info("WeatherDto = " + weatherDto);

            Optional<Batteries> batteries = batteryRepository.findByTempOn(weatherDto.getUserId(), Math.round(weatherDto.getTemperature()));
            if (batteries.isPresent()) {
                BatteryEvent batteryEvent = new BatteryEvent();
                batteryEvent.setId(batteries.get().getId());
                batteryEvent.setTempSet(Math.round(weatherDto.getTemperature()));
                kafkaTemplateWeather.send("topic-weather", batteryEvent);
            }

            batteries = batteryRepository.findByTempOff(weatherDto.getUserId(), Math.round(weatherDto.getTemperature()));
            if (batteries.isPresent()) {
                BatteryEvent batteryEvent = new BatteryEvent();
                batteryEvent.setId(batteries.get().getId());
                batteryEvent.setTempSet(Math.round(weatherDto.getTemperature()));
                kafkaTemplateWeather.send("topic-weather", batteryEvent);
            }

        }


//        WeatherDto weatherResponse = weatherService.getWeatherData(lon, lat);
//
//        MDC.put("weatherResponse", String.valueOf(weatherResponse));
//        log.info("Get new weather");
//
//        AmbientTemp ambientTemp = new AmbientTemp();
//        ambientTemp.setTemp(weatherResponse.getTemperature());
//        ambientTemp.setTimeStart(LocalDateTime.now());
//        ambientTempRepository.save(ambientTemp);
    }
}
