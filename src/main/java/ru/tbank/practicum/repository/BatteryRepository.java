package ru.tbank.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tbank.practicum.entity.Batteries;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Stream;

@Repository
public interface BatteryRepository extends JpaRepository<Batteries, Long> {
    Stream<Batteries> findAllByUser_Id(Long id);
    Optional<Batteries> findByUser_IdAndId(Long userId, Long id);
}
