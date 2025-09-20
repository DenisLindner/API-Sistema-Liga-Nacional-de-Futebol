package com.denis.API.Sistema.Liga.Nacional.de.Futebol.controller;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.*;
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
    public ResponseEntity<List<TimeResponseTabela>> mostrarTabelaTemporada(@RequestParam Long idTemporada){
        return ResponseEntity.status(HttpStatus.OK).body(temporadaService.mostrarTabelaTemporada(idTemporada));
    }

    @GetMapping("/top-5-gols")
    public ResponseEntity<List<EstatisticaTemporadaAtletaResponseGols>> buscarTop5GolsTemporada(@RequestParam Long idTemporada){
        return ResponseEntity.status(HttpStatus.OK).body(estatisticaTemporadaAtletaService.buscarTop5GolsTemporada(idTemporada));
    }

    @GetMapping("/top-5-amarelos")
    public ResponseEntity<List<EstatisticaTemporadaAtletaResponseAmarelos>> buscarTop5AmarelosTemporada(@RequestParam Long idTemporada){
        return ResponseEntity.status(HttpStatus.OK).body(estatisticaTemporadaAtletaService.buscarTop5AmarelosTemporada(idTemporada));
    }

    @GetMapping("/top-5-vermelhos")
    public ResponseEntity<List<EstatisticaTemporadaAtletaResponseVermelhos>> buscarTop5VermelhosTemporada(@RequestParam Long idTemporada){
        return ResponseEntity.status(HttpStatus.OK).body(estatisticaTemporadaAtletaService.buscarTop5VermelhosTemporada(idTemporada));
    }
}
