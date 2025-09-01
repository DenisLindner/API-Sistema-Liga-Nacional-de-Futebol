package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Empresa;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Partida;

public record CredenciamentoRequire(String cpf, String crtJornalista, String nome, Empresa empresa, Partida partida) {
}
