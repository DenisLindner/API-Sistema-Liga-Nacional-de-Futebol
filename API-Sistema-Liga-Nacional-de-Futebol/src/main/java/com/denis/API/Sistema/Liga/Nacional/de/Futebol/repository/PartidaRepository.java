package com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Partida;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Temporada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida,Long> {

    List<Partida> findAllByTemporadaAndRodada(Temporada temporada, int rodada);
}
