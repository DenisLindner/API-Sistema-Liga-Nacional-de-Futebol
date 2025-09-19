package com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Atleta;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.EstatisticaTemporadaAtleta;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Temporada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstatisticaTemporadaAtletaRepository extends JpaRepository<EstatisticaTemporadaAtleta, Long> {

    Optional<EstatisticaTemporadaAtleta> findByAtletaAndTemporada(Atleta atleta, Temporada temporada);

    List<EstatisticaTemporadaAtleta> findTop5ByTemporadaOrderByQuantidadeGolsDesc(Temporada temporada);

    List<EstatisticaTemporadaAtleta> findTop5ByTemporadaOrderByCartoesAmarelosDesc(Temporada temporada);

    List<EstatisticaTemporadaAtleta> findTop5ByTemporadaOrderByCartoesVermelhosDesc(Temporada temporada);
}
