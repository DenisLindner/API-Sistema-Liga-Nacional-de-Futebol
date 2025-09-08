package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.calculos.CalculoPartidasTemporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.calculos.CalculoTabelaTemporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.BuscarException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.PartidaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.TemporadaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.TemporadaResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.TimeResponseTabela;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Temporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Time;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.TemporadaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemporadaService {

    private final CalculoTabelaTemporada calculoTabelaTemporada;
    private TemporadaRepository temporadaRepository;
    private EstatisticaTemporadaTimeService estatisticaTemporadaTimeService;
    private CampeonatoService campeonatoService;
    private CalculoPartidasTemporada calculoPartidasTemporada;
    private PartidaService partidaService;


    public TemporadaService(TemporadaRepository temporadaRepository, EstatisticaTemporadaTimeService estatisticaTemporadaTimeService, CampeonatoService campeonatoService, CalculoPartidasTemporada calculoPartidasTemporada, PartidaService partidaService, CalculoTabelaTemporada calculoTabelaTemporada) {
        this.temporadaRepository = temporadaRepository;
        this.estatisticaTemporadaTimeService = estatisticaTemporadaTimeService;
        this.campeonatoService = campeonatoService;
        this.calculoPartidasTemporada = calculoPartidasTemporada;
        this.partidaService = partidaService;
        this.calculoTabelaTemporada = calculoTabelaTemporada;
    }

    public TemporadaResponse cadastrarTemporada (TemporadaRequest dto){
        try {
            Temporada temporada = new Temporada();
            temporada.setNome(dto.nome());
            temporada.setDataInicio(dto.dataInicio());
            temporada.setDataFim(dto.dataFim());
            temporada.setCampeonato(campeonatoService.buscarCampeonatoPorId(dto.idCampeonato()));

            if(temporada.getCampeonato().getTimes().isEmpty()){
                throw new CadastroException("Erro ao Cadastrar Temporada: Nenhum de Time Cadastrado");
            } else if (temporada.getCampeonato().getTimes().size() != 20){
                throw new CadastroException("Erro ao Cadastrar Temporada: Quantidade de Times Inválida");
            } else {
                Temporada salvo = temporadaRepository.save(temporada);

                List<PartidaRequest> partidas = calculoPartidasTemporada.partidas(salvo);
                for (PartidaRequest partida : partidas) {
                    partidaService.cadastrarPartida(partida);
                }

                cadastrarEstatisticasTemporada(salvo);

                return new TemporadaResponse(salvo.getId(), salvo.isConcluido(), salvo.getNome(), salvo.getDataInicio(), salvo.getDataFim(), salvo.getCampeonato().getNome());
            }
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
}
