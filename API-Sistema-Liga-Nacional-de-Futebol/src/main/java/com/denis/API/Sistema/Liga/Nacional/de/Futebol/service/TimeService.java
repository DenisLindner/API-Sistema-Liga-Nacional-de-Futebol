package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.*;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Temporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Time;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.EstatisticaTemporadaTimeRepository;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.EstatisticaTotalTimeRepository;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.TimeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class TimeService {

    private TimeRepository timeRepository;
    private EstatisticaTemporadaTimeService estatisticaTemporadaTimeService;
    private EstatisticaTotalTimeService estatisticaTotalTimeService;

    public TimeService(TimeRepository timeRepository, EstatisticaTemporadaTimeService estatisticaTemporadaTimeService, EstatisticaTotalTimeService estatisticaTotalTimeService) {
        this.timeRepository = timeRepository;
        this.estatisticaTemporadaTimeService = estatisticaTemporadaTimeService;
        this.estatisticaTotalTimeService = estatisticaTotalTimeService;
    }

    public Time cadastrarTime (TimeRequest dto){
        try {
            Time time = new Time();
            BeanUtils.copyProperties(dto, time);

            Time salvo = timeRepository.save(time);

            estatisticaTotalTimeService.cadastrarEstatisticaTotalTime(salvo);

            return salvo;
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar Time: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Time");
        }
    }
}
