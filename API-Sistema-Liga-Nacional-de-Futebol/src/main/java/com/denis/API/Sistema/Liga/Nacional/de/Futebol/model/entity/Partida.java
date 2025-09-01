package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "partidas")
@Getter
@Setter
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "concluido", nullable = false)
    private boolean concluido = false;

    @Column(name = "gols_mandante", nullable = false)
    private int golsMandante = 0;

    @Column(name = "gols_visitante", nullable = false)
    private int golsVisitante = 0;

    @Column(name = "rodada", nullable = false)
    private int rodada;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "id_time_mandante", nullable = false)
    private Time timeMandante;

    @ManyToOne
    @JoinColumn(name = "id_time_visitante", nullable = false)
    private Time timeVisitante;

    @ManyToOne
    @JoinColumn(name = "id_temporada", nullable = false)
    private Temporada temporada;

    @OneToMany(mappedBy = "partida", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Credenciamento> credenciamentos = new ArrayList<>();

    public Partida() {}

    public Partida(int rodada, LocalDateTime dataHora, Time timeMandante, Time timeVisitante, Temporada temporada) {
        this.rodada = rodada;
        this.dataHora = dataHora;
        this.timeMandante = timeMandante;
        this.timeVisitante = timeVisitante;
        this.temporada = temporada;
    }

    public Partida(Long id, int rodada, LocalDateTime dataHora, Time timeMandante, Time timeVisitante, Temporada temporada) {
        this.id = id;
        this.rodada = rodada;
        this.dataHora = dataHora;
        this.timeMandante = timeMandante;
        this.timeVisitante = timeVisitante;
        this.temporada = temporada;
    }
}
