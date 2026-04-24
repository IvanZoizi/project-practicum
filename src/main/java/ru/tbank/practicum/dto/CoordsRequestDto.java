package ru.tbank.practicum.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CoordsRequestDto {
    private Double lat;
    private Double lon;
}
