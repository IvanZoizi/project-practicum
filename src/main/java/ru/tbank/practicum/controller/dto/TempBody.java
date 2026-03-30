package ru.tbank.practicum.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TempBody {
    private Long id;
    private Long temp;
    private Long setTemp;
}
