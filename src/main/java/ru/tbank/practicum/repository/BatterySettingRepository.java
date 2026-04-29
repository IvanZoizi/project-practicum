package ru.tbank.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tbank.practicum.entity.BatteriesSetting;

@Repository
public interface BatterySettingRepository extends JpaRepository<BatteriesSetting, Long> {
}
