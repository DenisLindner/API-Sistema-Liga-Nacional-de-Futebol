package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.CampeonatoRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.CampeonatoResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Campeonato;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.CampeonatoRepository;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.EstatisticasCampeonatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CampeonatoService {
    private CampeonatoRepository campeonatoRepository;
    private EstatisticasCampeonatoService estatisticasCampeonatoService;

    public CampeonatoService(CampeonatoRepository campeonatoRepository, EstatisticasCampeonatoService estatisticasCampeonatoService) {
        this.campeonatoRepository = campeonatoRepository;
        this.estatisticasCampeonatoService = estatisticasCampeonatoService;
    }

    public Campeonato cadastrarCampeonato(CampeonatoRequest dto){
        try {
            Campeonato campeonato  = new Campeonato();
            BeanUtils.copyProperties(dto, campeonato);

            Campeonato salvo = campeonatoRepository.save(campeonato);

            estatisticasCampeonatoService.cadastrarEstatisticasCampeonato(salvo);

            return salvo;
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar Campeonato: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Campeonato");
        }
    }

    public Campeonato buscarCampeonatoPorId(Long id){
        try {
            return campeonatoRepository.findById(id).orElseThrow();
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar Campeonato: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Campeonato");
        }
    }
}
