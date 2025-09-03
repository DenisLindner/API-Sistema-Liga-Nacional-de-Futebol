package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.calculos.CalculoPartidasTemporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CalculoPartidasException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.TemporadaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.TemporadaResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Temporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Time;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.TemporadaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class TemporadaService {

    private PartidaService partidaService;
    private TemporadaRepository temporadaRepository;
    private EstatisticaTemporadaTimeService estatisticaTemporadaTimeService;


    public TemporadaService(TemporadaRepository temporadaRepository, PartidaService partidaService, EstatisticaTemporadaTimeService estatisticaTemporadaTimeService) {
        this.temporadaRepository = temporadaRepository;
        this.partidaService = partidaService;
        this.estatisticaTemporadaTimeService = estatisticaTemporadaTimeService;
    }

    public TemporadaResponse cadastrarTemporada (TemporadaRequest dto){
        try {
            Temporada temporada = new Temporada();
            BeanUtils.copyProperties(dto, temporada);

            if (temporada.getCampeonato().getTimes().size() != 20){
                throw new CalculoPartidasException("Erro ao Cadastrar Temporada: Quantidade de Times Inválida");
            }

            Temporada salvo = temporadaRepository.save(temporada);

            CalculoPartidasTemporada calculoPartidasTemporada = new CalculoPartidasTemporada(partidaService);
            calculoPartidasTemporada.partidas(salvo);
            cadastrarEstatisticasTemporada(temporada);

            return new TemporadaResponse(salvo.getId(), salvo.isConcluido(), salvo.getNome(), salvo.getDataInicio(), salvo.getDataFim(), salvo.getCampeonato().getNome());
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar Temporada: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Temporada");
        }
    }

    public void cadastrarEstatisticasTemporada(Temporada temporada){
        for (Time time : temporada.getCampeonato().getTimes()){
            estatisticaTemporadaTimeService.cadastrarEstatisticasTemporadaTime(time, temporada);
        }
    }
}
