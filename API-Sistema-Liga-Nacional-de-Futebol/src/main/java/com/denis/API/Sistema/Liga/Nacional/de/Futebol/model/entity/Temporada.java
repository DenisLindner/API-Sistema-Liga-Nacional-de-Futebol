package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "temporadas")
@Getter
@Setter
public class Temporada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "concluido", nullable = false)
    private boolean concluido = false;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @Column(name = "divisao", nullable = false)
    private int divisao;

    @OneToMany(mappedBy = "temporada", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EstatisticaTemporadaTime> estatisticaTemporadaTimes = new ArrayList<>();

    @OneToMany(mappedBy = "temporada", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Partida> partidas = new ArrayList<>();

    @OneToMany(mappedBy = "temporada", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EstatisticaTemporadaAtleta> estatisticaTemporadaAtletas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_campeonato", nullable = false)
    private Campeonato campeonato;

    @OneToOne(mappedBy = "temporada")
    private CampeaoTemporada campeaoTemporada;

    public Temporada() {}

    public Temporada(String nome,LocalDate dataInicio,LocalDate dataFim,Campeonato campeonato) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.campeonato = campeonato;
    }

    public Temporada(Long id, boolean concluido, String nome, LocalDate dataInicio, LocalDate dataFim, Campeonato campeonato) {
        this.id = id;
        this.concluido = concluido;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.campeonato = campeonato;
    }
}
