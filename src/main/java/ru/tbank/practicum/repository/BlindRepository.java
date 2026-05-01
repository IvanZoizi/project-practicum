package ru.tbank.practicum.repository;

import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tbank.practicum.dto.enums.Status;
import ru.tbank.practicum.entity.Blinds;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface BlindRepository extends JpaRepository<Blinds, Long> {
    Optional<Blinds> findByUser_IdAndId(Long userId, Long id);
    Stream<Blinds> findAllByUser_Id(Long id);
    List<Blinds> findAllByTimeOpenBetweenAndStatus(LocalTime localTimeStart, LocalTime localTimeEnd, Status status);
    List<Blinds> findAllByTimeCloseBetweenAndStatus(LocalTime localTimeStart, LocalTime localTimeEnd, Status status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE blinds SET status = ?2 WHERE id = ?1", nativeQuery = true)
    int updateStatusBlind(Long id, Status status);
}
