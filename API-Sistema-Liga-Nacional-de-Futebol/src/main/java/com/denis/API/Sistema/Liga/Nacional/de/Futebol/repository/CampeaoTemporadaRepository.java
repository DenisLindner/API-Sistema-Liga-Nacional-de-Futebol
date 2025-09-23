package com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.CampeaoTemporada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampeaoTemporadaRepository extends JpaRepository<CampeaoTemporada, Long> {

    CampeaoTemporada findByTemporada_Id(Long id);
}
