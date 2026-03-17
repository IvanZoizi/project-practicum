package ru.tbank.practicum.entity;

import ru.tbank.practicum.controller.dto.ActionCurtainsDto;
import ru.tbank.practicum.service.enums.ActionCurtainsEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class ActionCurtainsEntity implements Entity{
    public Long id;
    public LocalTime time;
    public ActionCurtainsEnum status;

    public long getId() {
        return id;
    }
}
