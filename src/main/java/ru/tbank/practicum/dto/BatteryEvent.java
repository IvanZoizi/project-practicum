package ru.tbank.practicum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatteryEvent {
    private Long id;
    private Long tempSet;
}
