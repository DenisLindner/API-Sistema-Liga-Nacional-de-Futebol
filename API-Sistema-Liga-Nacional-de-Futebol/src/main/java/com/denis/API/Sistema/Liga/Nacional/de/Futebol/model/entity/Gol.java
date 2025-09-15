package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "gols")
@Getter
@Setter
public class Gol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "minuto", nullable = false)
    private int minuto;

    @ManyToOne
    @JoinColumn(name = "id_atleta")
    private Atleta atleta;

    @ManyToOne
    @JoinColumn(name = "id_time", nullable = false)
    private Time time;

    @ManyToOne
    @JoinColumn(name = "id_partida", nullable = false)
    private Partida partida;

    public Gol() {}

    public Gol(int minuto, Atleta atleta, Partida partida) {}
}
