package ru.tbank.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tbank.practicum.entity.AmbientTemp;
import ru.tbank.practicum.entity.BatteryTemp;

public interface AmbientTempRepository extends JpaRepository<AmbientTemp, Long> {

}
