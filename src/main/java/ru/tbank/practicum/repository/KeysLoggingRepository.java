package ru.tbank.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tbank.practicum.entity.LoggingKeys;

import java.util.stream.Stream;

@Repository
public interface KeysLoggingRepository extends JpaRepository<LoggingKeys, Long> {
    Stream<LoggingKeys> findAllByKeys_IdAndKeys_User_Id(Long id, Long userId);
}
