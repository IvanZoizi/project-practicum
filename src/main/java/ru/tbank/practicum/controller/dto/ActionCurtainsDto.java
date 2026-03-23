package ru.tbank.practicum.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tbank.practicum.service.enums.ActionCurtainsEnum;

import java.time.LocalTime;

@AllArgsConstructor  // Создает конструктор со всеми полями
@NoArgsConstructor   // Добавляет конструктор без параметров (опционально)
@Data
public class ActionCurtainsDto {
    private Long id;
    private LocalTime time;
    private ActionCurtainsEnum status;
}