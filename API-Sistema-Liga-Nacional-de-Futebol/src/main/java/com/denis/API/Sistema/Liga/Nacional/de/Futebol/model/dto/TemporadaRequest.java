package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto;

import java.time.LocalDate;

public record TemporadaRequest(String nome, LocalDate dataInicio, Long idCampeonato) {
}
