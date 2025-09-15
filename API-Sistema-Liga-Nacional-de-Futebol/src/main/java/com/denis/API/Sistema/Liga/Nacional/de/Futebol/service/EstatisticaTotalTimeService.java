package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.VerificarException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.EstatisticaTotalTime;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Temporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Time;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.EstatisticaTotalTimeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class EstatisticaTotalTimeService {

    private EstatisticaTotalTimeRepository estatisticaTotalTimeRepository;

    public EstatisticaTotalTimeService(EstatisticaTotalTimeRepository estatisticaTotalTimeRepository) {this.estatisticaTotalTimeRepository = estatisticaTotalTimeRepository;}

    public void cadastrarEstatisticaTotalTime(Time time){
        try {
            EstatisticaTotalTime estatisticaTotalTime = new EstatisticaTotalTime(time);

            estatisticaTotalTimeRepository.save(estatisticaTotalTime);
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar Estatisticas Totais do Time: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Estatisticas Totais do Time");
        }
    }

    public void aumentarQuantidadeAmarelos(Time time) throws Exception {
        EstatisticaTotalTime estatisticaTotalTime = estatisticaTotalTimeRepository.findByTime(time);
        estatisticaTotalTime.setCartoesAmarelos(estatisticaTotalTime.getCartoesAmarelos() + 1);
        estatisticaTotalTimeRepository.save(estatisticaTotalTime);
    }

    public void aumentarQuantidadeVermelhos(Time time, Temporada temporada) throws Exception {
        EstatisticaTotalTime estatisticaTotalTime = estatisticaTotalTimeRepository.findByTime(time);
        estatisticaTotalTime.setCartoesAmarelos(estatisticaTotalTime.getCartoesAmarelos() + 1);
        estatisticaTotalTimeRepository.save(estatisticaTotalTime);
    }

    public void aumentarQuantidadeGols(int qtdGolsPro, int qtdGolsContra,Time time, Temporada temporada) throws Exception {
        if(qtdGolsPro < 0 || qtdGolsContra < 0){
            throw new VerificarException("Erro ao aumentar quantidade de gols: quantidade inválida");
        }
        EstatisticaTotalTime estatisticaTotalTime = estatisticaTotalTimeRepository.findByTime(time);
        estatisticaTotalTime.setGolsPro(estatisticaTotalTime.getGolsPro() + qtdGolsPro);
        estatisticaTotalTime.setGolsContra(estatisticaTotalTime.getGolsContra() + qtdGolsContra);
        estatisticaTotalTimeRepository.save(estatisticaTotalTime);
    }

    public void aumentarQuantidadePontos(int pontos, Time time, Temporada temporada) throws Exception {
        if(pontos < 0 || pontos > 3){
            throw new VerificarException("Erro ao aumentar quantidade de pontos: quantidade inválida");
        }
        EstatisticaTotalTime estatisticaTotalTime = estatisticaTotalTimeRepository.findByTime(time);
        estatisticaTotalTime.setPontos(estatisticaTotalTime.getPontos() + pontos);
        estatisticaTotalTimeRepository.save(estatisticaTotalTime);
    }

    public void aumentarQuantidadePartidas(Time time, Temporada temporada) throws Exception {
        EstatisticaTotalTime estatisticaTotalTime = estatisticaTotalTimeRepository.findByTime(time);
        estatisticaTotalTime.setQuantidadePartidas(estatisticaTotalTime.getQuantidadePartidas() + 1);
        estatisticaTotalTimeRepository.save(estatisticaTotalTime);
    }
}
