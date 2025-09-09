package com.denis.API.Sistema.Liga.Nacional.de.Futebol.calculos;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.TimeResponseTabela;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.EstatisticaTemporadaTime;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Temporada;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;

@Component
public class CalculoTabelaTemporada {


    public CalculoTabelaTemporada() {}

    public List<TimeResponseTabela> tabelaTemporadas(Temporada temporada) {
        List<TimeResponseTabela> timeResponseTabelas = new ArrayList<>();
        List<EstatisticaTemporadaTime> estatisticaTemporadaTimes = temporada.getEstatisticaTemporadaTimes();

        estatisticaTemporadaTimes.sort((t1, t2) -> {
            int comp = Integer.compare(t2.getPontos(), t1.getPontos());
            if (comp != 0) return comp;

            comp = Integer.compare(t2.getGolsPro()- t2.getGolsContra(), t1.getGolsPro()-t1.getGolsContra());
            if (comp != 0) return comp;

            return t1.getTime().getNome().compareTo(t2.getTime().getNome());
        });

        for (EstatisticaTemporadaTime estatisticaTemporadaTime : estatisticaTemporadaTimes) {
            timeResponseTabelas.add(new TimeResponseTabela(estatisticaTemporadaTime.getTime().getNome(), estatisticaTemporadaTime.getPontos(), estatisticaTemporadaTime.getGolsPro()-estatisticaTemporadaTime.getGolsContra(), estatisticaTemporadaTime.getGolsPro(), estatisticaTemporadaTime.getGolsContra(), estatisticaTemporadaTime.getQuantidadePartidas(), estatisticaTemporadaTime.getCartoesAmarelos(), estatisticaTemporadaTime.getCartoesVermelhos(), estatisticaTemporadaTime.getTime().getId()));
        }

        return timeResponseTabelas;
    }
}
