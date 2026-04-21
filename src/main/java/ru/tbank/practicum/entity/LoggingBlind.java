package ru.tbank.practicum.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "logging_blind")
public class LoggingBlind {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blind_id", referencedColumnName = "id")
    private Blinds blinds;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Column(name = "new_status", nullable = false)
    private String newStatus;
}
