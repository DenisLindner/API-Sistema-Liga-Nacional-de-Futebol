package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
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
}
