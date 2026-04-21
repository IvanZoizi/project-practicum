package ru.tbank.practicum.service;

import ru.tbank.practicum.controller.dto.RegisterDto;
import ru.tbank.practicum.entity.Users;
import ru.tbank.practicum.security.CustomUserDetail;
import ru.tbank.practicum.security.dto.JwtAutorizeToken;

import javax.naming.AuthenticationException;

public interface UserService {
    public String register(RegisterDto registerDto);
    public JwtAutorizeToken singIn(RegisterDto registerDto) throws AuthenticationException;
}
