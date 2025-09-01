package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto;

public record EstatisticaTotalTimeResponse(Long id, int pontos, int quantidadePartidas, int cartoesAmarelos, int cartoesVermelhos, int golsPro, int golsContra, String nome_time) {
}
