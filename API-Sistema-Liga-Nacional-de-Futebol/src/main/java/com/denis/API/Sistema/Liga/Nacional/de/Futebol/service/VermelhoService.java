package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.VermelhoRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Partida;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Vermelho;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.AtletaRepository;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.PartidaRepository;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.VermelhoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class VermelhoService {

    private VermelhoRepository vermelhoRepository;
    private AtletaRepository atletaRepository;

    public VermelhoService(VermelhoRepository vermelhoRepository, AtletaRepository atletaRepository) {
        this.vermelhoRepository = vermelhoRepository;
        this.atletaRepository = atletaRepository;
    }

    public Vermelho cadastrarVermelho(VermelhoRequest dto, Partida partida) {
        try {
            Vermelho vermelho = new Vermelho();
            vermelho.setMinuto(dto.minuto());
            vermelho.setAtleta(atletaRepository.findById(dto.idAtleta()).orElseThrow());
            vermelho.setTime(vermelho.getAtleta().getTime());
            vermelho.setPartida(partida);

            return vermelhoRepository.save(vermelho);
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar cartão Vermelho: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar cartão Vermelho");
        }
    }
}
