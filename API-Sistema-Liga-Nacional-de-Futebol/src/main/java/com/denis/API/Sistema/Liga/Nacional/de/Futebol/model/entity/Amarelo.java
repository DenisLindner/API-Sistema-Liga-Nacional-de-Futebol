package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "amarelos")
@Getter
@Setter
public class Amarelo extends Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Amarelo() {}

    public Amarelo(int minuto, Atleta atleta, Time time, Partida partida) {
        super(minuto, atleta, time, partida);
    }
}
