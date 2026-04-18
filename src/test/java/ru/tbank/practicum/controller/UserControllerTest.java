package ru.tbank.practicum.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.tbank.practicum.controller.dto.CreateUserDto;
import ru.tbank.practicum.controller.dto.DevicesDto;
import ru.tbank.practicum.controller.dto.LoginRequest;
import ru.tbank.practicum.controller.dto.UserDto;
import ru.tbank.practicum.entity.BatterySettings;
import ru.tbank.practicum.entity.Users;
import ru.tbank.practicum.entity.WindowBlindSettings;
import ru.tbank.practicum.mapper.SettingMapper;
import ru.tbank.practicum.service.UserServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SettingMapper settingMapper;

    @MockitoBean
    private UserServiceImpl userService;

    @Test
    @DisplayName("Тест /api/v1/user/ ")
    public void testPostUser() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLogin("testUser");

        CreateUserDto createUserDto = new CreateUserDto(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L);

        when(userService.createUser(loginRequest)).thenReturn(createUserDto);

        mockMvc.perform(post("/api/v1/user/")
                        .contentType("application/json")
                        .content("{\"login\":\"testUser\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.deviceId").value(2L))
                .andExpect(jsonPath("$.windowSettingsId").value(3L))
                .andExpect(jsonPath("$.batterySettingsId").value(4L))
                .andExpect(jsonPath("$.openActionId").value(5L))
                .andExpect(jsonPath("$.closeActionId").value(6L))
                .andExpect(jsonPath("$.minTempId").value(7L))
                .andExpect(jsonPath("$.maxTempId").value(8L));
    }

    @Test
    @DisplayName("Тест /api/v1/user/{id}")
    public void testGetUser() throws Exception {
        UserDto userDto = new UserDto(1L, "testUser");
        when(userService.getUser(1L)).thenReturn(userDto);

        mockMvc.perform(get("/api/v1/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.login").value("testUser"));
    }

    @Test
    @DisplayName("Тест /api/v1/user/{id} null")
    public void testGetUserNotFound() throws Exception {
        when(userService.getUser(9999L)).thenReturn(null);

        mockMvc.perform(get("/api/v1/user/9999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    @DisplayName("Тест /api/v1/user/device/{id}")
    public void testGetDevice() throws Exception {
        Users user = new Users();
        user.setId(1L);
        user.setLogin("testUser");

        BatterySettings batterySettings = new BatterySettings();
        batterySettings.setId(10L);

        WindowBlindSettings windowBlindSettings = new WindowBlindSettings();
        windowBlindSettings.setId(20L);

        DevicesDto devicesDto = new DevicesDto(100L, user, batterySettings, windowBlindSettings);

        when(userService.getDevices(100L)).thenReturn(devicesDto);

        mockMvc.perform(get("/api/v1/user/device/100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(100L))
                .andExpect(jsonPath("$.user.id").value(1L))
                .andExpect(jsonPath("$.user.login").value("testUser"))
                .andExpect(jsonPath("$.battery.id").value(10L))
                .andExpect(jsonPath("$.windowBlind.id").value(20L));
    }

    @Test
    @DisplayName("Тест /api/v1/user/device/{id} null")
    public void testGetDeviceNotFound() throws Exception {
        when(userService.getDevices(9999L)).thenReturn(null);

        mockMvc.perform(get("/api/v1/user/device/9999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }
}