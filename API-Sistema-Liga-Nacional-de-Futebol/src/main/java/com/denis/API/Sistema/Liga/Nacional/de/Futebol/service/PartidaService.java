package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.PartidaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.PartidaResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Partida;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.PartidaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {

    private PartidaRepository partidaRepository;

    public PartidaService(PartidaRepository partidaRepository) {this.partidaRepository = partidaRepository;}

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
}
