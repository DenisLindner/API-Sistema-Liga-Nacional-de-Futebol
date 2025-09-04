package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.calculos.CalculoPartidasTemporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.TemporadaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Temporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Time;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.TemporadaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class TemporadaService {

    private PartidaService partidaService;
    private TemporadaRepository temporadaRepository;
    private EstatisticaTemporadaTimeService estatisticaTemporadaTimeService;
    private CampeonatoService campeonatoService;


    public TemporadaService(TemporadaRepository temporadaRepository, PartidaService partidaService, EstatisticaTemporadaTimeService estatisticaTemporadaTimeService, CampeonatoService campeonatoService) {
        this.temporadaRepository = temporadaRepository;
        this.partidaService = partidaService;
        this.estatisticaTemporadaTimeService = estatisticaTemporadaTimeService;
        this.campeonatoService = campeonatoService;
    }

    public Temporada cadastrarTemporada (TemporadaRequest dto){
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

                CalculoPartidasTemporada calculoPartidasTemporada = new CalculoPartidasTemporada(partidaService);
                calculoPartidasTemporada.partidas(salvo);
                cadastrarEstatisticasTemporada(salvo);

                return salvo;
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
}
