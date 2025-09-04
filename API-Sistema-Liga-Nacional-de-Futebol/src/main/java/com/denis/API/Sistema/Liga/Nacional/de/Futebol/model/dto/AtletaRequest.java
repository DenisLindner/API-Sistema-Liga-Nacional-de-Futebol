package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto;

import java.time.LocalDate;

public record AtletaRequest(String nome, String cpf, LocalDate dataNascimento, LocalDate dataContratacao, LocalDate dataFinalContratacao, Long idTime) {
}
