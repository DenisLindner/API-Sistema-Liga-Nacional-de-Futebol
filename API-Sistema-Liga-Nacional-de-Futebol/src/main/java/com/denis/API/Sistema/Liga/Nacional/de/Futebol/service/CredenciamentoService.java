package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.CampeonatoRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.CampeonatoResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.CredenciamentoRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.CredenciamentoResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Campeonato;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Credenciamento;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.CredenciamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CredenciamentoService {

    private CredenciamentoRepository credenciamentoRepository;

    public CredenciamentoService(CredenciamentoRepository credenciamentoRepository) {this.credenciamentoRepository = credenciamentoRepository;}

    public CredenciamentoResponse cadastrarCredenciamento(CredenciamentoRequest dto){
        try {
            Credenciamento credenciamento = new Credenciamento();
            BeanUtils.copyProperties(dto, credenciamento);

            Credenciamento salvo = credenciamentoRepository.save(credenciamento);

            return new CredenciamentoResponse(salvo.getId(), salvo.getCpf(), salvo.getCrtJornalista(), salvo.getNome(), salvo.getEmpresa().getNomeEmpresarial(), salvo.getPartida().getId());
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao solicitar Credenciamento: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao solicitar Credenciamento");
        }
    }
}
