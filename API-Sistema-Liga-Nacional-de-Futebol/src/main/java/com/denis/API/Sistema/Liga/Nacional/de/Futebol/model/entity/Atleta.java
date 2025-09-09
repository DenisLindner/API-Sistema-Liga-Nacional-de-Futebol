package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

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
    private LocalDate dataNascimento;

    @Column(name = "data_contratacao", nullable = false)
    private LocalDate dataContratacao;

    @Column(name = "data_final_contratacao", nullable = false)
    private LocalDate dataFinalContratacao;

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

    @OneToMany(mappedBy = "atleta", cascade = CascadeType.ALL,  orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Gol> gols = new ArrayList<>();

    @OneToMany(mappedBy = "atleta", cascade = CascadeType.ALL,  orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Amarelo> amarelos = new ArrayList<>();

    @OneToMany(mappedBy = "atleta", cascade = CascadeType.ALL,  orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Vermelho> vermelhos = new ArrayList<>();

    public Atleta() {}

    public Atleta(String nome, String cpf, LocalDate dataNascimento, LocalDate dataContratacao, LocalDate dataFinalContratacao, Time time) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.dataContratacao = dataContratacao;
        this.dataFinalContratacao = dataFinalContratacao;
        this.time = time;
    }

    public Atleta(Long id, String nome, String cpf, LocalDate dataNascimento, LocalDate dataContratacao, LocalDate dataFinalContratacao, int quantidadePartidas, int cartoesAmarelos, int cartoesVermelhos, int quantidadeGols, Time time) {
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
