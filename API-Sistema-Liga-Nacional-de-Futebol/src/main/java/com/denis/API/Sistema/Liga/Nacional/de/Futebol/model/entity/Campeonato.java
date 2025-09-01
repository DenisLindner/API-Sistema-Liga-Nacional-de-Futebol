package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "campeonatos")
@Getter
@Setter
public class Campeonato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome",length = 100, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "campeonato", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Time> times = new ArrayList<>();

    @OneToMany(mappedBy = "campeonato", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Temporada> temporadas = new ArrayList<>();

    @OneToOne(mappedBy = "campeonato", cascade = CascadeType.ALL, orphanRemoval = true)
    private EstatisticasCampeonato estatisticasCampeonato;

    public Campeonato() {}

    public Campeonato(String nome) {
        this.nome = nome;
    }

    public Campeonato(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
