package ru.tbank.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tbank.practicum.entity.LoggingBlind;

@Repository
public interface BlindLoggingRepository extends JpaRepository<LoggingBlind, Long> {
}
