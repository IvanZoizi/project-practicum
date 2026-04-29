package ru.tbank.practicum.service;

import ru.tbank.practicum.dto.*;
import ru.tbank.practicum.entity.Users;
import ru.tbank.practicum.security.dto.JwtAutorizeToken;

import javax.naming.AuthenticationException;

public interface UserService {
    public String register(RegisterDto registerDto);
    public JwtAutorizeToken singIn(RegisterDto registerDto) throws AuthenticationException;
    public Users getUserByToken(String token);

    public CoordsResponseDto newUserCoords(String jwtToken, CoordsRequestDto coordsRequestDto);
    public CoordsResponseDto getUserCoords(String jwtToken);

}
