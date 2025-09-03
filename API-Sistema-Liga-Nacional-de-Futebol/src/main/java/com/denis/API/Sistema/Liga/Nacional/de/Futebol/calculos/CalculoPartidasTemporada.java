package com.denis.API.Sistema.Liga.Nacional.de.Futebol.calculos;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CalculoPartidasException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.PartidaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Partida;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Temporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Time;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.service.PartidaService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CalculoPartidasTemporada {

    private PartidaService partidaService;

    public  CalculoPartidasTemporada(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    public void partidas(Temporada temporada) {
        List<Time> times = temporada.getCampeonato().getTimes();
        if (times.size() != 20){
            throw new CalculoPartidasException("Erro ao Calcular Partidas da Temporada: Quantidade de Times Inválida");
        }

        HashMap<Integer, Time> numTimes = new HashMap<>();
        int i = 0;
        for (Time time : times){
            numTimes.put(i, time);
            i++;
        }

        List<Partida> partidas = new ArrayList<>();

        boolean mando = true;
        int rodada = 1;
        LocalDateTime dataInicio = LocalDateTime.of(temporada.getDataInicio(), LocalTime.of(19, 30));
        for (int a = 1; a <= 2; a++){
            for (int b = 0; b <= 18; b++){
                int[] posicoes = timesPosicao(b);
                LocalDateTime dataHora = dataInicio.plusDays(rodada* 7L);
                for (int c = 0; c < 10; c++){
                    PartidaRequest partidaRequest;
                    if (mando){
                        if (c == 9){
                            partidaRequest = new PartidaRequest(rodada, dataHora, numTimes.get(posicoes[c]), numTimes.get(19), temporada);
                        } else {
                            partidaRequest = new PartidaRequest(rodada, dataHora, numTimes.get(posicoes[c]), numTimes.get(posicoes[18-c]), temporada);
                        }
                    } else {
                        if (c == 9){
                            partidaRequest = new PartidaRequest(rodada, dataHora,numTimes.get(19), numTimes.get(posicoes[c]), temporada);
                        } else {
                            partidaRequest = new PartidaRequest(rodada, dataHora,numTimes.get(posicoes[18-c]), numTimes.get(posicoes[c]), temporada);
                        }
                    }
                    partidaService.cadastrarPartida(partidaRequest);
                }
                rodada++;
                mando = !mando;
            }
        }
    }

    public static int[] timesPosicao(int rodada){
        int[] timesPosicoes = new int[19];
        for (int i = 0; i < 19; i++){
            if (i+rodada > 18){
                timesPosicoes[i+rodada-19] = i;
            } else {
                timesPosicoes[i+rodada] = i;
            }
        }
        return timesPosicoes;
    }
}
