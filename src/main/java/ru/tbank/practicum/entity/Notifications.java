package ru.tbank.practicum.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bot_user_id", referencedColumnName = "id")
    private BotUser userBot;

    @Column(name="message", nullable = false)
    private String message;
}
