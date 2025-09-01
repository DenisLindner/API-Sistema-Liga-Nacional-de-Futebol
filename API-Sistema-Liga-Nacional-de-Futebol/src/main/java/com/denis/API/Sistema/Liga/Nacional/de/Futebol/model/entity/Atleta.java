package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "atletas")
@Getter
@Setter
public class Atleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "cpf",  length = 11,  nullable = false, unique = true)
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;

    @Column(name = "data_contratacao", nullable = false)
    private Date dataContratacao;

    @Column(name = "data_final_contratacao", nullable = false)
    private Date dataFinalContratacao;

    @Column(name = "quantidade_partidas", nullable = false)
    private int quantidadePartidas = 0;

    @Column(name = "cartoes_amarelos", nullable = false)
    private int cartoesAmarelos = 0;

    @Column(name = "cartoes_vermelhos", nullable = false)
    private int cartoesVermelhos = 0;

    @Column(name = "quantidade_gols", nullable = false)
    private int quantidadeGols = 0;

    @ManyToOne
    @JoinColumn(name = "id_time", nullable = false)
    private Time time;

    public Atleta() {}

    public Atleta(String nome, String cpf, Date dataNascimento, Date dataContratacao, Date dataFinalContratacao, Time time) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.dataContratacao = dataContratacao;
        this.dataFinalContratacao = dataFinalContratacao;
        this.time = time;
    }

    public Atleta(Long id, String nome, String cpf, Date dataNascimento, Date dataContratacao, Date dataFinalContratacao, int quantidadePartidas, int cartoesAmarelos, int cartoesVermelhos, int quantidadeGols, Time time) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.dataContratacao = dataContratacao;
        this.dataFinalContratacao = dataFinalContratacao;
        this.quantidadePartidas = quantidadePartidas;
        this.cartoesAmarelos = cartoesAmarelos;
        this.cartoesVermelhos = cartoesVermelhos;
        this.quantidadeGols = quantidadeGols;
        this.time = time;
    }
}
