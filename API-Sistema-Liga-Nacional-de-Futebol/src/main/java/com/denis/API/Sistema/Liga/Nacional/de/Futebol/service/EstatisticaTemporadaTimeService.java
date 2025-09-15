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

    public EstatisticaTemporadaTimeService (EstatisticaTemporadaTimeRepository estatisticaTemporadaTimeRepository) {this.estatisticaTemporadaTimeRepository = estatisticaTemporadaTimeRepository;}

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

    public void aumentarQuantidadeAmarelos(Time time, Temporada temporada) throws Exception {
        EstatisticaTemporadaTime estatisticaTemporadaTime = estatisticaTemporadaTimeRepository.findByTimeAndTemporada(time, temporada);
        estatisticaTemporadaTime.setCartoesAmarelos(estatisticaTemporadaTime.getCartoesAmarelos() + 1);
        estatisticaTemporadaTimeRepository.save(estatisticaTemporadaTime);
    }

    public void aumentarQuantidadeVermelhos(Time time, Temporada temporada) throws Exception {
        EstatisticaTemporadaTime estatisticaTemporadaTime = estatisticaTemporadaTimeRepository.findByTimeAndTemporada(time, temporada);
        estatisticaTemporadaTime.setCartoesAmarelos(estatisticaTemporadaTime.getCartoesAmarelos() + 1);
        estatisticaTemporadaTimeRepository.save(estatisticaTemporadaTime);
    }

    public void aumentarQuantidadeGols(int qtdGolsPro, int qtdGolsContra,Time time, Temporada temporada) throws Exception {
        if(qtdGolsPro < 0 || qtdGolsContra < 0){
            throw new VerificarException("Erro ao aumentar quantidade de gols: quantidade inválida");
        }
        EstatisticaTemporadaTime estatisticaTemporadaTime = estatisticaTemporadaTimeRepository.findByTimeAndTemporada(time, temporada);
        estatisticaTemporadaTime.setGolsPro(estatisticaTemporadaTime.getGolsPro() + qtdGolsPro);
        estatisticaTemporadaTime.setGolsContra(estatisticaTemporadaTime.getGolsContra() + qtdGolsContra);
        estatisticaTemporadaTimeRepository.save(estatisticaTemporadaTime);
    }

    public void aumentarQuantidadePontos(int pontos, Time time, Temporada temporada) throws Exception {
        if(pontos < 0 || pontos > 3){
            throw new VerificarException("Erro ao aumentar quantidade de pontos: quantidade inválida");
        }
        EstatisticaTemporadaTime estatisticaTemporadaTime = estatisticaTemporadaTimeRepository.findByTimeAndTemporada(time, temporada);
        estatisticaTemporadaTime.setPontos(estatisticaTemporadaTime.getPontos() + pontos);
        estatisticaTemporadaTimeRepository.save(estatisticaTemporadaTime);
    }

    public void aumentarQuantidadePartidas(Time time, Temporada temporada) throws Exception {
        EstatisticaTemporadaTime estatisticaTemporadaTime = estatisticaTemporadaTimeRepository.findByTimeAndTemporada(time, temporada);
        estatisticaTemporadaTime.setQuantidadePartidas(estatisticaTemporadaTime.getQuantidadePartidas() + 1);
        estatisticaTemporadaTimeRepository.save(estatisticaTemporadaTime);
    }
}
