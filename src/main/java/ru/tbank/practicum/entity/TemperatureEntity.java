package ru.tbank.practicum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TemperatureEntity{
    private long id;
    private int temperature;
}
