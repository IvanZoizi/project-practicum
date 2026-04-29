package ru.tbank.practicum.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BatteryRequest {
    private String room;
    private Long tempNow;
    private TempSettingRequest tempSettingRequest;
}
