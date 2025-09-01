package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.NoticiaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.NoticiaResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Noticia;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.NoticiaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class NoticiaService {

    private NoticiaRepository noticiaRepository;

    public NoticiaService(NoticiaRepository noticiaRepository) {this.noticiaRepository = noticiaRepository;}

    public NoticiaResponse cadastrarNoticia (NoticiaRequest dto){
        try {
            Noticia noticia = new Noticia();
            BeanUtils.copyProperties(dto, noticia);

            Noticia salvo = noticiaRepository.save(noticia);

            return new NoticiaResponse(salvo.getId(), salvo.getAutor(), salvo.getTitulo(), salvo.getDescricao(), salvo.getConteudo(), salvo.getEmpresa().getNomeEmpresarial());
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar Noticia: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Noticia");
        }
    }
}
