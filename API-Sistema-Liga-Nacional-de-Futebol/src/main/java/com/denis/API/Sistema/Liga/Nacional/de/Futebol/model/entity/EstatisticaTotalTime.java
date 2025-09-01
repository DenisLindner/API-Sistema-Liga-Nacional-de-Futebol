package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "estatisticas_total_times")
@Getter
@Setter
public class EstatisticaTotalTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pontos", nullable = false)
    private int pontos = 0;

    @Column(name = "quantidade_partidas", nullable = false)
    private int quantidadePartidas = 0;

    @Column(name = "cartoes_amarelos", nullable = false)
    private int cartoesAmarelos = 0;

    @Column(name = "cartoes_vermelhos",  nullable = false)
    private int cartoesVermelhos = 0;

    @Column(name = "gols_pro", nullable = false)
    private int golsPro = 0;

    @Column(name = "gols_contra", nullable = false)
    private int golsContra = 0;

    @OneToOne
    @JoinColumn(name = "id_time", nullable = false)
    private Time time;

    public EstatisticaTotalTime() {}

    public EstatisticaTotalTime(Time time) {
        this.time = time;
    }

    public  EstatisticaTotalTime(Long id, int pontos, int quantidadePartidas, int cartoesAmarelos, int cartoesVermelhos, int golsPro, int golsContra, Time time) {
        this.id = id;
        this.pontos = pontos;
        this.quantidadePartidas = quantidadePartidas;
        this.cartoesAmarelos = cartoesAmarelos;
        this.cartoesVermelhos = cartoesVermelhos;
        this.golsPro = golsPro;
        this.golsContra = golsContra;
        this.time = time;
    }
}
