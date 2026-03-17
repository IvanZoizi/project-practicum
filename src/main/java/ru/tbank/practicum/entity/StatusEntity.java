package ru.tbank.practicum.entity;

import ru.tbank.practicum.service.enums.StatusWindowBlindEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusEntity implements Entity{
    public long id;
    public StatusWindowBlindEnum status;

    public long getId() {
        return id;
    }
}
