package ru.tbank.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tbank.practicum.entity.WindowBlindSettings;

import java.util.Optional;

public interface WindowBlindSettingsRepository extends JpaRepository<WindowBlindSettings, Long>  {
    Optional<WindowBlindSettings> findById(Long id);
}
