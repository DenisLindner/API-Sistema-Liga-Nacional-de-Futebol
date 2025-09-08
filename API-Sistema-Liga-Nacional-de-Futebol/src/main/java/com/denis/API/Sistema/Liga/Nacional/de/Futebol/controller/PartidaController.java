package com.denis.API.Sistema.Liga.Nacional.de.Futebol.controller;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.PartidaResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.service.PartidaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/partida")
public class PartidaController {

    private PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @GetMapping("/listar-rodadas-temporada")
    public ResponseEntity<List<PartidaResponse>> listarPartidaPorRodadaECampeonato(@RequestParam Long idTemporada, @RequestParam int rodada) {
        List<PartidaResponse> partidas = partidaService.listarPartidaPorRodadaECampeonato(idTemporada, rodada);
        return ResponseEntity.status(HttpStatus.CREATED).body(partidas);
    }
}
