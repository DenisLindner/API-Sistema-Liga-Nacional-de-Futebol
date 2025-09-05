package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.AtletaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.AtletaResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Atleta;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.AtletaRepository;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.TimeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class AtletaService {

    private AtletaRepository atletaRepository;
    private TimeService timeService;

    public AtletaService(AtletaRepository atletaRepository, TimeService timeService) {
        this.atletaRepository = atletaRepository;
        this.timeService = timeService;
    }

    public AtletaResponse cadastrarAtleta(AtletaRequest dto){
        try {
            Atleta atleta  = new Atleta();
            atleta.setNome(dto.nome());
            atleta.setCpf(dto.cpf());
            atleta.setDataNascimento(dto.dataNascimento());
            atleta.setDataContratacao(dto.dataContratacao());
            atleta.setDataFinalContratacao(dto.dataFinalContratacao());
            atleta.setTime(timeService.buscarTimePorId(dto.idTime()));

            Atleta salvo = atletaRepository.save(atleta);
            return new AtletaResponse(salvo.getId(), salvo.getNome(), salvo.getCpf(), salvo.getDataNascimento(), salvo.getDataContratacao(), salvo.getDataFinalContratacao(), salvo.getQuantidadePartidas(), salvo.getCartoesAmarelos(), salvo.getCartoesVermelhos(), salvo.getQuantidadeGols(), salvo.getTime().getNome());
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar Atleta: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Atleta");
        }
    }
}
