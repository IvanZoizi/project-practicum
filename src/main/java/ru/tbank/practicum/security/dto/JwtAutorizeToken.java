package ru.tbank.practicum.security.dto;

import lombok.Data;

@Data
public class JwtAutorizeToken {
    String token;
    String refreshToken;
}
