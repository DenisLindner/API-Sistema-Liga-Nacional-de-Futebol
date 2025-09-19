package com.denis.API.Sistema.Liga.Nacional.de.Futebol.controller;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.AtletaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.AtletaResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.service.AtletaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atleta")
public class AtletaController {

    private AtletaService atletaService;

    public AtletaController(AtletaService atletaService) {
        this.atletaService = atletaService;
    }

    @PostMapping("/cadastrar-atleta")
    public ResponseEntity<AtletaResponse> cadastrarAtleta(@RequestBody AtletaRequest dto){
        AtletaResponse atleta = atletaService.cadastrarAtleta(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(atleta);
    }

    @PostMapping("/cadastrar-atletas")
    public ResponseEntity<Void> cadastrarListaAtletas(@RequestBody List<AtletaRequest> dto){
        for (AtletaRequest atleta : dto) {
            atletaService.cadastrarAtleta(atleta);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
