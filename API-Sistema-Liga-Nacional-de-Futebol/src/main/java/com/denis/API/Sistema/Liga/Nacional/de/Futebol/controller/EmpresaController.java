package com.denis.API.Sistema.Liga.Nacional.de.Futebol.controller;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.EmpresaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.EmpresaResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.service.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    private EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping("/cadastrar-empresa")
    public ResponseEntity<EmpresaResponse> cadastrarEmpresa(@RequestBody EmpresaRequest dto) {
        EmpresaResponse empresa = empresaService.cadastrarEmpresa(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresa);
    }
}
