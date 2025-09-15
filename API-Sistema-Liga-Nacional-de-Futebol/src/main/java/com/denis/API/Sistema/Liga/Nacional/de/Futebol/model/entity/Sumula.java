package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity;

import java.util.List;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.AmareloRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.GolRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.VermelhoRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sumula {

    private int golsMandante;

    private int golsVisitante;

    private Partida partida;

    private List<AmareloRequest> amarelos;

    private List<VermelhoRequest> vermelhos;

    private List<GolRequest> gols;


    public Sumula(){}

    public Sumula(int golsMandante, int golsVisitante,Partida partida, List<AmareloRequest> amarelos, List<VermelhoRequest> vermelhos, List<GolRequest> gols){
        this.golsMandante = golsMandante;
        this.golsVisitante = golsVisitante;
        this.partida = partida;
        this.amarelos = amarelos;
        this.vermelhos = vermelhos;
        this.gols = gols;
    }
}
