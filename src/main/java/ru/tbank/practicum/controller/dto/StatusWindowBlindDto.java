package ru.tbank.practicum.controller.dto;

import ru.tbank.practicum.service.enums.StatusWindowBlindEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StatusWindowBlindDto {
    public Long id;
    public StatusWindowBlindEnum status;
}
