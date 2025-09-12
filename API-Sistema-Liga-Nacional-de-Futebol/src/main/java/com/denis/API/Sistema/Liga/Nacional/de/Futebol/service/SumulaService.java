package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;


import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Amarelo;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Gol;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Sumula;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Vermelho;

import java.util.List;

public class SumulaService {
    public void cadastrarSumula(Sumula sumula){
        if (!verificarAmarelos(sumula.getAmarelos())){

        }
    }

    public boolean verificarAmarelos(List <Amarelo> amarelos){

        return true;
    }

    public boolean verificarVermelhos(List <Vermelho> vermelhos){

        return true;
    }

    public boolean verificarGols(List <Gol> gols){

        return true;
    }
}
