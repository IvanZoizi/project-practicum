package ru.tbank.practicum.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tbank.practicum.dto.CoordsRequestDto;
import ru.tbank.practicum.dto.CoordsResponseDto;
import ru.tbank.practicum.dto.RegisterDto;
import ru.tbank.practicum.security.dto.JwtAutorizeToken;
import ru.tbank.practicum.security.jwt.JwtService;
import ru.tbank.practicum.service.UserService;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/")
@Tag(name="users")
@AllArgsConstructor
@Slf4j
public class UserController {

    private UserService userService;
    private JwtService jwtService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAutorizeToken> singIn(@RequestBody RegisterDto userCredentialsDto) {
        try {
            JwtAutorizeToken jwtAuthenticationDto = userService.singIn(userCredentialsDto);
            return ResponseEntity.ok(jwtAuthenticationDto);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/coords")
    public ResponseEntity<CoordsResponseDto> setUserCoord(@RequestHeader("Authorization") String authHeader,
                                                          @RequestBody CoordsRequestDto coordsRequestDto) {
        try {
            String jwtToken = jwtService.getJwtToken(authHeader);
            return ResponseEntity.ok(userService.newUserCoords(jwtToken, coordsRequestDto));
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/coords")
    public ResponseEntity<CoordsResponseDto> getUserCoord(@RequestHeader("Authorization") String authHeader) {
        try {
            String jwtToken = jwtService.getJwtToken(authHeader);
            return ResponseEntity.ok(userService.getUserCoords(jwtToken));
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
