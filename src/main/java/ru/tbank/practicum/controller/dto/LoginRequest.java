// Создайте DTO для запроса
package ru.tbank.practicum.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "login is required")
    private String login;
}