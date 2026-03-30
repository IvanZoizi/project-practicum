package ru.tbank.practicum.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users_bot")
public class BotUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(name="username", nullable = false)
    private String username;

    @Column(name="full_name", nullable = false)
    private String full_name;
}
