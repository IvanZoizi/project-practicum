package ru.tbank.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.tbank.practicum.entity.Keys;
import ru.tbank.practicum.entity.Users;

import java.util.Optional;
import java.util.stream.Stream;

public interface KeyRepository extends JpaRepository<Keys, Long> {
    Stream<Keys> findAllByUser_Id(Long id);
    Optional<Keys> findByUser_IdAndId(Long userId, Long Id);
}
