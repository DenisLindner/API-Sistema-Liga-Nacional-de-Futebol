package com.denis.API.Sistema.Liga.Nacional.de.Futebol.controller;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.TimeRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.TimeResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.service.TimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time")
public class TimeController {

    private TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @PostMapping("/cadastrar-time")
    public ResponseEntity<TimeResponse> cadastrarTime(@RequestBody TimeRequest dto) {
        TimeResponse time = timeService.cadastrarTime(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(time);
    }

    @PostMapping("/cadastrar-times")
    public ResponseEntity<Void> cadastrarListaTimes(@RequestBody List<TimeRequest> dto){
        for (TimeRequest time : dto) {
            timeService.cadastrarTime(time);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/deletar-time")
    public ResponseEntity<Void> deletarTimePorId(@RequestParam Long id){
        timeService.deletarTimePorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
