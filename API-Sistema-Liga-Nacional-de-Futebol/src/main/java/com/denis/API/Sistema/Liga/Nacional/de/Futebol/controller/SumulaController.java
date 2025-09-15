package com.denis.API.Sistema.Liga.Nacional.de.Futebol.controller;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.SumulaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.*;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.service.SumulaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sumula")
public class SumulaController {

    private SumulaService sumulaService;

    public SumulaController(SumulaService sumulaService) {
        this.sumulaService = sumulaService;
    }

    @PostMapping("/cadastrar-sumula")
    public ResponseEntity<Void> cadastrarSumula(@RequestBody SumulaRequest dto){
        sumulaService.cadastrarSumula(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
