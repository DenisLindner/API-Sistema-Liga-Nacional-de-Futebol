package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.VerificarException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.*;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.EstatisticaTemporadaTimeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class EstatisticaTemporadaTimeService {

    private EstatisticaTemporadaTimeRepository estatisticaTemporadaTimeRepository;
    private EstatisticaTotalTimeService estatisticaTotalTimeService;

    public EstatisticaTemporadaTimeService (EstatisticaTemporadaTimeRepository estatisticaTemporadaTimeRepository, EstatisticaTotalTimeService estatisticaTotalTimeService) {
        this.estatisticaTemporadaTimeRepository = estatisticaTemporadaTimeRepository;
        this.estatisticaTotalTimeService = estatisticaTotalTimeService;
    }

    public void cadastrarEstatisticasTemporadaTime(Time time, Temporada temporada){
        try {
            EstatisticaTemporadaTime estatisticaTemporadaTime = new EstatisticaTemporadaTime(time, temporada);

            estatisticaTemporadaTimeRepository.save(estatisticaTemporadaTime);
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar Estatisticas da Temporada do Time: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Estatisticas da Temporada do Time");
        }
    }

    public void aumentarQuantidadeAmarelos(int qtd, Time time, Temporada temporada) throws Exception {
        EstatisticaTemporadaTime estatisticaTemporadaTime = estatisticaTemporadaTimeRepository.findByTimeAndTemporada(time, temporada);
        estatisticaTemporadaTime.setCartoesAmarelos(estatisticaTemporadaTime.getCartoesAmarelos() + 1);
        estatisticaTemporadaTimeRepository.save(estatisticaTemporadaTime);
        estatisticaTotalTimeService.aumentarQuantidadeAmarelos(qtd, time);
    }

    public void aumentarQuantidadeVermelhos(int qtd, Time time, Temporada temporada) throws Exception {
        EstatisticaTemporadaTime estatisticaTemporadaTime = estatisticaTemporadaTimeRepository.findByTimeAndTemporada(time, temporada);
        estatisticaTemporadaTime.setCartoesVermelhos(estatisticaTemporadaTime.getCartoesVermelhos() + qtd);
        estatisticaTemporadaTimeRepository.save(estatisticaTemporadaTime);
        estatisticaTotalTimeService.aumentarQuantidadeVermelhos(qtd, time);
    }

    public void aumentarQuantidadeGolsPro(Time time, Temporada temporada) throws Exception {
        EstatisticaTemporadaTime estatisticaTemporadaTime = estatisticaTemporadaTimeRepository.findByTimeAndTemporada(time, temporada);
        estatisticaTemporadaTime.setGolsPro(estatisticaTemporadaTime.getGolsPro() + 1);
        estatisticaTemporadaTimeRepository.save(estatisticaTemporadaTime);
        estatisticaTotalTimeService.aumentarQuantidadeGolsPro(time);
    }

    public void aumentarQuantidadeGolsContra(Time time, Temporada temporada) throws Exception {
        EstatisticaTemporadaTime estatisticaTemporadaTime = estatisticaTemporadaTimeRepository.findByTimeAndTemporada(time, temporada);
        estatisticaTemporadaTime.setGolsContra(estatisticaTemporadaTime.getGolsContra() + 1);
        estatisticaTemporadaTimeRepository.save(estatisticaTemporadaTime);
        estatisticaTotalTimeService.aumentarQuantidadeGolsContra(time);
    }

    public void aumentarQuantidadePontos(int pontos, Time time, Temporada temporada) throws Exception {
        if(pontos < 0 || pontos > 3){
            throw new VerificarException("Erro ao aumentar quantidade de pontos: quantidade inválida");
        }
        EstatisticaTemporadaTime estatisticaTemporadaTime = estatisticaTemporadaTimeRepository.findByTimeAndTemporada(time, temporada);
        estatisticaTemporadaTime.setPontos(estatisticaTemporadaTime.getPontos() + pontos);
        estatisticaTemporadaTimeRepository.save(estatisticaTemporadaTime);
        estatisticaTotalTimeService.aumentarQuantidadePontos(pontos, time);
    }

    public void aumentarQuantidadePartidas(Time time, Temporada temporada) throws Exception {
        EstatisticaTemporadaTime estatisticaTemporadaTime = estatisticaTemporadaTimeRepository.findByTimeAndTemporada(time, temporada);
        estatisticaTemporadaTime.setQuantidadePartidas(estatisticaTemporadaTime.getQuantidadePartidas() + 1);
        estatisticaTemporadaTimeRepository.save(estatisticaTemporadaTime);
        estatisticaTotalTimeService.aumentarQuantidadePartidas(time);
    }
}
