package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto;

import java.util.Date;

public record TemporadaResponse(Long id, boolean concluido, String nome, Date dataInicio, Date dataFim, String nome_campeonato) {
}
