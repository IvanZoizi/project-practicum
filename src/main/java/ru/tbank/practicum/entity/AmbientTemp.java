package ru.tbank.practicum.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Table(name = "ambient_temp")
@Entity
public class AmbientTemp {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "time", nullable = false)
    private LocalDateTime timeStart;

    @Column(name = "temp", nullable = false)
    private Double temp;
}
