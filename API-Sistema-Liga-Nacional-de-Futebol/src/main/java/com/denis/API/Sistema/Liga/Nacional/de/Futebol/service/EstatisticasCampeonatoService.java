package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.VerificarException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.EstatisticasCampeonatoResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.*;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.EstatisticasCampeonatoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class EstatisticasCampeonatoService {

    private EstatisticasCampeonatoRepository estatisticasCampeonatoRepository;

    public EstatisticasCampeonatoService (EstatisticasCampeonatoRepository estatisticasCampeonatoRepository){this.estatisticasCampeonatoRepository = estatisticasCampeonatoRepository;}

    public void cadastrarEstatisticasCampeonato(Campeonato campeonato){
        try {
            EstatisticasCampeonato estatisticasCampeonato = new EstatisticasCampeonato(campeonato);

            EstatisticasCampeonato salvo = estatisticasCampeonatoRepository.save(estatisticasCampeonato);
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar Estatisticas do Campeonato: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Estatisticas do Campeonato");
        }
    }

    public void aumentarQuantidadeAmarelos(Campeonato campeonato) throws Exception {
        EstatisticasCampeonato estatisticasCampeonato = estatisticasCampeonatoRepository.findByCampeonato(campeonato);
        estatisticasCampeonato.setCartoesAmarelos(estatisticasCampeonato.getCartoesAmarelos() + 1);
        estatisticasCampeonatoRepository.save(estatisticasCampeonato);
    }

    public void aumentarQuantidadeVermelhos(Campeonato campeonato) throws Exception {
        EstatisticasCampeonato estatisticasCampeonato = estatisticasCampeonatoRepository.findByCampeonato(campeonato);
        estatisticasCampeonato.setCartoesAmarelos(estatisticasCampeonato.getCartoesAmarelos() + 1);
        estatisticasCampeonatoRepository.save(estatisticasCampeonato);
    }

    public void aumentarQuantidadeGols(Campeonato campeonato, int qtdGols) throws Exception {
        if(qtdGols < 0 || qtdGols > 3){
            throw new VerificarException("Erro ao aumentar quantidade de gols: quantidade inválida");
        }
        EstatisticasCampeonato estatisticasCampeonato = estatisticasCampeonatoRepository.findByCampeonato(campeonato);
        estatisticasCampeonato.setGolsFeitos(estatisticasCampeonato.getGolsFeitos() + qtdGols);
        estatisticasCampeonatoRepository.save(estatisticasCampeonato);
    }

    public void aumentarQuantidadePontos(Campeonato campeonato, int pontos) throws Exception {
        if(pontos < 0 || pontos > 3){
            throw new VerificarException("Erro ao aumentar quantidade de pontos: quantidade inválida");
        }
        EstatisticasCampeonato estatisticasCampeonato = estatisticasCampeonatoRepository.findByCampeonato(campeonato);
        estatisticasCampeonato.setPontos(estatisticasCampeonato.getPontos() + pontos);
        estatisticasCampeonatoRepository.save(estatisticasCampeonato);
    }

    public void aumentarQuantidadePartidas(Campeonato campeonato) throws Exception {
        EstatisticasCampeonato estatisticasCampeonato = estatisticasCampeonatoRepository.findByCampeonato(campeonato);
        estatisticasCampeonato.setQuantidadePartidas(estatisticasCampeonato.getQuantidadePartidas() + 1);
        estatisticasCampeonatoRepository.save(estatisticasCampeonato);
    }
}
