package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.VerificarException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.EstatisticaTotalTime;
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

    public void aumentarQuantidadeAmarelos(int qtd, Time time) throws Exception {
        EstatisticaTotalTime estatisticaTotalTime = estatisticaTotalTimeRepository.findByTime(time);
        estatisticaTotalTime.setCartoesAmarelos(estatisticaTotalTime.getCartoesAmarelos() + qtd);
        estatisticaTotalTimeRepository.save(estatisticaTotalTime);
    }

    public void aumentarQuantidadeVermelhos(int qtd, Time time) throws Exception {
        EstatisticaTotalTime estatisticaTotalTime = estatisticaTotalTimeRepository.findByTime(time);
        estatisticaTotalTime.setCartoesAmarelos(estatisticaTotalTime.getCartoesAmarelos() + qtd);
        estatisticaTotalTimeRepository.save(estatisticaTotalTime);
    }

    public void aumentarQuantidadeGolsPro(Time time) throws Exception {
        EstatisticaTotalTime estatisticaTotalTime = estatisticaTotalTimeRepository.findByTime(time);
        estatisticaTotalTime.setGolsPro(estatisticaTotalTime.getGolsPro() + 1);
        estatisticaTotalTimeRepository.save(estatisticaTotalTime);
    }

    public void aumentarQuantidadeGolsContra(Time time) throws Exception {
        EstatisticaTotalTime estatisticaTotalTime = estatisticaTotalTimeRepository.findByTime(time);
        estatisticaTotalTime.setGolsContra(estatisticaTotalTime.getGolsContra() + 1);
        estatisticaTotalTimeRepository.save(estatisticaTotalTime);
    }

    public void aumentarQuantidadePontos(int pontos, Time time) throws Exception {
        if(pontos < 0 || pontos > 3){
            throw new VerificarException("Erro ao aumentar quantidade de pontos: quantidade inválida");
        }
        EstatisticaTotalTime estatisticaTotalTime = estatisticaTotalTimeRepository.findByTime(time);
        estatisticaTotalTime.setPontos(estatisticaTotalTime.getPontos() + pontos);
        estatisticaTotalTimeRepository.save(estatisticaTotalTime);
    }

    public void aumentarQuantidadePartidas(Time time) throws Exception {
        EstatisticaTotalTime estatisticaTotalTime = estatisticaTotalTimeRepository.findByTime(time);
        estatisticaTotalTime.setQuantidadePartidas(estatisticaTotalTime.getQuantidadePartidas() + 1);
        estatisticaTotalTimeRepository.save(estatisticaTotalTime);
    }
}
