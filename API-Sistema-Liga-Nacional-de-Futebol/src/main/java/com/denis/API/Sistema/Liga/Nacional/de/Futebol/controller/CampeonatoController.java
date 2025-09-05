package com.denis.API.Sistema.Liga.Nacional.de.Futebol.controller;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.CampeonatoRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.CampeonatoResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.service.CampeonatoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
