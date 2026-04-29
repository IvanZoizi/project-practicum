package ru.tbank.practicum.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.LinkOption;

@Data
@Entity
@NoArgsConstructor
@Table(name = "battery_setting")
public class BatteriesSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "battery_id")
    private Batteries batteries;

    @Column(name = "temp_off", nullable = false)
    private Long tempOff;
    @Column(name = "temp_on", nullable = false)
    private Long tempOn;
    @Column(name = "temp_set", nullable = false)
    private Long tempSet;

}

