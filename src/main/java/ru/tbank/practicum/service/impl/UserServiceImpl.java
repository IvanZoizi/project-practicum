package ru.tbank.practicum.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tbank.practicum.dto.*;
import ru.tbank.practicum.entity.Users;
import ru.tbank.practicum.entity.UsersCoords;
import ru.tbank.practicum.mapper.SettingMapper;
import ru.tbank.practicum.repository.UsersCoordsRepository;
import ru.tbank.practicum.repository.UsersRepository;
import ru.tbank.practicum.security.dto.JwtAutorizeToken;
import ru.tbank.practicum.security.jwt.JwtService;
import ru.tbank.practicum.service.UserService;

import java.util.Optional;
import javax.naming.AuthenticationException;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final UsersCoordsRepository usersCoordsRepository;
    private final SettingMapper settingMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final KafkaTemplate<String, String> kafkaTemplate;

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
        kafkaTemplate.send("test-topic", "Аунтефикация пользователя");
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

    public Users getUserByToken(String token) {
        String login = jwtService.getLoginFromToken(token);
        Optional<Users> user = usersRepository.findByLogin(login);

        if (user.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return user.get();
    }

    @Override
    public CoordsResponseDto newUserCoords(String jwtToken, CoordsRequestDto coordsRequestDto) {

        Users user = getUserByToken(jwtToken);
        Optional<UsersCoords> usersCoords = usersCoordsRepository.findByUserId_Id(user.getId());
        if (usersCoords.isEmpty()) {
            UsersCoords newUserCoords = new UsersCoords();
            newUserCoords.setUserId(user);
            newUserCoords.setLat(coordsRequestDto.getLat());
            newUserCoords.setLon(coordsRequestDto.getLon());
            return settingMapper.getDto(usersCoordsRepository.save(newUserCoords));
        }

        UsersCoords coords = usersCoords.get();
        coords.setLon(coordsRequestDto.getLon());
        coords.setLat(coordsRequestDto.getLat());
        return settingMapper.getDto(coords);

    }

    @Override
    public CoordsResponseDto getUserCoords(String jwtToken) {
        Users user = getUserByToken(jwtToken);

        Optional<UsersCoords> usersCoords = usersCoordsRepository.findByUserId_Id(user.getId());
        if (usersCoords.isEmpty()) {
            return settingMapper.getDto(new UsersCoords());
        }

        return settingMapper.getDto(usersCoords.get());
    }
}
