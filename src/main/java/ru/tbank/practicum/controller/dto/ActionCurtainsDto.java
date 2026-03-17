package ru.tbank.practicum.controller.dto;

import ru.tbank.practicum.service.enums.ActionCurtainsEnum;
import ru.tbank.practicum.service.enums.StatusWindowBlindEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ActionCurtainsDto {
    public Long id;
    public LocalTime time;
    public ActionCurtainsEnum status;
}
