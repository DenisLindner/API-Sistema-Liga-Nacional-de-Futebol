package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.BuscarException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.NoticiaRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.NoticiaResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Empresa;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Noticia;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.EmpresaRepository;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.NoticiaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticiaService {

    private NoticiaRepository noticiaRepository;
    private EmpresaRepository empresaRepository;

    public NoticiaService(NoticiaRepository noticiaRepository, EmpresaRepository empresaRepository) {
        this.noticiaRepository = noticiaRepository;
        this.empresaRepository = empresaRepository;
    }

    public NoticiaResponse cadastrarNoticia (NoticiaRequest dto){
        try {
            Noticia noticia = new Noticia();
            BeanUtils.copyProperties(dto, noticia);
            Empresa empresa = empresaRepository.findById(dto.idEmpresa()).orElseThrow();
            noticia.setEmpresa(empresa);

            Noticia salvo = noticiaRepository.save(noticia);

            return new NoticiaResponse(salvo.getId(), salvo.getAutor(), salvo.getTitulo(), salvo.getDescricao(), salvo.getConteudo(), salvo.getEmpresa().getNomeEmpresarial());
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar Noticia: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Noticia");
        }
    }

    public List<NoticiaResponse> ultimas10Noticias(){
        try {
            List<Noticia> noticias = noticiaRepository.findTop10ByOrderByIdDesc();

            List<NoticiaResponse> noticiasResponse = new ArrayList<>();
            for (Noticia noticia : noticias) {
                noticiasResponse.add(new NoticiaResponse(noticia.getId(), noticia.getAutor(), noticia.getTitulo(), noticia.getDescricao(), noticia.getConteudo(), noticia.getEmpresa().getNomeFantasia()));
            }
            return noticiasResponse;
        } catch (Exception e){
            throw new BuscarException("Erro ao Buscar Últimas 10 Noticias");
        }
    }

    public List<NoticiaResponse> buscarNoticiasTitulo(String titulo){
        try {
            List<Noticia> noticias = noticiaRepository.findTop5ByTituloContaining(titulo);
            List<NoticiaResponse> noticiasResponse = new ArrayList<>();

            for (Noticia noticia : noticias) {
                noticiasResponse.add(new NoticiaResponse(noticia.getId(), noticia.getAutor(), noticia.getTitulo(), noticia.getDescricao(), noticia.getConteudo(), noticia.getEmpresa().getNomeFantasia()));
            }
            return noticiasResponse;
        } catch (DataIntegrityViolationException e){
            throw new BuscarException("Erro ao Buscar Noticias: Dados Inválidos");
        } catch (Exception e){
            throw new BuscarException("Erro ao Buscar Notícias pelo nome");
        }
    }
}
