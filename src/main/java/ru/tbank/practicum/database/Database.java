package ru.tbank.practicum.database;

import ru.tbank.practicum.entity.ActionCurtainsEntity;
import ru.tbank.practicum.entity.StatusEntity;
import ru.tbank.practicum.entity.TemperatureEntity;
import ru.tbank.practicum.service.enums.ActionCurtainsEnum;
import ru.tbank.practicum.service.enums.StatusWindowBlindEnum;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

@Component
public class Database {
    private List<TemperatureEntity> temperatureEntityList = new ArrayList<>();
    private List<StatusEntity> statusEntityList = new ArrayList<>();
    private List<ActionCurtainsEntity> actionCurtainsEntities = new ArrayList<>();

    public Long generateLongUID() {
        UUID uuid = UUID.randomUUID();
        return -uuid.getLeastSignificantBits() / 10000000;
    }

    public TemperatureEntity getTemperature(long id) {
        for (TemperatureEntity temperatureEntity : temperatureEntityList) {
            if (temperatureEntity.getId() == id) {
                return temperatureEntity;
            }
        }
        return null;
    }

    public void deleteTemperature(long id) {
        for (TemperatureEntity temperatureEntity : temperatureEntityList) {
            if (temperatureEntity.getId() == id) {
                temperatureEntityList.remove(
                        temperatureEntity
                );
                break;
            }
        }
    }

    public TemperatureEntity newTemperature(int temperature) {
        TemperatureEntity temperatureEntity = new TemperatureEntity(
                generateLongUID(),
                temperature
        );
        temperatureEntityList.add(temperatureEntity);
        return temperatureEntity;
    }

    public StatusEntity getStatus(long id) {
        for (StatusEntity statusEntity : statusEntityList) {
            if (statusEntity.getId() == id) {
                return statusEntity;
            }
        }
        return null;
    }

    public void deleteStatus(long id) {
        for (StatusEntity statusEntity : statusEntityList) {
            if (statusEntity.getId() == id) {
                statusEntityList.remove(
                        statusEntity
                );
                break;
            }
        }
    }

    public StatusEntity newStatus(StatusWindowBlindEnum status) {
        StatusEntity statusEntity = new StatusEntity(
                generateLongUID(),
                status
        );
        statusEntityList.add(statusEntity);
        return statusEntity;
    }

    public ActionCurtainsEntity getAction(long id) {
        for (ActionCurtainsEntity actionCurtainsEntity : actionCurtainsEntities) {
            if (actionCurtainsEntity.getId() == id) {
                return actionCurtainsEntity;
            }
        }
        return null;
    }

    public void deleteAction(long id) {
        for (ActionCurtainsEntity actionCurtainsEntity : actionCurtainsEntities) {
            if (actionCurtainsEntity.getId() == id) {
                actionCurtainsEntities.remove(
                        actionCurtainsEntity
                );
                break;
            }
        }
    }

    public ActionCurtainsEntity newAction(LocalTime time, ActionCurtainsEnum actionCurtainsEnum) {
        ActionCurtainsEntity actionCurtainsEntity = new ActionCurtainsEntity(
                generateLongUID(),
                time,
                actionCurtainsEnum
        );
        actionCurtainsEntities.add(actionCurtainsEntity);
        return actionCurtainsEntity;
    }

}
