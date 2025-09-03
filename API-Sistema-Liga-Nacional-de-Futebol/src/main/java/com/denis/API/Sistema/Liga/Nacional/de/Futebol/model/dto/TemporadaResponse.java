package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto;

import java.time.LocalDate;
import java.util.Date;

public record TemporadaResponse(Long id, boolean concluido, String nome, LocalDate dataInicio, LocalDate dataFim, String nome_campeonato) {
}
