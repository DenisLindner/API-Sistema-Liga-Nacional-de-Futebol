package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "campeoes")
@Getter
@Setter
public class CampeaoTemporada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_time", nullable = false)
    private Time time;

    @OneToOne
    @JoinColumn(name = "id_temporada", nullable = false)
    private Temporada temporada;

    public CampeaoTemporada(){}

    public CampeaoTemporada(Time time, Temporada temporada) {
        this.time = time;
        this.temporada = temporada;
    }
}
