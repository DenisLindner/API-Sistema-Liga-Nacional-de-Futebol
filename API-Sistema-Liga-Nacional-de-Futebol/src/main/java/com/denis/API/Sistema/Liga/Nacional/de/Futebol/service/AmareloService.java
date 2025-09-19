package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.AmareloRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Amarelo;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Partida;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.AmareloRepository;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.AtletaRepository;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.PartidaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class AmareloService {

    private AmareloRepository amareloRepository;
    private AtletaRepository atletaRepository;

    public AmareloService(AmareloRepository amareloRepository, AtletaRepository atletaRepository) {
        this.amareloRepository = amareloRepository;
        this.atletaRepository = atletaRepository;
    }

    public Amarelo cadastrarAmarelo(AmareloRequest dto, Partida partida) {
        try {
            Amarelo amarelo = new Amarelo();
            amarelo.setMinuto(dto.minuto());
            amarelo.setAtleta(atletaRepository.findById(dto.idAtleta()).orElseThrow());
            amarelo.setTime(amarelo.getAtleta().getTime());
            amarelo.setPartida(partida);

            return amareloRepository.save(amarelo);
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar cartão Amarelo: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar cartão Amarelo");
        }
    }
}
