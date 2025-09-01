package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.EmpresaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.EmpresaResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Empresa;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.EmpresaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    private EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public EmpresaResponse cadastrarEmpresa(EmpresaRequest dto){
        try {
            Empresa empresa = new Empresa();
            BeanUtils.copyProperties(dto, empresa);

            Empresa salvo = empresaRepository.save(empresa);

            return new EmpresaResponse(salvo.getId(), salvo.getNomeEmpresarial(), salvo.getNomeFantasia(), salvo.getCnpj(), salvo.getTelefone());
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar Empresa: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Empresa");
        }
    }
}
