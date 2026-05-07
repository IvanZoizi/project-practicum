package ru.tbank.practicum.controller;

import io.micrometer.core.instrument.Counter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tbank.practicum.dto.BlindCreateDto;
import ru.tbank.practicum.dto.BlindResponseDto;
import ru.tbank.practicum.dto.LoggingBlindDto;
import ru.tbank.practicum.dto.enums.BlindUpdateTimeDto;
import ru.tbank.practicum.dto.enums.Status;
import ru.tbank.practicum.security.jwt.JwtService;
import ru.tbank.practicum.service.BlindService;

import java.util.List;

@RestController
@Tag(name = "blind")
@Slf4j
@AllArgsConstructor
@RequestMapping("blind")
public class BlindController {
    private BlindService blindService;
    private JwtService jwtService;
    private Counter counterRequests;

    @PostMapping
    public ResponseEntity<BlindResponseDto> createBlindUser(@RequestHeader("Authorization") String authHeader,
                                                            @RequestBody BlindCreateDto blindCreateDto) {
        counterRequests.increment();
        try {
            String jwtToken = jwtService.getJwtToken(authHeader);
            return ResponseEntity.ok(blindService.newBlind(jwtToken, blindCreateDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlindResponseDto> getBlindUser(@RequestHeader("Authorization") String authHeader,
                                                         @PathVariable("id") Long id)  {
        counterRequests.increment();
        try {
            String jwtToken = jwtService.getJwtToken(authHeader);
            return ResponseEntity.ok(blindService.getBlind(jwtToken, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<Status> getStatusBlindUser(@RequestHeader("Authorization") String authHeader,
                                                     @PathVariable("id") Long id)  {
        counterRequests.increment();
        try {
            String jwtToken = jwtService.getJwtToken(authHeader);
            return ResponseEntity.ok(blindService.getStatusBlind(jwtToken, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<BlindResponseDto>> getAllBlindUser(@RequestHeader("Authorization") String authHeader)  {
        counterRequests.increment();
        try {
            String jwtToken = jwtService.getJwtToken(authHeader);
            return ResponseEntity.ok(blindService.getBlinds(jwtToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/time/open")
    public ResponseEntity<BlindResponseDto> updateTimeOpen(@RequestHeader("Authorization") String authHeader,
                                                            @RequestBody BlindUpdateTimeDto blindUpdateTimeDto) {
        counterRequests.increment();
        try {
            String jwtToken = jwtService.getJwtToken(authHeader);
            return ResponseEntity.ok(blindService.updateTimeOpenBlind(jwtToken, blindUpdateTimeDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/time/close")
    public ResponseEntity<BlindResponseDto> updateTimeClose(@RequestHeader("Authorization") String authHeader,
                                                            @RequestBody BlindUpdateTimeDto blindUpdateTimeDto) {
        counterRequests.increment();
        try {
            String jwtToken = jwtService.getJwtToken(authHeader);
            return ResponseEntity.ok(blindService.updateTimeCloseBlind(jwtToken, blindUpdateTimeDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/history/{id}")
    public ResponseEntity<List<LoggingBlindDto>> getHistory(@RequestHeader("Authorization") String authHeader,
                                                            @PathVariable("id") Long id ) {
        counterRequests.increment();
        try {
            String jwtToken = jwtService.getJwtToken(authHeader);
            return ResponseEntity.ok(blindService.getHistoryBlind(jwtToken, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
