package com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Partida;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Temporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida,Long> {

    List<Partida> findAllByTemporadaAndRodada(Temporada temporada, int rodada);

    List<Partida> findTop5ByTimeMandanteOrTimeVisitanteAndDataHoraAfterOrderByDataHoraAsc(Time timeMandante, Time timeVisitante, LocalDateTime dataHora);

    List<Partida> findTop2ByTemporadaAndConcluido(Temporada temporada,boolean concluido);
}
