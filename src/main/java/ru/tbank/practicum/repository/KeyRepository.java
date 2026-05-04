package ru.tbank.practicum.repository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tbank.practicum.dto.enums.Status;
import ru.tbank.practicum.entity.Keys;
import ru.tbank.practicum.entity.Users;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface KeyRepository extends JpaRepository<Keys, Long> {
    Stream<Keys> findAllByUser_Id(Long id);
    Optional<Keys> findByUser_IdAndId(Long userId, Long Id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE keys SET status = $2 WHERE id = $1", nativeQuery = true)
    int updateStatusKey(Long id, Status status);
}
