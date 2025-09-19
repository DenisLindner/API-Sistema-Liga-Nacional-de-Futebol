package com.denis.API.Sistema.Liga.Nacional.de.Futebol.controller;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.EstatisticaTemporadaAtletaResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.TemporadaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.TemporadaResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.TimeResponseTabela;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.service.EstatisticaTemporadaAtletaService;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.service.TemporadaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/temporada")
public class TemporadaController {

    private TemporadaService temporadaService;
    private EstatisticaTemporadaAtletaService  estatisticaTemporadaAtletaService;

    public TemporadaController(TemporadaService temporadaService, EstatisticaTemporadaAtletaService estatisticaTemporadaAtletaService) {
        this.temporadaService = temporadaService;
        this.estatisticaTemporadaAtletaService = estatisticaTemporadaAtletaService;
    }

    @PostMapping("/cadastrar-temporada")
    public ResponseEntity<TemporadaResponse> cadastrarTemporada(@RequestBody TemporadaRequest dto) {
        TemporadaResponse temporada = temporadaService.cadastrarTemporada(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(temporada);
    }

    @GetMapping("/tabela-atualizada-temporada")
    public List<TimeResponseTabela> mostrarTabelaTemporada(@RequestParam Long idTemporada){
        return temporadaService.mostrarTabelaTemporada(idTemporada);
    }

    @GetMapping("/top-5-artilheiros")
    public List<EstatisticaTemporadaAtletaResponse> buscarTop5GolsTemporada(@RequestParam Long idTemporada){
        return estatisticaTemporadaAtletaService.buscarTop5GolsTemporada(idTemporada);
    }
}
