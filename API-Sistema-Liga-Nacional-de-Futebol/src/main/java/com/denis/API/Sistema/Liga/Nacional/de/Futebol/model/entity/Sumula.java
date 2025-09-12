package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sumula {

    private List<Amarelo> amarelos;

    private List<Vermelho> vermelhos;

    private List<Gol> gols;

    private Long idPartida;

    public Sumula(){}

    public Sumula(Long idPartida, List<Amarelo> amarelos, List<Vermelho> vermelhos, List<Gol> gols){
        this.idPartida = idPartida;
        this.amarelos = amarelos;
        this.vermelhos = vermelhos;
        this.gols = gols;
    }
}
