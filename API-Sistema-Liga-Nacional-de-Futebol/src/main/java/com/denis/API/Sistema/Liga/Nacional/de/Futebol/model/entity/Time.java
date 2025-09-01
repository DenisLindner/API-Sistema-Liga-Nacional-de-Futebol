package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "times")
@Getter
@Setter
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "estadio", length = 100, nullable = false)
    private String estadio;

    @Column(name = "nome_treinador", length = 100, nullable = false)
    private String nomeTreinador;

    @OneToMany(mappedBy = "time", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Atleta>atletas = new ArrayList<>();

    @OneToMany(mappedBy = "time", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EstatisticaTemporadaTime> estatisticasTemporadasTimes = new ArrayList<>();

    @OneToOne(mappedBy = "time", cascade = CascadeType.ALL, orphanRemoval = true)
    private EstatisticaTotalTime estatisticaTotalTime;

    @ManyToOne
    @JoinColumn(name = "id_campeonato", nullable = false)
    private Campeonato campeonato;

    public Time() {}

    public Time(String nome, String estadio, String nomeTreinador, Campeonato campeonato) {
        this.nome = nome;
        this.estadio = estadio;
        this.nomeTreinador = nomeTreinador;
        this.campeonato = campeonato;
    }

    public Time(Long id,String nome, String estadio, String nomeTreinador, Campeonato campeonato) {
        this.id = id;
        this.nome = nome;
        this.estadio = estadio;
        this.nomeTreinador = nomeTreinador;
        this.campeonato = campeonato;
    }
}
