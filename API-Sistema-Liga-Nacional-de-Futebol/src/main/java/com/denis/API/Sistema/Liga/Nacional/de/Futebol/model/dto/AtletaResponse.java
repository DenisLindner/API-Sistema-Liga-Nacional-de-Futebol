package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto;

import java.time.LocalDate;

public record AtletaResponse(Long id, String nome, LocalDate dataNascimento, LocalDate dataContratacao, LocalDate dataFinalContratacao, int quantidadePartidas, int cartoesAmarelos, int cartoesVermelhos, int quantidadeGols, String nomeTime) {
}
