package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "empresas")
@Getter
@Setter
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_empresarial", nullable = false, length = 100)
    private String nomeEmpresarial;

    @Column(name = "nome_fantasia", nullable = false, length = 100)
    private String nomeFantasia;

    @Column(name = "cnpj", nullable = false, length = 14, unique = true)
    private String cnpj;

    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Credenciamento> credenciamentos = new ArrayList<>();

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Noticia> noticias = new ArrayList<>();

    public Empresa() {}

    public Empresa(String nomeEmpresarial, String nomeFantasia, String cnpj, String telefone) {
        this.nomeEmpresarial = nomeEmpresarial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.telefone = telefone;
    }

    public Empresa(Long id, String nomeEmpresarial, String nomeFantasia, String cnpj, String telefone) {
        this.id = id;
        this.nomeEmpresarial = nomeEmpresarial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.telefone = telefone;
    }

}
