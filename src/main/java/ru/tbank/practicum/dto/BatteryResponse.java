package ru.tbank.practicum.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BatteryResponse {
    private Long id;
    private String room;
    private Long tempNow;
    private TempSettingRequest tempSettingRequest;
}
