package ru.tbank.practicum.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.tbank.practicum.entity.ActionStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class WindowActionDto {
    private Long id;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private ActionStatus status;
}
