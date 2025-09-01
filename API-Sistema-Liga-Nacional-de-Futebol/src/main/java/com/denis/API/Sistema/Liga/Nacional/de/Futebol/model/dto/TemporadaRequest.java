package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Campeonato;

import java.util.Date;

public record TemporadaRequest(String nome, Date dataInicio, Date dataFim, Campeonato campeonato) {
}
