package ru.tbank.practicum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDto {
    private Long id;
    private Long userId;
    private Double temperature;
    private String weather;
}