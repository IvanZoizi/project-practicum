package ru.tbank.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tbank.practicum.entity.Blinds;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface BlindRepository extends JpaRepository<Blinds, Long> {
    Optional<Blinds> findByUser_IdAndId(Long userId, Long id);
    Stream<Blinds> findAllByUser_Id(Long id);
}
