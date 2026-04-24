package ru.tbank.practicum.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CoordsResponseDto {
    private Long id;
    private Double lat;
    private Double lon;
}
