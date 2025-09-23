package com.denis.API.Sistema.Liga.Nacional.de.Futebol.controller;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.CampeonatoRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.CampeonatoResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.service.CampeonatoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/campeonato")
public class CampeonatoController {

    private CampeonatoService campeonatoService;

    public CampeonatoController(CampeonatoService campeonatoService) {this.campeonatoService = campeonatoService;}

    @PostMapping("/cadastrar-campeonato")
    public ResponseEntity<CampeonatoResponse> cadastrarCampeonato(@RequestBody CampeonatoRequest dto){
        CampeonatoResponse campeonato = campeonatoService.cadastrarCampeonato(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(campeonato);
    }

    @DeleteMapping("/deletar-campeonato")
    public ResponseEntity<Void> deletarCampeonato(@RequestParam Long idCampeonato){
        campeonatoService.deletarCampeonato(idCampeonato);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
