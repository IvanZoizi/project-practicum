package ru.tbank.practicum.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
    private int id;
    private String main;
    private String description;
    private String icon;
    private Wind wind;
    private Rain rain;
    private Clouds clouds;
}
