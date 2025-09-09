package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.GolRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.GolResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Gol;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.AtletaRepository;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.GolRepository;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.PartidaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class GolService {

    private GolRepository golRepository;
    private AtletaRepository atletaRepository;
    private PartidaRepository partidaRepository;

    public GolService(GolRepository golRepository,  AtletaRepository atletaRepository, PartidaRepository partidaRepository) {
        this.golRepository = golRepository;
        this.atletaRepository = atletaRepository;
        this.partidaRepository = partidaRepository;
    }

    public GolResponse cadastrarGol(GolRequest dto) {
        try {
            Gol gol = new Gol();
            gol.setMinuto(dto.minuto());
            gol.setAtleta(atletaRepository.findById(dto.idAtleta()).orElseThrow());
            gol.setPartida(partidaRepository.findById(dto.idPartida()).orElseThrow());

            Gol salvo = golRepository.save(gol);

            return new GolResponse(salvo.getId(), salvo.getMinuto(), salvo.getAtleta().getNome(), salvo.getAtleta().getTime().getNome());
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao solicitar Credenciamento: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao solicitar Credenciamento");
        }
    }
}
