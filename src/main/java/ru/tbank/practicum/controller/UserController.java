package ru.tbank.practicum.controller;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.*;
import ru.tbank.practicum.controller.dto.*;
import ru.tbank.practicum.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
@Slf4j
public class UserController {

    private UserService userService;

    @PostMapping("/")
    public CreateUserDto postUser(@Valid @RequestBody LoginRequest loginRequest) {

        MDC.put("loginRequsets", String.valueOf(loginRequest));
        log.info("Create new user");

        return userService.createUser(loginRequest);
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {

        MDC.put("id", String.valueOf(id));
        log.info("Get user by id");

        return userService.getUser(id);
    }

    @GetMapping("/device/{id}")
    public DevicesDto getDevice(@PathVariable Long id) {

        MDC.put("id", String.valueOf(id));
        log.info("Get user devices by id user");

        return userService.getDevices(id);
    }
}
