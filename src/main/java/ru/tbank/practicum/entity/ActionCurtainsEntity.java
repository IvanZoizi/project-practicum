package ru.tbank.practicum.entity;

import lombok.AllArgsConstructor;
import ru.tbank.practicum.service.enums.ActionCurtainsEnum;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class ActionCurtainsEntity{
    private Long id;
    private LocalTime time;
    private ActionCurtainsEnum status;
}
