package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;


import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.VerificarException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.AmareloRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.GolRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.SumulaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.VermelhoRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SumulaService {

    private PartidaService partidaService;
    private AtletaService atletaService;
    private TimeService timeService;

    public SumulaService(PartidaService partidaService, AtletaService atletaService,  TimeService timeService) {
        this.partidaService = partidaService;
        this.atletaService = atletaService;
        this.timeService = timeService;
    }

    public void cadastrarSumula(SumulaRequest dto){
        try {
            Partida partida = partidaService.buscarPartidaPorId(dto.idPartida());
            if (partida.isConcluido()){
                throw new VerificarException("Erro ao verificar Sumula: Partida já foi concluída");
            }
            if (dto.golsMandante() < 0){
                throw new VerificarException("Erro ao verificar Sumula: Gols do Mandante Inválido");
            }
            if (dto.golsVisitante() < 0){
                throw new VerificarException("Erro ao verificar Sumula: Gols do Visitante Inválido");
            }

            Sumula sumula = new Sumula();
            sumula.setGolsMandante(dto.golsMandante());
            sumula.setGolsVisitante(dto.golsVisitante());
            sumula.setAmarelos(dto.amarelos());
            sumula.setVermelhos(dto.vermelhos());
            sumula.setGols(dto.gols());
            sumula.setPartida(partida);

            if (!verificarAmarelos(sumula)){
                throw new VerificarException("Erro ao verificar Sumula: Erro na lista de amarelos");
            }
            if (!verificarVermelhos(sumula)){
                throw new VerificarException("Erro ao verificar Sumula: Erro na lista de vermelhos");
            }
            if (!verificarGols(sumula)){
                throw new VerificarException("Erro ao verificar Sumula: Erro na lista de gols");
            }

            for (AmareloRequest amarelo : sumula.getAmarelos()) {

            }
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao verificar Sumula: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao verificar Sumula");
        }
    }

    public boolean verificarAmarelos(Sumula sumula){
        List<AmareloRequest> amarelos = sumula.getAmarelos();
        if (!amarelos.isEmpty()){
            for (AmareloRequest amarelo : amarelos) {
                Atleta atleta = atletaService.buscarAtletaPorId(amarelo.idAtleta());
                if (!(atleta.getTime() == sumula.getPartida().getTimeMandante() || atleta.getTime() == sumula.getPartida().getTimeVisitante())){
                    throw new VerificarException("Erro ao verificar amarelo: time do Atleta não condizente com nenhum time");
                }
                if (amarelo.minuto() < 0){
                    throw new VerificarException("Erro ao verificar amarelo: minuto inválido");
                }
            }
        }
        return true;
    }

    public boolean verificarVermelhos(Sumula sumula){
        List<VermelhoRequest> vermelhos = sumula.getVermelhos();
        if (!vermelhos.isEmpty()){
            for (VermelhoRequest vermelho : vermelhos) {
                Atleta atleta = atletaService.buscarAtletaPorId(vermelho.idAtleta());
                if (!(atleta.getTime() == sumula.getPartida().getTimeMandante() || atleta.getTime() == sumula.getPartida().getTimeVisitante())){
                    throw new VerificarException("Erro ao verificar vermelho: time do Atleta não condizente com nenhum time da Partida");
                }
                if (vermelho.minuto() < 0){
                    throw new VerificarException("Erro ao verificar vermelho: minuto inválido");
                }
            }
        }
        return true;
    }

    public boolean verificarGols(Sumula sumula){
        List<GolRequest> gols = sumula.getGols();
        if (!gols.isEmpty()){
            int quantidadeGolsVisitante = 0;
            int quantidadeGolsMandante = 0;
            for (GolRequest gol : gols) {
                if (!(gol.idAtleta() == 0)){
                    Atleta atleta = atletaService.buscarAtletaPorId(gol.idAtleta());
                    if (!(atleta.getTime() == sumula.getPartida().getTimeMandante() || atleta.getTime() == sumula.getPartida().getTimeVisitante())){
                        throw new VerificarException("Erro ao verificar gol: time do Atleta não condizente com nenhum time da Partida");
                    }
                }

                if (gol.minuto() < 0){
                    throw new VerificarException("Erro ao verificar gol: minuto inválido");
                }

                Time time = timeService.buscarTimePorId(gol.idTime());
                if (time.equals(sumula.getPartida().getTimeVisitante())){
                    quantidadeGolsVisitante++;
                } else if (time.equals(sumula.getPartida().getTimeMandante())){
                    quantidadeGolsMandante++;
                } else {
                    throw new VerificarException("Erro ao verificar gol: time inválido");
                }
            }

            if (!(quantidadeGolsMandante == sumula.getGolsMandante())){
                throw new VerificarException("Erro ao verificar gols: quantidade de gols do mandante não condizente com o estipulado");
            }
            if (!(quantidadeGolsVisitante == sumula.getGolsVisitante())){
                throw new VerificarException("Erro ao verificar gols: quantidade de gols do visitante não condizente com o estipulado");
            }
        }
        return true;
    }
}
