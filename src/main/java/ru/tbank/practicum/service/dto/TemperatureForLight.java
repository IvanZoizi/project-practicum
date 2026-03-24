package ru.tbank.practicum.service.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TemperatureForLight  {
    @JsonProperty("temperature")
    private int temperature;
}
