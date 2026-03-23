package ru.tbank.practicum.service.dto;

import ru.tbank.practicum.service.enums.StatusWindowBlindEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusWindowBlind {
    private StatusWindowBlindEnum statusWindowBlindEnum;
}
