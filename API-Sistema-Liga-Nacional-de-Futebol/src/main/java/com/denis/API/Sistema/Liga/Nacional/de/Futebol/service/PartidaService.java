package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.BuscarException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.PartidaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.PartidaResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Partida;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Sumula;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Temporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.PartidaRepository;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.TemporadaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartidaService {

    private TemporadaRepository temporadaRepository;
    private PartidaRepository partidaRepository;

    public PartidaService(PartidaRepository partidaRepository, TemporadaRepository temporadaRepository) {this.partidaRepository = partidaRepository;
        this.temporadaRepository = temporadaRepository;
    }

    public PartidaResponse cadastrarPartida (PartidaRequest dto){
        try {
            Partida partida = new Partida();
            BeanUtils.copyProperties(dto, partida);

            Partida salvo = partidaRepository.save(partida);

            return new PartidaResponse(salvo.getId(), salvo.getRodada(), salvo.getDataHora(), salvo.getTimeMandante().getNome(), salvo.getTimeVisitante().getNome(), salvo.getTemporada().getNome(), String.valueOf(salvo.isConcluido()));
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar Partida: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Partida");
        }
    }

    public List<PartidaResponse> listarPartidaPorRodadaECampeonato(Long idTemporada, int rodada){
        try {
            Temporada temporada = temporadaRepository.findById(idTemporada).orElseThrow();

            List<Partida> partidas = partidaRepository.findAllByTemporadaAndRodada(temporada, rodada);
            List<PartidaResponse> partidasResponse = new ArrayList<>();
            for (Partida partida : partidas) {
                partidasResponse.add(new PartidaResponse(partida.getId(), partida.getRodada(), partida.getDataHora(), partida.getTimeMandante().getNome(), partida.getTimeVisitante().getNome(), partida.getTemporada().getNome(), String.valueOf(partida.isConcluido())));
            }
            return partidasResponse;
        } catch (DataIntegrityViolationException e){
            throw new BuscarException("Erro ao buscar Partidas: Dados Inválidos");
        } catch (Exception e) {
            throw new BuscarException("Erro inesperado ao buscar Partidas");
        }
    }

    public Partida buscarPartidaPorId(Long id){
        try {
            return partidaRepository.findById(id).orElseThrow();
        } catch (DataIntegrityViolationException e){
            throw new BuscarException("Erro ao buscar Partida: Dados Inválidos");
        } catch (Exception e) {
            throw new BuscarException("Erro inesperado ao buscar Partida");
        }
    }

    public Partida atualizarPartida(Sumula sumula){
        try {
            Partida partida = sumula.getPartida();
            partida.setConcluido(true);
            partida.setGolsMandante(sumula.getGolsMandante());
            partida.setGolsVisitante(sumula.getGolsVisitante());
            return partidaRepository.save(partida);
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao atualizar Partida: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao atualizar Partida");
        }
    }

    public List<Partida> verificarStatusTemporada(Temporada temporada){
        try {
            return partidaRepository.findTop2ByTemporadaAndConcluido(temporada, false);
        } catch (Exception e) {
            throw new BuscarException("Erro ao buscar Partidas");
        }
    }
}
