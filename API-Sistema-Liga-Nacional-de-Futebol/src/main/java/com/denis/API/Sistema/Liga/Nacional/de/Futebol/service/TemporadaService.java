package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.calculos.CalculoPartidasTemporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.calculos.CalculoTabelaTemporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.BuscarException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.DeletarException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.VerificarException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.PartidaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.TemporadaResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.TimeResponseTabela;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Campeonato;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Partida;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Temporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Time;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.TemporadaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TemporadaService {

    private final CalculoTabelaTemporada calculoTabelaTemporada;
    private TemporadaRepository temporadaRepository;
    private EstatisticaTemporadaTimeService estatisticaTemporadaTimeService;
    private CampeonatoService campeonatoService;
    private CalculoPartidasTemporada calculoPartidasTemporada;
    private PartidaService partidaService;
    private CampeaoTemporadaService campeaoTemporadaService;


    public TemporadaService(TemporadaRepository temporadaRepository, EstatisticaTemporadaTimeService estatisticaTemporadaTimeService, CampeonatoService campeonatoService, CalculoPartidasTemporada calculoPartidasTemporada, PartidaService partidaService, CalculoTabelaTemporada calculoTabelaTemporada,  CampeaoTemporadaService campeaoTemporadaService) {
        this.temporadaRepository = temporadaRepository;
        this.estatisticaTemporadaTimeService = estatisticaTemporadaTimeService;
        this.campeonatoService = campeonatoService;
        this.calculoPartidasTemporada = calculoPartidasTemporada;
        this.partidaService = partidaService;
        this.calculoTabelaTemporada = calculoTabelaTemporada;
        this.campeaoTemporadaService = campeaoTemporadaService;
    }

    public List<TemporadaResponse> cadastrarTemporada (Long idCampeonato, LocalDate dataInicio, String nome){
        try {
            Campeonato campeonato = campeonatoService.buscarCampeonatoPorId(idCampeonato);
            if (!verificarConclusaoTemporadas(campeonato)){
                throw new VerificarException("Erro ao Cadastrar Temporada: Temporada Anterior Inacabada");
            }

            if (campeonato.getTimes().size() != 40){
                throw new VerificarException("Erro ao Cadastrar Temporada: Número de Times Inválido");
            }
            List<Time> timesSerieA = new ArrayList<>();
            List<Time> timesSerieB = new ArrayList<>();
            for (Time time : campeonato.getTimes()){
                if (time.getDivisao() == 1){
                    timesSerieA.add(time);
                } else  {
                    timesSerieB.add(time);
                }
            }

            if (timesSerieA.size() != 20){
                throw new VerificarException("Erro inesperado ao Cadastrar Temporada");
            } else if (timesSerieB.size() != 20){
                throw new VerificarException("Erro inesperado ao Cadastrar Temporada");
            }

            List<TemporadaResponse> temporadaResponses = new ArrayList<>();
            for (int i = 1; i <= 2; i++){
                if (dataInicio.isBefore(LocalDate.now())){
                    throw new VerificarException("Erro ao Cadastrar Temporada: Data de Inicio Inválida");
                }
                Temporada temporada = new Temporada();
                String nomeTemporada = campeonato.getNome()+" "+nome+" "+ (i == 1 ? "Série A" : "Série B")+ " "+dataInicio.getYear();
                temporada.setNome(nomeTemporada);
                temporada.setDataInicio(dataInicio);
                temporada.setDataFim(dataInicio.plusDays(((20 - 1) * 2) * 7L));
                temporada.setCampeonato(campeonato);

                Temporada salvo = temporadaRepository.save(temporada);

                List<PartidaRequest> partidas = calculoPartidasTemporada.partidas((i == 1 ? timesSerieA :timesSerieB), salvo);
                for (PartidaRequest partida : partidas) {
                    partidaService.cadastrarPartida(partida);
                }
                cadastrarEstatisticasTemporada((i == 1? timesSerieA : timesSerieB), temporada);
                temporadaResponses.add(new TemporadaResponse(salvo.getId(), salvo.isConcluido(), salvo.getNome(), salvo.getDataInicio(), salvo.getDataFim(), salvo.getCampeonato().getNome()));
            }
            return temporadaResponses;
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar Temporada: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Temporada");
        }
    }

    public void cadastrarEstatisticasTemporada(List<Time> times, Temporada temporada){
        for (Time time : times){
            estatisticaTemporadaTimeService.cadastrarEstatisticasTemporadaTime(time, temporada);
        }
    }

    public List<TimeResponseTabela> mostrarTabelaTemporada(Long  idTemporada){
        return calculoTabelaTemporada.tabelaTemporadas(buscarTemporadaPorId(idTemporada));
    }

    public Temporada buscarTemporadaPorId(Long id){
        try {
            return temporadaRepository.findById(id).orElseThrow();
        } catch (DataIntegrityViolationException e){
            throw new BuscarException("Erro ao buscar Temporada: Dados Inválidos");
        } catch (Exception e) {
            throw new BuscarException("Erro inesperado ao buscar Temporada");
        }
    }

    public void verificarStatusTemporada(Temporada temporada){
        try {
            List<Partida> partidas = partidaService.verificarStatusTemporada(temporada);
            if (!partidas.isEmpty()){
                temporada.setConcluido(true);
                temporadaRepository.save(temporada);
                campeaoTemporadaService.cadastrarCampeao(buscarLiderTabelaTemporada(temporada), temporada);
            }
        } catch (Exception e) {
            throw new BuscarException("Erro ao verificar Temporada");
        }
    }

    public Time buscarLiderTabelaTemporada(Temporada temporada){
        try {
            return calculoTabelaTemporada.buscarLider(temporada);
        } catch (Exception e) {
            throw new BuscarException("Erro ao buscar Temporada");
        }
    }

    public void deletarTemporada(Long idTemporada){
        try {
            temporadaRepository.deleteById(idTemporada);
        } catch (DataIntegrityViolationException e){
            throw new DeletarException("Erro ao deletar Temporada: Dados Inválidos");
        } catch (Exception e) {
            throw new DeletarException("Erro inesperado ao deletar Temporada");
        }
    }

    public boolean verificarConclusaoTemporadas(Campeonato campeonato){
        try {
            List<Temporada> temporadas = temporadaRepository.findByCampeonatoAndConcluido(campeonato,false);
            return temporadas.isEmpty();
        } catch (Exception e) {
            throw new VerificarException("Erro ao verificar Conclusão de Temporadas");
        }
    }
}
