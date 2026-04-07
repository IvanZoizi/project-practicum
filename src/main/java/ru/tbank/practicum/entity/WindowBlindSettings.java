package ru.tbank.practicum.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "window_blind_settings")
@Entity
public class WindowBlindSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="openAction_id", referencedColumnName = "id")
    private WindowBlindAction openAction;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="closeAction_id", referencedColumnName = "id")
    private WindowBlindAction closeAction;
}
