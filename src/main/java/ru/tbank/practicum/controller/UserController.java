package ru.tbank.practicum.controller;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tbank.practicum.controller.dto.RegisterDto;
import ru.tbank.practicum.security.dto.JwtAutorizeToken;
import ru.tbank.practicum.service.UserService;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/")
@AllArgsConstructor
@Slf4j
public class UserController {

    private UserService userService;

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

    @GetMapping("/welcome")
    public String getVoid() {
        return "Теперь ты зарегистрирован";
    }

}
