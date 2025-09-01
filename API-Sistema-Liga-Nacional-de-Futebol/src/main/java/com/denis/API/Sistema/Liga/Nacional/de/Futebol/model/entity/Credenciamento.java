package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "credenciamentos")
@Getter
@Setter
public class Credenciamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf", length = 11, nullable = false)
    private String cpf;

    @Column(name = "crt_jornalista", length = 11, nullable = false)
    private String crtJornalista;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "id_partida", nullable = false)
    private Partida partida;

    public Credenciamento() {}

    public Credenciamento(String cpf, String crtJornalista, String nome, Empresa empresa, Partida partida) {
        this.cpf = cpf;
        this.crtJornalista = crtJornalista;
        this.nome = nome;
        this.empresa = empresa;
        this.partida = partida;
    }

    public Credenciamento(Long id, String cpf, String crtJornalista, String nome, Empresa empresa, Partida partida) {
        this.id = id;
        this.cpf = cpf;
        this.crtJornalista = crtJornalista;
        this.nome = nome;
        this.empresa = empresa;
        this.partida = partida;
    }
}
