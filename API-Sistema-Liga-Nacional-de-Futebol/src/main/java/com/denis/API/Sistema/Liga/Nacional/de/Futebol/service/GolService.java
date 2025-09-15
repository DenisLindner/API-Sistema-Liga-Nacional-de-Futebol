package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.GolRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Gol;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.AtletaRepository;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.GolRepository;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.PartidaRepository;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.TimeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class GolService {

    private GolRepository golRepository;
    private AtletaRepository atletaRepository;
    private TimeRepository timeRepository;
    private PartidaRepository partidaRepository;

    public GolService(GolRepository golRepository,  AtletaRepository atletaRepository, PartidaRepository partidaRepository, TimeRepository timeRepository) {
        this.golRepository = golRepository;
        this.atletaRepository = atletaRepository;
        this.partidaRepository = partidaRepository;
        this.timeRepository = timeRepository;
    }

    public Gol cadastrarGol(GolRequest dto) {
        try {
            Gol gol = new Gol();
            gol.setMinuto(dto.minuto());
            gol.setAtleta(atletaRepository.findById(dto.idAtleta()).orElseThrow());
            gol.setPartida(partidaRepository.findById(dto.idPartida()).orElseThrow());
            gol.setTime(timeRepository.findById(dto.idTime()).orElseThrow());

            return golRepository.save(gol);
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar Gol: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Gol");
        }
    }
}
