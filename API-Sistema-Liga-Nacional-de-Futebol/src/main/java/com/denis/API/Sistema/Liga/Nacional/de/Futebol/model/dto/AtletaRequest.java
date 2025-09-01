package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Time;

import java.util.Date;

public record AtletaRequest(String nome, String cpf, Date dataNascimento, Date dataContratacao, Date dataFinalContratacao, Time time) {
}
