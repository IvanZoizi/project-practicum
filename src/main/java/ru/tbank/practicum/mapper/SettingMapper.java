package ru.tbank.practicum.mapper;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.tbank.practicum.entity.Users;
import ru.tbank.practicum.security.CustomUserDetail;

import java.util.Optional;


@Component
public class SettingMapper {
    public CustomUserDetail getDto(Optional<Users> user, String login) {
        return user.map(CustomUserDetail::new)
                .orElseThrow(() -> new UsernameNotFoundException(login));
    }
}