package com.denis.API.Sistema.Liga.Nacional.de.Futebol.controller;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.CredenciamentoRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.CredenciamentoResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.service.CredenciamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credenciamento")
public class CredenciamentoController {

    private CredenciamentoService credenciamentoService;

    public CredenciamentoController(CredenciamentoService credenciamentoService) {
        this.credenciamentoService = credenciamentoService;
    }

    @PostMapping("/cadastrar-credenciamento")
    public ResponseEntity<CredenciamentoResponse> cadastrarCredenciamento(@RequestBody CredenciamentoRequest dto){
        CredenciamentoResponse credenciamento = credenciamentoService.cadastrarCredenciamento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(credenciamento);
    }
}
