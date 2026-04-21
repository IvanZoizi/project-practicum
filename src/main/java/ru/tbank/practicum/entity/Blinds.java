package ru.tbank.practicum.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "blinds")
public class Blinds {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private Users user;

    @Column(name = "room", nullable = false)
    private String room;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "time_open", nullable = false)
    private LocalTime timeOpen;

    @Column(name = "time_close", nullable = false)
    private LocalTime timeClose;
}
