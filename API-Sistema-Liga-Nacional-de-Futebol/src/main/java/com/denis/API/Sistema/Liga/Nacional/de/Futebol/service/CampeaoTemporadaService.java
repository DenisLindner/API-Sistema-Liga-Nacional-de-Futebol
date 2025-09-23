package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.BuscarException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.CampeaoTemporadaResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.CampeaoTemporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Temporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Time;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.CampeaoTemporadaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampeaoTemporadaService {

    private CampeaoTemporadaRepository campeaoTemporadaRepository;

    public CampeaoTemporadaService(CampeaoTemporadaRepository campeaoTemporadaRepository) {
        this.campeaoTemporadaRepository = campeaoTemporadaRepository;
    }

    public void cadastrarCampeao(Time time, Temporada temporada){
        try {
            campeaoTemporadaRepository.save(new CampeaoTemporada(time, temporada));
        } catch (DataIntegrityViolationException e) {
            throw new CadastroException("Erro ao cadastrar Campeao Temporada: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro ao cadastrar Campeao Temporada: " + e.getMessage());
        }
    }

    public CampeaoTemporadaResponse buscarCampeaoTemporada(Long idTemporada){
        try {
            CampeaoTemporada campeaoTemporada = campeaoTemporadaRepository.findByTemporada_Id(idTemporada);
            if(campeaoTemporada == null){
                return null;
            }
            return new CampeaoTemporadaResponse(campeaoTemporada.getId(), campeaoTemporada.getTime().getNome(), campeaoTemporada.getTemporada().getNome(), campeaoTemporada.getTemporada().getDataInicio().getYear());
        } catch (Exception e) {
            throw new BuscarException("Erro ao buscar Campeao Temporada: " + e.getMessage());
        }
    }

    public List<CampeaoTemporadaResponse> buscarCampeoesTemporadas(){
        try {
            List<CampeaoTemporada> campeaoTemporadas = campeaoTemporadaRepository.findAll();
            List<CampeaoTemporadaResponse> campeaoTemporadasResponse = new ArrayList<>();
            for (CampeaoTemporada campeaoTemporada : campeaoTemporadas) {
                campeaoTemporadasResponse.add(new CampeaoTemporadaResponse(campeaoTemporada.getId(), campeaoTemporada.getTime().getNome(), campeaoTemporada.getTemporada().getNome(), campeaoTemporada.getTemporada().getDataInicio().getYear()));
            }
            return campeaoTemporadasResponse;
        } catch (Exception e) {
            throw new BuscarException("Erro ao buscar Campeoes Temporadas: " + e.getMessage());
        }
    }
}
