package ru.tbank.practicum.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tbank.practicum.controller.dto.CreateUserDto;
import ru.tbank.practicum.controller.dto.DevicesDto;
import ru.tbank.practicum.controller.dto.LoginRequest;
import ru.tbank.practicum.controller.dto.UserDto;
import ru.tbank.practicum.entity.*;
import ru.tbank.practicum.mapper.SettingMapper;
import ru.tbank.practicum.repository.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private WindowBlindSettingsRepository windowBlindSettingsRepository;

    @Mock
    private BatterySettingsRepository batterySettingsRepository;

    @Mock
    private WindowBlindActionRepository windowBlindActionRepository;

    @Mock
    private BatteryTempRepository batteryTempRepository;

    @Mock
    private SettingMapper settingMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Тест createUser")
    public void testCreateUserWithCorrectValues() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLogin("newUser");

        Users savedUser = new Users();
        savedUser.setId(5L);
        savedUser.setLogin("newUser");

        when(userRepository.save(any(Users.class))).thenAnswer(invocation -> {
            Users user = invocation.getArgument(0);
            assertEquals("newUser", user.getLogin());
            return savedUser;
        });

        when(windowBlindSettingsRepository.save(any(WindowBlindSettings.class))).thenReturn(new WindowBlindSettings());
        when(windowBlindActionRepository.save(any(WindowBlindAction.class))).thenReturn(new WindowBlindAction(), new WindowBlindAction());
        when(batterySettingsRepository.save(any(BatterySettings.class))).thenReturn(new BatterySettings());
        when(batteryTempRepository.save(any(BatteryTemp.class))).thenAnswer(invocation -> {
            BatteryTemp temp = invocation.getArgument(0);
            assertNotNull(temp.getTemp());
            assertNotNull(temp.getSetTemp());
            return temp;
        });
        when(deviceRepository.save(any(Devices.class))).thenReturn(new Devices());

        CreateUserDto mockDto = new CreateUserDto(null, null, null, null, null, null, null, null);
        when(settingMapper.getDto(any(Users.class), any(Devices.class), any(WindowBlindSettings.class),
                any(BatterySettings.class), any(WindowBlindAction.class), any(WindowBlindAction.class),
                any(BatteryTemp.class), any(BatteryTemp.class))).thenReturn(mockDto);

        userService.createUser(loginRequest);

        verify(batteryTempRepository, times(2)).save(any(BatteryTemp.class));
    }

    @Test
    @DisplayName("Тест getUser ")
    public void testGetUserSuccess() {
        Long id = 1L;
        Users user = new Users();
        user.setId(id);
        user.setLogin("testUser");

        UserDto expectedDto = new UserDto(id, "testUser");

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(settingMapper.getDto(user)).thenReturn(expectedDto);

        UserDto result = userService.getUser(id);

        assertNotNull(result);
        assertEquals(expectedDto, result);
        assertEquals(id, result.getId());
        assertEquals("testUser", result.getLogin());
        verify(userRepository).findById(id);
        verify(settingMapper).getDto(user);
    }

    @Test
    @DisplayName("Тест getUser null")
    public void testGetUserNotFound() {
        Long id = 9999L;

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(java.util.NoSuchElementException.class, () -> userService.getUser(id));
        verify(userRepository).findById(id);
        verify(settingMapper, never()).getDto(any(Users.class));
    }

    @Test
    @DisplayName("Тест getDevices")
    public void testGetDevicesSuccess() {
        Long id = 1L;
        Devices devices = new Devices();
        devices.setId(id);

        Users user = new Users();
        user.setId(10L);
        user.setLogin("testUser");
        devices.setUser(user);

        DevicesDto expectedDto = new DevicesDto(id, user, null, null);

        when(deviceRepository.findById(id)).thenReturn(Optional.of(devices));
        when(settingMapper.getDto(devices)).thenReturn(expectedDto);

        DevicesDto result = userService.getDevices(id);

        assertNotNull(result);
        assertEquals(expectedDto, result);
        assertEquals(id, result.getId());
        assertEquals(10L, result.getUser().getId());
        assertEquals("testUser", result.getUser().getLogin());
        verify(deviceRepository).findById(id);
        verify(settingMapper).getDto(devices);
    }

    @Test
    @DisplayName("Тест getDevices null")
    public void testGetDevicesNotFound() {
        Long id = 9999L;

        when(deviceRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(java.util.NoSuchElementException.class, () -> userService.getDevices(id));
        verify(deviceRepository).findById(id);
        verify(settingMapper, never()).getDto(any(Devices.class));
    }

    @Test
    @DisplayName("Тест getUser")
    public void testGetUserWithCorrectId() {
        Long id = 100L;
        Users user = new Users();
        user.setId(id);
        user.setLogin("user100");

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(settingMapper.getDto(user)).thenReturn(new UserDto(id, "user100"));

        userService.getUser(id);

        verify(userRepository).findById(id);
        verify(settingMapper).getDto(user);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    @DisplayName("Тест getDevices ")
    public void testGetDevicesWithCorrectId() {
        Long id = 200L;
        Devices devices = new Devices();
        devices.setId(id);
        devices.setUser(new Users());

        when(deviceRepository.findById(id)).thenReturn(Optional.of(devices));
        when(settingMapper.getDto(devices)).thenReturn(new DevicesDto(id, null, null, null));

        userService.getDevices(id);

        verify(deviceRepository).findById(id);
        verify(settingMapper).getDto(devices);
        verifyNoMoreInteractions(deviceRepository);
    }

    @Test
    @DisplayName("Тест getUser")
    public void testGetUserReturnsCorrectDto() {
        Long id = 5L;
        String login = "john_doe";

        Users user = new Users();
        user.setId(id);
        user.setLogin(login);

        UserDto expectedDto = new UserDto(id, login);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(settingMapper.getDto(user)).thenReturn(expectedDto);

        UserDto result = userService.getUser(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(login, result.getLogin());
    }
}