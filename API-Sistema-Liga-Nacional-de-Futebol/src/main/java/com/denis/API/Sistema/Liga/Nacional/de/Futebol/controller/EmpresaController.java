package com.denis.API.Sistema.Liga.Nacional.de.Futebol.controller;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.EmpresaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.EmpresaResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.service.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/buscar-todas-empresas")
    public ResponseEntity<List<EmpresaResponse>> buscarTodasEmpresas() {
        return ResponseEntity.status(HttpStatus.OK).body(empresaService.buscarTodasEmpresas());
    }
}
