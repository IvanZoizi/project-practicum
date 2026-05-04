package ru.tbank.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tbank.practicum.entity.LoggingBlind;

import java.util.stream.Stream;

@Repository
public interface BlindLoggingRepository extends JpaRepository<LoggingBlind, Long> {
    Stream<LoggingBlind> findAllByBlinds_IdAndBlinds_User_Id(Long id, Long userId);
}
