package ru.tbank.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tbank.practicum.entity.Batteries;
import ru.tbank.practicum.entity.LoggingBattery;

@Repository
public interface BatteryLoggingRepository extends JpaRepository<LoggingBattery, Long> {
}
