package ru.tbank.practicum.controller;

import io.micrometer.core.instrument.Counter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tbank.practicum.dto.*;
import ru.tbank.practicum.security.jwt.JwtService;
import ru.tbank.practicum.service.BatteryService;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("battery")
@Tag(name="battery")
public class BatteryController {

    private BatteryService batteryService;

    private JwtService jwtService;
    private Counter counterRequests;

    @PostMapping
    public ResponseEntity<BatteryResponse> createNewBattery(@RequestHeader("Authorization") String authHeader,
                                                            @RequestBody BatteryRequest batteryRequest) {
        counterRequests.increment();
        try {
            String jwtToken = jwtService.getJwtToken(authHeader);
            BatteryResponse batteryResponse = batteryService.newBattery(jwtToken, batteryRequest);
            return ResponseEntity.ok(batteryResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<BatteryResponse>> getAllBattery(@RequestHeader("Authorization") String authHeader) {
        counterRequests.increment();
        try {
            String jwtToken = jwtService.getJwtToken(authHeader);
            return ResponseEntity.ok(batteryService.getAllBatteries(jwtToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BatteryResponse> getBattery(@RequestHeader("Authorization") String authHeader,
                                                      @PathVariable("id") Long id) {
        counterRequests.increment();
        try {
            String jwtToken = jwtService.getJwtToken(authHeader);
            return ResponseEntity.ok(batteryService.getBatteryById(jwtToken, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/temp/{id}")
    public ResponseEntity<Long> getTempBattery(@RequestHeader("Authorization") String authHeader,
                                                      @PathVariable("id") Long id) {
        counterRequests.increment();
        try {
            String jwtToken = jwtService.getJwtToken(authHeader);
            return ResponseEntity.ok(batteryService.getTempNow(jwtToken, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BatteryResponse> updateTempBattery(@RequestHeader("Authorization") String authHeader,
                                                             @PathVariable("id") Long id,
                                                             @RequestBody TempSettingRequest tempSettingRequest) {
        counterRequests.increment();
        try {
            String jwtToken = jwtService.getJwtToken(authHeader);
            return ResponseEntity.ok(batteryService.updateTempSetting(jwtToken, id, tempSettingRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/history/{id}")
    public ResponseEntity<List<LoggingBatteryDto>> getHistory(@RequestHeader("Authorization") String authHeader,
                                                              @PathVariable("id") Long id ) {
        counterRequests.increment();
        try {
            String jwtToken = jwtService.getJwtToken(authHeader);
            return ResponseEntity.ok(batteryService.getHistoryKey(jwtToken, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
