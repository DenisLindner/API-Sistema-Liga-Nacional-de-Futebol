package com.denis.API.Sistema.Liga.Nacional.de.Futebol.controller;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.TimeRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.TimeResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Time;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.service.TimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/time")
public class TimeController {

    private TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @PostMapping
    public ResponseEntity<TimeResponse> cadastrarTime(@RequestBody TimeRequest dto) {
        TimeResponse time = timeService.cadastrarTime(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(time);
    }
}
