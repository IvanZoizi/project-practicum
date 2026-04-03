package ru.tbank.practicum.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TempBody {
    @NotNull
    private Long id;
    @NotNull
    private Long temp;
    @NotNull
    private Long setTemp;
}
