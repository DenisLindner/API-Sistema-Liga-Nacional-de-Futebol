package com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Campeonato;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.EstatisticasCampeonato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstatisticasCampeonatoRepository extends JpaRepository<EstatisticasCampeonato,Long> {

    EstatisticasCampeonato findByCampeonato(Campeonato campeonato) throws Exception;
}
