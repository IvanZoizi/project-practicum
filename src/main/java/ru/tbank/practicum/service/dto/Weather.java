package ru.tbank.practicum.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
    @JsonProperty("id")
    private int id;

    @JsonProperty("main")
    private String main;

    @JsonProperty("description")
    private String description;

    @JsonProperty("icon")
    private String icon;

    @JsonProperty("wind")
    private Wind wind;

    @JsonProperty("rain")
    private Rain rain;

    @JsonProperty("clouds")
    private Clouds clouds;
}
