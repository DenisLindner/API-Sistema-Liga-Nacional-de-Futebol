package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Empresa;

public record NoticiaRequest(String autor, String titulo, String descricao, String conteudo, Empresa empresa) {
}
