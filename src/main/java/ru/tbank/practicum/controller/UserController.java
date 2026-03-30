package ru.tbank.practicum.controller;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tbank.practicum.controller.dto.*;
import ru.tbank.practicum.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/")
    public CreateUserDto postUser(@RequestBody LoginRequest loginRequest) {
        return userService.createUser(loginRequest); // возвращайте нужный DTO
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/device/{id}")
    public DevicesDto getDevice(@PathVariable Long id) {
        return userService.getDevices(id); // возвращайте нужный DTO
    }
}
