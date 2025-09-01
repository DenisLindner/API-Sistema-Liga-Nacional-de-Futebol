package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto;

public record EstatisticasCampeonatoResponse(Long id, int pontos, int quantidadePartidas, int golsFeitos, int cartoesAmarelos, int cartoesVermelhos, String nome_campeonato) {
}
