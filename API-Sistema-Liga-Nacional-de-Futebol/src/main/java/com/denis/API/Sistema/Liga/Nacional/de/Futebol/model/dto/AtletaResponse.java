package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto;

import java.util.Date;

public record AtletaResponse(Long id, String nome, String cpf, Date dataNascimento, Date dataContratacao, Date dataFinalContratacao, int quantidadePartidas, int cartoesAmarelos, int cartoesVermelhos, int quantidadeGols, String nome_time) {
}
