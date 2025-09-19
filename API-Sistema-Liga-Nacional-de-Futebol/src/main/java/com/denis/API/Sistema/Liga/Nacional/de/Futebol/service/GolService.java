package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.GolRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Atleta;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Gol;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Partida;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.AtletaRepository;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.GolRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class GolService {

    private GolRepository golRepository;
    private AtletaRepository atletaRepository;

    public GolService(GolRepository golRepository,  AtletaRepository atletaRepository) {
        this.golRepository = golRepository;
        this.atletaRepository = atletaRepository;
    }

    public Gol cadastrarGol(GolRequest dto, Partida partida) {
        try {
            Gol gol = new Gol();
            gol.setMinuto(dto.minuto());
            Atleta atleta = atletaRepository.findById(dto.idAtleta()).orElseThrow();
            gol.setAtleta(atleta);
            gol.setPartida(partida);
            gol.setTime(atleta.getTime());

            return golRepository.save(gol);
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar Gol: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Gol");
        }
    }
}
