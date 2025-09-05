package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.BuscarException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.PartidaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.PartidaResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Partida;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Temporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.PartidaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartidaService {

    private final TemporadaService temporadaService;
    private PartidaRepository partidaRepository;

    public PartidaService(PartidaRepository partidaRepository, TemporadaService temporadaService) {this.partidaRepository = partidaRepository;
        this.temporadaService = temporadaService;
    }

    public PartidaResponse cadastrarPartida (PartidaRequest dto){
        try {
            Partida partida = new Partida();
            BeanUtils.copyProperties(dto, partida);

            Partida salvo = partidaRepository.save(partida);

            return new PartidaResponse(salvo.getId(), salvo.getRodada(), salvo.getDataHora(), salvo.getTimeMandante().getNome(), salvo.getTimeVisitante().getNome(), salvo.getTemporada().getNome());
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar Partida: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Partida");
        }
    }

    public List<PartidaResponse> listarPartidaPorRodadaECampeonato(Long idTemporada, int rodada){
        try {
            Temporada temporada = temporadaService.buscarTemporadaPorId(idTemporada);

            List<Partida> partidas = partidaRepository.findAllByTemporadaAndRodada(temporada, rodada);
            List<PartidaResponse> partidasResponse = new ArrayList<>();
            for (Partida partida : partidas) {
                partidasResponse.add(new PartidaResponse(partida.getId(), partida.getRodada(), partida.getDataHora(), partida.getTimeMandante().getNome(), partida.getTimeVisitante().getNome(), partida.getTemporada().getNome()))
            }
            return partidasResponse;
        } catch (DataIntegrityViolationException e){
            throw new BuscarException("Erro ao buscar Partidas: Dados Inválidos");
        } catch (Exception e) {
            throw new BuscarException("Erro inesperado ao buscar Partidas");
        }
    }
}
