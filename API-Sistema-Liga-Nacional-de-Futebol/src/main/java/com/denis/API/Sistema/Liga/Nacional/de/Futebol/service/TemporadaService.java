package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.TemporadaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.TemporadaResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Temporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.TemporadaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class TemporadaService {

    private TemporadaRepository temporadaRepository;

    public TemporadaService(TemporadaRepository temporadaRepository) {this.temporadaRepository = temporadaRepository;}

    public TemporadaResponse cadastrarTemporada (TemporadaRequest dto){
        try {
            Temporada temporada = new Temporada();
            BeanUtils.copyProperties(dto, temporada);

            Temporada salvo = temporadaRepository.save(temporada);

            return new TemporadaResponse(salvo.getId(), salvo.isConcluido(), salvo.getNome(), salvo.getDataInicio(), salvo.getDataFim(), salvo.getCampeonato().getNome());
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar Temporada: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Temporada");
        }
    }
}
