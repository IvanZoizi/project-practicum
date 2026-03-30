package ru.tbank.practicum.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "devices")
@Entity
public class Devices {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private Users user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "battery_settings_id", referencedColumnName = "id")
    private BatterySettings battery;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "window_blind_settings_id", referencedColumnName = "id")
    private WindowBlindSettings windowBlind;

}
