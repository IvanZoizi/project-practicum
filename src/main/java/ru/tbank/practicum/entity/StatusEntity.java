package ru.tbank.practicum.entity;

import ru.tbank.practicum.service.enums.StatusWindowBlindEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusEntity{
    private Long id;
    private StatusWindowBlindEnum status;
}
