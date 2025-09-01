package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.AtletaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.AtletaResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Atleta;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.AtletaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class AtletaService {

    private AtletaRepository atletaRepository;

    public AtletaService(AtletaRepository atletaRepository) {this.atletaRepository = atletaRepository;}

    public AtletaResponse cadastrarAtleta(AtletaRequest dto){
        try {
            Atleta atleta  = new Atleta();
            BeanUtils.copyProperties(dto, atleta);

            Atleta salvo = atletaRepository.save(atleta);

            return new AtletaResponse(salvo.getId(), salvo.getNome(), salvo.getCpf(), salvo.getDataNascimento(), salvo.getDataContratacao(), salvo.getDataFinalContratacao(), salvo.getQuantidadePartidas(), salvo.getCartoesAmarelos(), salvo.getCartoesVermelhos(), salvo.getQuantidadeGols(), salvo.getTime().getNome());
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar Atleta: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Atleta");
        }
    }
}
