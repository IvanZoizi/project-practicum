package ru.tbank.practicum.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tbank.practicum.dto.KeyRequestDto;
import ru.tbank.practicum.dto.KeyResponseDto;
import ru.tbank.practicum.dto.enums.Status;
import ru.tbank.practicum.security.jwt.JwtFilter;
import ru.tbank.practicum.security.jwt.JwtService;
import ru.tbank.practicum.service.KeyService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/keys")
@AllArgsConstructor
@Tag(name="keys")
public class KeysController {

    private KeyService keyService;
    private JwtService jwtService;

    @PostMapping
    public ResponseEntity<KeyResponseDto> createKey(@RequestHeader("Authorization") String authHeader,
                                                    @RequestBody KeyRequestDto keyRequestDto) {
        try {

            log.info("New request, token: " + authHeader);

            String jwtToken = jwtService.getJwtToken(authHeader);

            return ResponseEntity.ok(keyService.createNewKey(jwtToken, keyRequestDto));


        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<KeyResponseDto>> getAllKey(@RequestHeader("Authorization") String authHeader) {
        try {
            log.info("New request, token: " + authHeader);
            String jwtToken = jwtService.getJwtToken(authHeader);
            return ResponseEntity.ok(keyService.getMyKeys(jwtToken));
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<KeyResponseDto> getMyKey(@RequestHeader("Authorization") String authHeader,
                                                   @PathVariable("id") Long id) {
        try {
            log.info("New request, token: " + authHeader);
            String jwtToken = jwtService.getJwtToken(authHeader);
            return ResponseEntity.ok(keyService.getKey(jwtToken, id));
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<Status> getMyStatusKey(@RequestHeader("Authorization") String authHeader,
                                                 @PathVariable("id") Long id) {
        try {
            log.info("New request, token: " + authHeader);
            String jwtToken = jwtService.getJwtToken(authHeader);
            return ResponseEntity.ok(keyService.getStatusKey(jwtToken, id));
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
