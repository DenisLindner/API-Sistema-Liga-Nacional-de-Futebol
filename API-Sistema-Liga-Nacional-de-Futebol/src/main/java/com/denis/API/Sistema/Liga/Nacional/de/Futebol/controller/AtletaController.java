package com.denis.API.Sistema.Liga.Nacional.de.Futebol.controller;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.AtletaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.AtletaResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Atleta;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.service.AtletaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(new AtletaResponse(atleta));
    }

}
