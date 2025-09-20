package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.BuscarException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.EstatisticaTemporadaAtletaResponseAmarelos;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.EstatisticaTemporadaAtletaResponseGols;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.EstatisticaTemporadaAtletaResponseVermelhos;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Atleta;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.EstatisticaTemporadaAtleta;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Temporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.EstatisticaTemporadaAtletaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstatisticaTemporadaAtletaService {

    private EstatisticaTemporadaAtletaRepository estatisticaTemporadaAtletaRepository;
    private AtletaService atletaService;
    private TemporadaService temporadaService;

    public EstatisticaTemporadaAtletaService(EstatisticaTemporadaAtletaRepository estatisticaTemporadaAtletaRepository,  AtletaService atletaService,  TemporadaService temporadaService) {
        this.estatisticaTemporadaAtletaRepository = estatisticaTemporadaAtletaRepository;
        this.atletaService = atletaService;
        this.temporadaService = temporadaService;
    }

    public EstatisticaTemporadaAtleta cadastrarEstatisticaTemporadaAtleta(Atleta atleta, Temporada temporada) {
        try {
            return estatisticaTemporadaAtletaRepository.save(new EstatisticaTemporadaAtleta(atleta, temporada));
        } catch (Exception e) {
            throw new CadastroException("Erro ao cadastrar Estatistica Temporada Atleta");
        }
    }

    public List<EstatisticaTemporadaAtletaResponseGols> buscarTop5GolsTemporada(Long id) {
        try {
            List<EstatisticaTemporadaAtleta> estatisticaTemporadaAtletas = estatisticaTemporadaAtletaRepository.findTop5ByTemporadaOrderByQuantidadeGolsDesc(temporadaService.buscarTemporadaPorId(id));
            List<EstatisticaTemporadaAtletaResponseGols> estatisticaTemporadaAtletaResponse = new ArrayList<>();
            for (EstatisticaTemporadaAtleta estatisticaTemporadaAtleta : estatisticaTemporadaAtletas) {
                if (estatisticaTemporadaAtleta.getQuantidadeGols() > 0){
                    estatisticaTemporadaAtletaResponse.add(new EstatisticaTemporadaAtletaResponseGols(estatisticaTemporadaAtleta.getId(), estatisticaTemporadaAtleta.getAtleta().getNome(), estatisticaTemporadaAtleta.getTemporada().getNome(), estatisticaTemporadaAtleta.getQuantidadeGols()));
                }
            }
            return estatisticaTemporadaAtletaResponse;
        } catch (Exception e) {
            throw new BuscarException("Erro ao buscar Top 5 de Gols Temporada");
        }
    }

    public List<EstatisticaTemporadaAtletaResponseAmarelos> buscarTop5AmarelosTemporada(Long id) {
        try {
            List<EstatisticaTemporadaAtleta> estatisticaTemporadaAtletas = estatisticaTemporadaAtletaRepository.findTop5ByTemporadaOrderByCartoesAmarelosDesc(temporadaService.buscarTemporadaPorId(id));
            List<EstatisticaTemporadaAtletaResponseAmarelos>  estatisticaTemporadaAtletaResponse = new ArrayList<>();
            for (EstatisticaTemporadaAtleta estatisticaTemporadaAtleta : estatisticaTemporadaAtletas) {
                if (estatisticaTemporadaAtleta.getCartoesAmarelos() > 0){
                    estatisticaTemporadaAtletaResponse.add(new EstatisticaTemporadaAtletaResponseAmarelos(estatisticaTemporadaAtleta.getId(), estatisticaTemporadaAtleta.getAtleta().getNome(), estatisticaTemporadaAtleta.getTemporada().getNome(), estatisticaTemporadaAtleta.getCartoesAmarelos()));
                }
            }
            return estatisticaTemporadaAtletaResponse;
        } catch (Exception e) {
            throw new BuscarException("Erro ao buscar Top 5 de Amarelos Temporada");
        }
    }

    public List<EstatisticaTemporadaAtletaResponseVermelhos> buscarTop5VermelhosTemporada(Long id) {
        try {
            List<EstatisticaTemporadaAtleta> estatisticaTemporadaAtletas = estatisticaTemporadaAtletaRepository.findTop5ByTemporadaOrderByCartoesVermelhosDesc(temporadaService.buscarTemporadaPorId(id));
            List<EstatisticaTemporadaAtletaResponseVermelhos>  estatisticaTemporadaAtletaResponse = new ArrayList<>();
            for (EstatisticaTemporadaAtleta estatisticaTemporadaAtleta : estatisticaTemporadaAtletas) {
                if (estatisticaTemporadaAtleta.getCartoesVermelhos() > 0){
                    estatisticaTemporadaAtletaResponse.add(new EstatisticaTemporadaAtletaResponseVermelhos(estatisticaTemporadaAtleta.getId(), estatisticaTemporadaAtleta.getAtleta().getNome(), estatisticaTemporadaAtleta.getTemporada().getNome(), estatisticaTemporadaAtleta.getCartoesVermelhos()));
                }
            }
            return estatisticaTemporadaAtletaResponse;
        } catch (Exception e) {
            throw new BuscarException("Erro ao buscar Top 5 de Vermelhos Temporada");
        }
    }

    public EstatisticaTemporadaAtleta buscarPorAtletaETemporada(Atleta atleta, Temporada temporada) {
        try {
            EstatisticaTemporadaAtleta estatisticaTemporadaAtleta = estatisticaTemporadaAtletaRepository.findByAtletaAndTemporada(atleta, temporada).orElse(null);
            if (estatisticaTemporadaAtleta == null) {
                estatisticaTemporadaAtleta = cadastrarEstatisticaTemporadaAtleta(atleta, temporada);
            }
            return estatisticaTemporadaAtleta;
        }catch (Exception e) {
            throw new BuscarException("Erro ao buscar Estatistica Temporada");
        }
    }

    public void atualizarGolsAtleta(EstatisticaTemporadaAtleta estatisticaTemporadaAtleta) {
        try {
            estatisticaTemporadaAtleta.setQuantidadeGols(estatisticaTemporadaAtleta.getQuantidadeGols()+1);
            atletaService.atualizarGols(estatisticaTemporadaAtleta.getAtleta());
            estatisticaTemporadaAtletaRepository.save(estatisticaTemporadaAtleta);
        } catch (Exception e){
            throw new CadastroException("Erro ao atualizar Gols Temporada");
        }
    }

    public void atualizarAmarelosAtleta(EstatisticaTemporadaAtleta estatisticaTemporadaAtleta) {
        try {
            estatisticaTemporadaAtleta.setCartoesAmarelos(estatisticaTemporadaAtleta.getCartoesAmarelos()+1);
            atletaService.atualizarAmarelos(estatisticaTemporadaAtleta.getAtleta());
            estatisticaTemporadaAtletaRepository.save(estatisticaTemporadaAtleta);
        } catch (Exception e){
            throw new CadastroException("Erro ao atualizar Gols Temporada");
        }
    }

    public void atualizarVermelhosAtleta(EstatisticaTemporadaAtleta estatisticaTemporadaAtleta) {
        try {
            estatisticaTemporadaAtleta.setCartoesVermelhos(estatisticaTemporadaAtleta.getCartoesVermelhos()+1);
            atletaService.atualizarVermelhos(estatisticaTemporadaAtleta.getAtleta());
            estatisticaTemporadaAtletaRepository.save(estatisticaTemporadaAtleta);
        } catch (Exception e){
            throw new CadastroException("Erro ao atualizar Gols Temporada");
        }
    }


}
