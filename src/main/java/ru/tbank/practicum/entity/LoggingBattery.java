package ru.tbank.practicum.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "logging_battery")
public class LoggingBattery {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "battery_id", referencedColumnName = "id")
    private Batteries batteries;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Column(name = "temp", nullable = false)
    private Long temp;
}
