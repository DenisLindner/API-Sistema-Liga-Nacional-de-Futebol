package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.EstatisticasCampeonatoResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Campeonato;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.EstatisticasCampeonato;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.EstatisticasCampeonatoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class EstatisticasCampeonatoService {

    private EstatisticasCampeonatoRepository estatisticasCampeonatoRepository;

    public EstatisticasCampeonatoService (EstatisticasCampeonatoRepository estatisticasCampeonatoRepository){this.estatisticasCampeonatoRepository = estatisticasCampeonatoRepository;}

    public EstatisticasCampeonatoResponse cadastrarEstatisticasCampeonato(Campeonato campeonato){
        try {
            EstatisticasCampeonato estatisticasCampeonato = new EstatisticasCampeonato(campeonato);

            EstatisticasCampeonato salvo = estatisticasCampeonatoRepository.save(estatisticasCampeonato);

            return new EstatisticasCampeonatoResponse(salvo.getId(), salvo.getPontos(), salvo.getQuantidadePartidas(), salvo.getGolsFeitos(), salvo.getCartoesAmarelos(), salvo.getCartoesVermelhos(), salvo.getCampeonato().getNome());
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar Estatisticas do Campeonato: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Estatisticas do Campeonato");
        }
    }
}
