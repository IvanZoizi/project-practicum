package ru.tbank.practicum.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
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
    public Counter getCounterMetricsRequest(MeterRegistry registry) {
        return Counter.builder("reqyests.count") // Имя метрики
                .description("Total number of requests")   // Описание
                .register(registry);
    }
}
