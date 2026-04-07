package ru.tbank.practicum.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "logging_battery_settings")
@Entity
public class LoggingBatterySettings {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private Users user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ambient_temp_id", referencedColumnName = "id")
    private AmbientTemp temp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "devices_id", referencedColumnName = "id")
    private Devices device;

}
