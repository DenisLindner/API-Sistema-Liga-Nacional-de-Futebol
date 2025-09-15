package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.VerificarException;
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

    public void aumentarQuantidadeAmarelos(int qtdCartoes, Campeonato campeonato) throws Exception {
        if (qtdCartoes < 0) {
            throw new CadastroException("Erro ao aumentar quantidade de amarelos: quantidade inválida");
        }
        EstatisticasCampeonato estatisticasCampeonato = estatisticasCampeonatoRepository.findByCampeonato(campeonato);
        estatisticasCampeonato.setCartoesAmarelos(estatisticasCampeonato.getCartoesAmarelos() + qtdCartoes);
        estatisticasCampeonatoRepository.save(estatisticasCampeonato);
    }

    public void aumentarQuantidadeVermelhos(int qtdCartoes,Campeonato campeonato) throws Exception {
        if (qtdCartoes < 0) {
            throw new CadastroException("Erro ao aumentar quantidade de amarelos: quantidade inválida");
        }
        EstatisticasCampeonato estatisticasCampeonato = estatisticasCampeonatoRepository.findByCampeonato(campeonato);
        estatisticasCampeonato.setCartoesAmarelos(estatisticasCampeonato.getCartoesAmarelos() + qtdCartoes);
        estatisticasCampeonatoRepository.save(estatisticasCampeonato);
    }

    public void aumentarQuantidadeGols(int qtdGols, Campeonato campeonato) throws Exception {
        if(qtdGols < 0 || qtdGols > 3){
            throw new VerificarException("Erro ao aumentar quantidade de gols: quantidade inválida");
        }
        EstatisticasCampeonato estatisticasCampeonato = estatisticasCampeonatoRepository.findByCampeonato(campeonato);
        estatisticasCampeonato.setGolsFeitos(estatisticasCampeonato.getGolsFeitos() + qtdGols);
        estatisticasCampeonatoRepository.save(estatisticasCampeonato);
    }

    public void aumentarQuantidadePontos(int pontos, Campeonato campeonato) throws Exception {
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
