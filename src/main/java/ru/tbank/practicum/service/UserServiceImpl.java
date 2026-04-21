package ru.tbank.practicum.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.tbank.practicum.controller.dto.RegisterDto;
import ru.tbank.practicum.entity.Users;
import ru.tbank.practicum.mapper.SettingMapper;
import ru.tbank.practicum.repository.UsersRepository;
import ru.tbank.practicum.security.CustomUserDetail;
import ru.tbank.practicum.security.dto.JwtAutorizeToken;
import ru.tbank.practicum.security.jwt.JwtService;

import java.util.Optional;
import javax.naming.AuthenticationException;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final SettingMapper settingMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String register(RegisterDto registerDto) {
        Users user = new Users();
        user.setLogin(registerDto.getLogin());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        usersRepository.save(user);
        return "User add";
    }

    @Override
    public JwtAutorizeToken singIn(RegisterDto registerDto) throws AuthenticationException {
        Users user = findByRegisterDto(registerDto);
        log.info("User: " + user);
        return jwtService.generateAuthToken(user.getLogin());

    }

    private Users findByRegisterDto(RegisterDto registerDto) throws AuthenticationException {
        Optional<Users> usersOptional = usersRepository.findByLogin(registerDto.getLogin());
        log.info("UsersOptional: " + usersOptional);
        if (usersOptional.isPresent()) {
            Users user = usersOptional.get();
            log.info("User: " + user);
            if (passwordEncoder.matches(registerDto.getPassword(), user.getPassword())){
                return user;
            }
        }
        throw new AuthenticationException("Email or password is not correct");
    }


}
