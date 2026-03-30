// Создайте DTO для запроса
package ru.tbank.practicum.controller.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String login;
}