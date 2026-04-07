package ru.tbank.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tbank.practicum.entity.BatterySettings;
import ru.tbank.practicum.entity.BatteryTemp;
import ru.tbank.practicum.entity.WindowBlindSettings;

import java.util.Optional;

public interface BatterySettingsRepository extends JpaRepository<BatterySettings, Long> {
    Optional<BatterySettings> findById(Long id);
}
