package ru.tbank.practicum.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "battery_temp")
@Entity
public class BatteryTemp {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "temp", nullable = false)
    private Long temp;

    @Column(name = "set_temp", nullable = false)
    private Long setTemp;
}
