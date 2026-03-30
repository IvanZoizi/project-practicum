package ru.tbank.practicum.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "battery_settings")
@Entity
public class BatterySettings {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="minTemp_id", referencedColumnName = "id")
    private BatteryTemp minTemp;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="maxTemp_id", referencedColumnName = "id")
    private BatteryTemp maxTemp;

}
