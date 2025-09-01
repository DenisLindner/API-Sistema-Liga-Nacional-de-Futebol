package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "noticias")
@Getter
@Setter
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "autor", length = 100, nullable = false)
    private String autor;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "conteudo", nullable = false)
    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa empresa;

    public Noticia() {}

    public Noticia(String autor, String titulo, String descricao, String conteudo, Empresa empresa) {
        this.autor = autor;
        this.titulo = titulo;
        this.descricao = descricao;
        this.conteudo = conteudo;
        this.empresa = empresa;
    }

    public Noticia(Long id ,String autor, String titulo, String descricao, String conteudo, Empresa empresa) {
        this.id = id;
        this.autor = autor;
        this.titulo = titulo;
        this.descricao = descricao;
        this.conteudo = conteudo;
        this.empresa = empresa;
    }
}
