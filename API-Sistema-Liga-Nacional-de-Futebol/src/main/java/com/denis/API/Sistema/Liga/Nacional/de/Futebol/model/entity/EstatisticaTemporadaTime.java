package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

@Entity
@Table(name = "estatisticas_temporada_times")
@Getter
@Setter
public class EstatisticaTemporadaTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pontos", nullable = false)
    private int pontos = 0;

    @Column(name = "quantidade_partidas", nullable = false)
    private int quantidadePartidas = 0;

    @Column(name = "cartoes_amarelos",  nullable = false)
    private int cartoesAmarelos = 0;

    @Column(name = "cartoes_vermelhos",   nullable = false)
    private int cartoesVermelhos = 0;

    @Column(name = "gols_pro", nullable = false)
    private int golsPro = 0;

    @Column(name = "gols_contra", nullable = false)
    private int golsContra = 0;

    @ManyToOne
    @JoinColumn(name = "id_time", nullable = false)
    private Time time;

    @ManyToOne
    @JoinColumn(name = "id_temporada", nullable = false)
    private Temporada temporada;

    public EstatisticaTemporadaTime() {}

    public EstatisticaTemporadaTime(Time time, Temporada temporada) {
        this.time = time;
        this.temporada = temporada;
    }

    public EstatisticaTemporadaTime(Long id, int pontos, int quantidadePartidas, int cartoesAmarelos, int cartoesVermelhos, int golsPro, int golsContra, Time time, Temporada temporada) {
        this.id = id;
        this.pontos = pontos;
        this.quantidadePartidas = quantidadePartidas;
        this.cartoesAmarelos = cartoesAmarelos;
        this.cartoesVermelhos = cartoesVermelhos;
        this.golsPro = golsPro;
        this.golsContra = golsContra;
        this.time = time;
        this.temporada = temporada;
    }
}
