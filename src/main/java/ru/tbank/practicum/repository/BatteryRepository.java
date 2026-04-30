package ru.tbank.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tbank.practicum.entity.Batteries;

import java.beans.Transient;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Stream;

@Repository
public interface BatteryRepository extends JpaRepository<Batteries, Long> {
    Stream<Batteries> findAllByUser_Id(Long id);
    Optional<Batteries> findByUser_IdAndId(Long userId, Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE batteries SET temp_now = ?2 WHERE id = ?1", nativeQuery = true)
    int updateTempBattery(Long id, Long temp);

    @Query(value = "SELECT b.* FROM batteries b " +
            "JOIN battery_setting bs ON bs.battery_id = b.id " +
            "WHERE b.users_id = ?1 AND bs.temp_off >= ?2",
            nativeQuery = true)
    Optional<Batteries> findByTempOff(Long id, Long temp);

    @Query(value = "SELECT b.* FROM batteries b " +
            "JOIN battery_setting bs ON bs.battery_id = b.id " +
            "WHERE b.users_id = ?1 AND bs.temp_on <= ?2",
            nativeQuery = true)
    Optional<Batteries> findByTempOn(Long id, Long temp);
}