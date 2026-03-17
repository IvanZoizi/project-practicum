package ru.tbank.practicum.repository;

import ru.tbank.practicum.controller.dto.ActionCurtainsDto;
import ru.tbank.practicum.database.Database;
import ru.tbank.practicum.entity.ActionCurtainsEntity;
import ru.tbank.practicum.entity.StatusEntity;
import ru.tbank.practicum.entity.TemperatureEntity;
import ru.tbank.practicum.service.enums.ActionCurtainsEnum;
import ru.tbank.practicum.service.enums.StatusWindowBlindEnum;
import lombok.AllArgsConstructor;
import org.springframework.data.jdbc.core.convert.DataAccessStrategy;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;

@Repository
@AllArgsConstructor
public class SettingRepository {
    private Database database;

    private final DataAccessStrategy dataAccessStrategy;

    public TemperatureEntity getTemperatureEntity(long id) {
        return database.getTemperature(id);
    }

    public TemperatureEntity newTemperature(int temperature) {
        return database.newTemperature(temperature);
    }

    public void deleteTemperature(long id) {
        database.deleteTemperature(id);
    }

    public StatusEntity newStatus(StatusWindowBlindEnum status) {
        return database.newStatus(status);
    }

    public StatusEntity getStatus(long id) {
        return database.getStatus(id);
    }

    public void deleteStatus(long id) {
        database.deleteStatus(id);
    }

    public ActionCurtainsEntity newAction(LocalTime time, ActionCurtainsEnum actionCurtainsEnum) {
        return database.newAction(time, actionCurtainsEnum);
    }

    public ActionCurtainsEntity getAction(long id) {
        return database.getAction(id);
    }

    public void deleteAction(long id) {
        database.deleteAction(id);
    }
}
