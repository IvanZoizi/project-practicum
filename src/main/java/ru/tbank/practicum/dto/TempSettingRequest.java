package ru.tbank.practicum.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TempSettingRequest {
    private Long tempOff;
    private Long tempOn;
    private Long tempSet;
}
