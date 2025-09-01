package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Temporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Time;

import java.time.LocalDateTime;

public record PartidaRequest(int rodada, LocalDateTime dataHora, Time timeMandante, Time timeVisitante, Temporada temporada) {
}
