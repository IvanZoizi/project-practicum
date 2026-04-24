package ru.tbank.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tbank.practicum.entity.UsersCoords;

import java.util.Optional;

public interface UsersCoordsRepository extends JpaRepository<UsersCoords, Long> {
    Optional<UsersCoords> findByUserId_Id(Long id);
}
