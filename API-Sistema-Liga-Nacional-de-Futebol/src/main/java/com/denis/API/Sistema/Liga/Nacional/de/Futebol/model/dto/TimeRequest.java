package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Campeonato;

public record TimeRequest(String nome, String estadio, String nomeTreinador, Campeonato campeonato) {
}
