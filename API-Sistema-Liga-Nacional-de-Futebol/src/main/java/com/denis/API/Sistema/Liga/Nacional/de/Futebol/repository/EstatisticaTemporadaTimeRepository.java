package com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.EstatisticaTemporadaTime;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Temporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstatisticaTemporadaTimeRepository extends JpaRepository<EstatisticaTemporadaTime,Long> {

    EstatisticaTemporadaTime findByTimeAndTemporada(Time time, Temporada temporada) throws Exception;
}
