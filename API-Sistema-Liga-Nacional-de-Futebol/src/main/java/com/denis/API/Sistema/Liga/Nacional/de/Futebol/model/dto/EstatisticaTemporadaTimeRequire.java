package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Temporada;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Time;

public record EstatisticaTemporadaTimeRequire(Time time, Temporada temporada) {
}
