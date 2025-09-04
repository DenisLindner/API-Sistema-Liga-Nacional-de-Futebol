package com.denis.API.Sistema.Liga.Nacional.de.Futebol.controller;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.AtletaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.AtletaResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Atleta;
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

    @PostMapping
    public ResponseEntity<AtletaResponse> cadastrarAtleta(@RequestBody AtletaRequest dto){
        Atleta atleta = atletaService.cadastrarAtleta(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AtletaResponse(atleta.getId(), atleta.getNome(), atleta.getCpf(), atleta.getDataNascimento(), atleta.getDataContratacao(), atleta.getDataFinalContratacao(), atleta.getQuantidadePartidas(), atleta.getCartoesAmarelos(), atleta.getCartoesVermelhos(), atleta.getQuantidadeGols(), atleta.getTime().getNome()));
    }

    @PostMapping("/atletas")
    public ResponseEntity<Void> cadastrarListaAtletas(@RequestBody List<AtletaRequest> dto){
        for (AtletaRequest atleta : dto) {
            atletaService.cadastrarAtleta(atleta);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
