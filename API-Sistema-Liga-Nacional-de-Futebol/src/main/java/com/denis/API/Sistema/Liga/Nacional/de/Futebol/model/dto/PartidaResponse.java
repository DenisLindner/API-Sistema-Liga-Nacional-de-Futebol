package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto;

import java.time.LocalDateTime;

public record PartidaResponse(Long id, int rodada, LocalDateTime dataHora, String nome_timeMandante, String nome_timeVisitante, String nome_temporada, String concluido) {
}
