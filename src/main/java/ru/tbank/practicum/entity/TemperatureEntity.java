package ru.tbank.practicum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TemperatureEntity implements Entity{
    public long id;
    public int temperature;

    public long getId() {
        return id;
    }
}
