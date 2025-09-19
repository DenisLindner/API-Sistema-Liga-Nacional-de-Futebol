package com.denis.API.Sistema.Liga.Nacional.de.Futebol.controller;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.NoticiaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.NoticiaResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.service.NoticiaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noticia")
public class NoticiaController {

    private NoticiaService noticiaService;

    public NoticiaController(NoticiaService noticiaService) {
        this.noticiaService = noticiaService;
    }

    @PostMapping("/cadastrar-noticia")
    public ResponseEntity<NoticiaResponse> cadastrarNoticia(@RequestBody NoticiaRequest dto) {
        NoticiaResponse noticia = noticiaService.cadastrarNoticia(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(noticia);
    }

    @GetMapping("/ultimas-10-noticias")
    public ResponseEntity<List<NoticiaResponse>> ultimas10Noticias() {
        return ResponseEntity.status(HttpStatus.OK).body(noticiaService.ultimas10Noticias());
    }
}
