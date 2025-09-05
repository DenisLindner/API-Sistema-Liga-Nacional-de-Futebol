package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.BuscarException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.*;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Time;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.TimeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class TimeService {

    private TimeRepository timeRepository;
    private EstatisticaTotalTimeService estatisticaTotalTimeService;
    private CampeonatoService campeonatoService;

    public TimeService(TimeRepository timeRepository, EstatisticaTotalTimeService estatisticaTotalTimeService, CampeonatoService campeonatoService) {
        this.timeRepository = timeRepository;
        this.estatisticaTotalTimeService = estatisticaTotalTimeService;
        this.campeonatoService = campeonatoService;
    }

    public TimeResponse cadastrarTime (TimeRequest dto) {
        try {
            Time time = new Time();
            time.setNome(dto.nome());
            time.setEstadio(dto.estadio());
            time.setNomeTreinador(dto.nomeTreinador());
            time.setCampeonato(campeonatoService.buscarCampeonatoPorId(dto.idCampeonato()));

            if (time.getCampeonato().getTimes().size() >= 20){
                throw new CadastroException("Erro ao Cadastrar Time: Quantidade Limite de Times Alcaçada na Competição");
            } else {

                Time salvo = timeRepository.save(time);

                estatisticaTotalTimeService.cadastrarEstatisticaTotalTime(salvo);

                return new TimeResponse(salvo.getId(), salvo.getNome(), salvo.getEstadio(), salvo.getNomeTreinador(), salvo.getCampeonato().getNome());
            }
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar Time: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Time");
        }
    }

    public Time buscarTimePorId(Long id){
        try {
            return timeRepository.findById(id).orElseThrow();
        } catch (DataIntegrityViolationException e){
            throw new BuscarException("Erro ao buscar Time: Dados Inválidos");
        } catch (Exception e) {
            throw new BuscarException("Erro inesperado ao buscar Time");
        }
    }
}
