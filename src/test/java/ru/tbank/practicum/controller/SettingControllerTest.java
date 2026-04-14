package ru.tbank.practicum.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.tbank.practicum.controller.dto.StatusWindowBlindDto;
import ru.tbank.practicum.controller.dto.TemperatureDto;
import ru.tbank.practicum.controller.dto.WindowActionDto;
import ru.tbank.practicum.entity.ActionStatus;
import ru.tbank.practicum.entity.BatteryTemp;
import ru.tbank.practicum.entity.WindowBlindAction;
import ru.tbank.practicum.mapper.SettingMapper;
import ru.tbank.practicum.service.SettingServiceImpl;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest(SettingController.class)
public class SettingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean  // вместо @Autowired
    private SettingMapper settingMapper;

    @MockitoBean  // вместо @Mock
    private SettingServiceImpl settingService;

    @Test
    @DisplayName("Тест /api/v1/temperature/{id}")
    public void testGetTemperatureByController() throws Exception {
        BatteryTemp minTemp = new BatteryTemp();
        BatteryTemp maxTemp = new BatteryTemp();

        TemperatureDto temperatureDto = new TemperatureDto(1L, minTemp, maxTemp);

        when(settingService.getTemperature(1L)).thenReturn(temperatureDto);

        mockMvc.perform(get("/api/v1/temperature/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.minTemp").isNotEmpty())
                .andExpect(jsonPath("$.maxTemp").isNotEmpty());
    }

    @Test
    @DisplayName("Тест /api/v1/status/window/{id}")
    public void testGetTemperatureByControllerNull() throws Exception {
        BatteryTemp minTemp = new BatteryTemp();
        BatteryTemp maxTemp = new BatteryTemp();

        TemperatureDto temperatureDto = new TemperatureDto(null, minTemp, maxTemp);

        when(settingService.getTemperature(9999L)).thenReturn(temperatureDto);

        mockMvc.perform(get("/api/v1/temperature/9999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isEmpty())
                .andExpect(jsonPath("$.minTemp").isNotEmpty())
                .andExpect(jsonPath("$.maxTemp").isNotEmpty());
    }

    @Test
    @DisplayName("Тест /api/v1/status/window/{id}")
    public void testGetStatusByController() throws Exception {

        LocalDateTime testTime = LocalDateTime.now();

        WindowBlindAction openAction = new WindowBlindAction();

        openAction.setId(1L);
        openAction.setStatus(ActionStatus.CANCELLED);
        openAction.setTimeStart(testTime);
        openAction.setTimeEnd(testTime);

        WindowBlindAction closeAction = new WindowBlindAction();

        closeAction.setId(2L);
        closeAction.setStatus(ActionStatus.COMPLETED);
        closeAction.setTimeStart(testTime);
        closeAction.setTimeEnd(testTime);


        StatusWindowBlindDto statusWindowBlindDto = new StatusWindowBlindDto(1L, openAction, closeAction);

        when(settingService.getStatusWindowBlind(1L)).thenReturn(statusWindowBlindDto);

        mockMvc.perform(get("/api/v1/status/window/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.openAction.id").value(1L))
                .andExpect(jsonPath("$.openAction.status").value("CANCELLED"))
                .andExpect(jsonPath("$.closeAction.id").value(2L))
                .andExpect(jsonPath("$.closeAction.status").value("COMPLETED"));
    }

    @Test
    @DisplayName("Тест /api/v1/status/window/{id} (null)")
    public void testGetStatusByControllerNull() throws Exception {

        WindowBlindAction windowBlindAction = new WindowBlindAction();

        StatusWindowBlindDto statusWindowBlindDto = new StatusWindowBlindDto(null, windowBlindAction, windowBlindAction);

        when(settingService.getStatusWindowBlind(9999L)).thenReturn(statusWindowBlindDto);

        mockMvc.perform(get("/api/v1/status/window/9999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isEmpty())
                .andExpect(jsonPath("$.openAction").isNotEmpty())
                .andExpect(jsonPath("$.closeAction").isNotEmpty());
    }
}