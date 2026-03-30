package ru.tbank.practicum.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.tbank.practicum.entity.BatteryTemp;

import java.util.Optional;

public interface BatteryTempRepository extends JpaRepository<BatteryTemp, Long> {
    Optional<BatteryTemp> findById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE BatteryTemp bt SET bt.temp = :temp, bt.setTemp = :setTemp WHERE bt.id = :id")
    int updateTempById(@Param("id") Long id, @Param("temp") Long temp, @Param("setTemp") Long setTemp);
}
