package com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Campeonato;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Temporada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemporadaRepository extends JpaRepository<Temporada,Long> {

    List<Temporada> findByCampeonatoAndConcluido(Campeonato campeonato, boolean concluido);
}
