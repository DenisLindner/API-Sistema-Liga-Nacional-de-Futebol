package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "estatistica_temporada_atletas")
@Getter
@Setter
public class EstatisticaTemporadaAtleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cartoes_amarelos", nullable = false)
    private int cartoesAmarelos = 0;

    @Column(name = "cartoes_vermelhos", nullable = false)
    private int cartoesVermelhos = 0;

    @Column(name = "quantidade_gols", nullable = false)
    private int quantidadeGols = 0;

    @ManyToOne
    @JoinColumn(name = "id_atleta")
    private Atleta atleta;

    @ManyToOne
    @JoinColumn(name = "id_temporada")
    private Temporada temporada;

    public EstatisticaTemporadaAtleta() {}

    public EstatisticaTemporadaAtleta(Atleta atleta, Temporada temporada) {
        this.atleta = atleta;
        this.temporada = temporada;
    }
}
