package ru.tbank.practicum.config;

import ru.tbank.practicum.mapper.SettingMapper;
import ru.tbank.practicum.repository.SettingRepository;
import ru.tbank.practicum.service.SettingService;
import ru.tbank.practicum.service.SettingServiceImpl;
import ru.tbank.practicum.service.WeatherService;
import ru.tbank.practicum.service.WeatherServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://api.openweathermap.org")
                .build();
    }

    @Bean
    public SettingService settingService(SettingMapper settingMapper,
                                         SettingRepository settingRepository) {
        return new SettingServiceImpl(settingMapper, settingRepository);
    }

    @Bean
    public WeatherService weatherService(WebClient webClient,
                                         SettingMapper settingMapper) {
        return new WeatherServiceImpl(webClient, settingMapper);
    }
}
