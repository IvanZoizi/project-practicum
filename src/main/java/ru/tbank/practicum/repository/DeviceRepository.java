package ru.tbank.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tbank.practicum.entity.Devices;
import ru.tbank.practicum.entity.Users;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Devices, Long> {
    Optional<Devices> findById(Long id);
}
