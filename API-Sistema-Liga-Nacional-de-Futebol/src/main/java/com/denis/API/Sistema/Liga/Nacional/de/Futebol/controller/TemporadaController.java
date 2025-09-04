package com.denis.API.Sistema.Liga.Nacional.de.Futebol.controller;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.TemporadaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.TemporadaResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Temporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.service.TemporadaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temporada")
public class TemporadaController {

    private TemporadaService temporadaService;

    public TemporadaController(TemporadaService temporadaService) {
        this.temporadaService = temporadaService;
    }

    @PostMapping
    public ResponseEntity<TemporadaResponse> cadastrarTemporada(@RequestBody TemporadaRequest dto) {
        Temporada temporada = temporadaService.cadastrarTemporada(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TemporadaResponse(temporada.getId(), temporada.isConcluido(), temporada.getNome(), temporada.getDataInicio(), temporada.getDataFim(), temporada.getCampeonato().getNome()));
    }
}
