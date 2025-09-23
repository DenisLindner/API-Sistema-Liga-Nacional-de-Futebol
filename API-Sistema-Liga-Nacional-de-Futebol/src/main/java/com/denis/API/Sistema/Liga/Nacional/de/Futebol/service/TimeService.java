package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.BuscarException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.VerificarException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.*;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Atleta;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Partida;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Time;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.PartidaRepository;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.TimeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeService {

    private TimeRepository timeRepository;
    private EstatisticaTotalTimeService estatisticaTotalTimeService;
    private CampeonatoService campeonatoService;
    private PartidaRepository partidaRepository;

    public TimeService(TimeRepository timeRepository, EstatisticaTotalTimeService estatisticaTotalTimeService, CampeonatoService campeonatoService, PartidaRepository partidaRepository) {
        this.timeRepository = timeRepository;
        this.estatisticaTotalTimeService = estatisticaTotalTimeService;
        this.campeonatoService = campeonatoService;
        this.partidaRepository = partidaRepository;
    }

    public TimeResponse cadastrarTime (TimeRequest dto) {
        try {
            if (dto.divisao() != 1 && dto.divisao() != 2){
                throw new VerificarException("Erro inesperado ao Cadastrar Time");
            }
            Time time = new Time();
            time.setNome(dto.nome());
            time.setEstadio(dto.estadio());
            time.setNomeTreinador(dto.nomeTreinador());
            time.setDivisao(dto.divisao());
            time.setCampeonato(campeonatoService.buscarCampeonatoPorId(dto.idCampeonato()));

            if (time.getCampeonato().getTimes().size() >= 20){
                throw new CadastroException("Erro ao Cadastrar Time: Quantidade Limite de Times Alcaçada na Competição");
            } else {

                Time salvo = timeRepository.save(time);

                estatisticaTotalTimeService.cadastrarEstatisticaTotalTime(salvo);

                return new TimeResponse(salvo.getId(), salvo.getNome(), salvo.getEstadio(), salvo.getNomeTreinador(), salvo.getDivisao(), salvo.getCampeonato().getNome());
            }
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar Time: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Time");
        }
    }

    public void deletarTimePorId (Long id){
        try {
            timeRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new BuscarException("Erro ao deletar Time: Dados Inválidos");
        } catch (Exception e) {
            throw new BuscarException("Erro inesperado ao deletar Time");
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

    public List<AtletaResponse> buscarElencoTime(Long id){
        try {
            Time time = timeRepository.findById(id).orElseThrow();
            List<AtletaResponse> atletas = new ArrayList<>();
            for (Atleta atleta : time.getAtletas()){
                atletas.add(new AtletaResponse(atleta.getId(), atleta.getNome(), atleta.getDataNascimento(), atleta.getDataContratacao(), atleta.getDataFinalContratacao(), atleta.getTime().getNome()));
            }
            return atletas;
        } catch (DataIntegrityViolationException e){
            throw new BuscarException("Erro ao Buscar Elenco do Time: Dados Inválidos");
        } catch (Exception e) {
            throw new BuscarException("Erro inesperado ao Buscar Elenco");
        }
    }

    public List<PartidaResponse> buscarProximas5Partidas(Long id){
        try {
            List<Partida> partidas = partidaRepository.findTop5ByTimeMandanteOrTimeVisitanteAndDataHoraAfterOrderByDataHoraAsc(buscarTimePorId(id), buscarTimePorId(id), LocalDateTime.now());
            List<PartidaResponse> partidaResponses = new ArrayList<>();
            for (Partida partida : partidas){
                partidaResponses.add(new PartidaResponse(partida.getId(), partida.getRodada(), partida.getDataHora(), partida.getTimeMandante().getNome(), partida.getTimeVisitante().getNome(), partida.getTemporada().getNome(), String.valueOf(partida.isConcluido())));
            }
            return partidaResponses;
        } catch (DataIntegrityViolationException e){
            throw new BuscarException("Erro ao Buscar Elenco do Time: Dados Inválidos");
        } catch (Exception e) {
            throw new BuscarException("Erro inesperado ao Buscar Elenco");
        }
    }
}
