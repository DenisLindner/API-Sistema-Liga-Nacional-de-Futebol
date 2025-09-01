package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "estatisticas_campeonatos")
@Getter
@Setter
public class EstatisticasCampeonato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pontos", nullable = false)
    private int pontos = 0;

    @Column(name = "quantidade_partidas", nullable = false)
    private int quantidadePartidas = 0;

    @Column(name = "gols_feitos", nullable = false)
    private int golsFeitos = 0;

    @Column(name = "cartoes_amarelos", nullable = false)
    private int cartoesAmarelos = 0;

    @Column(name = "cartoes_vermelhos", nullable = false)
    private int cartoesVermelhos = 0;

    @OneToOne
    @JoinColumn(name = "id_campeonato", nullable = false)
    private Campeonato campeonato;

    public EstatisticasCampeonato() {}

    public EstatisticasCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public EstatisticasCampeonato(Long id, int pontos, int quantidadePartidas, int golsFeitos, int cartoesAmarelos, int cartoesVermelhos, Campeonato campeonato) {
        this.id = id;
        this.pontos = pontos;
        this.quantidadePartidas = quantidadePartidas;
        this.golsFeitos = golsFeitos;
        this.cartoesAmarelos = cartoesAmarelos;
        this.cartoesVermelhos = cartoesVermelhos;
        this.campeonato = campeonato;
    }
}
