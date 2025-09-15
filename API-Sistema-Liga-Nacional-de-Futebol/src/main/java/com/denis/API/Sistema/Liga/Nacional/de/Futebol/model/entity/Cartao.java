package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@MappedSuperclass
@Getter
@Setter
public abstract class Cartao {

    @Column(name = "minuto", nullable = false)
    private int minuto;

    @ManyToOne
    @JoinColumn(name = "id_atleta", nullable = false)
    private Atleta atleta;

    @ManyToOne
    @JoinColumn(name = "id_time", nullable = false)
    private Time time;

    @ManyToOne
    @JoinColumn(name = "id_partida", nullable = false)
    private Partida partida;

    public Cartao() {}

    public Cartao(int minuto, Atleta atleta, Time time, Partida partida) {
        this.minuto = minuto;
        this.atleta = atleta;
        this.time = time;
        this.partida = partida;
    }
}
