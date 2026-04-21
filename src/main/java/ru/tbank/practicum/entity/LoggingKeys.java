package ru.tbank.practicum.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "logging_keys")
public class LoggingKeys {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "key_id", referencedColumnName = "id")
    private Keys keys;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Column(name = "new_status", nullable = false)
    private String newStatus;
}
