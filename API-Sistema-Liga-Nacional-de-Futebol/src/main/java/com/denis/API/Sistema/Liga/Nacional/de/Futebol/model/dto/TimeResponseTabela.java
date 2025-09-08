package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto;

public record TimeResponseTabela(String nome_time, int pontos, int saldoGols, int golsPro, int golsContra, int quantidadePartidas, int cartoesAmarelos, int cartoesVermelhos, Long id) {
}
