package ru.tbank.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.tbank.practicum.entity.ActionStatus;
import ru.tbank.practicum.entity.BatterySettings;
import ru.tbank.practicum.entity.WindowBlindAction;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

public interface WindowBlindActionRepository extends JpaRepository<WindowBlindAction, Long>  {
    Optional<WindowBlindAction> findById(Long id);


    @Modifying
    @Transactional
    @Query("UPDATE WindowBlindAction SET timeStart = :timeStart, timeEnd = :timeEnd, status = :status WHERE id = :id")
    int updateWindowById(@Param("id") Long id, @Param("timeStart") LocalDateTime timeStart,
                         @Param("timeEnd") LocalDateTime timeEnd, @Param("status")ActionStatus status);
}
