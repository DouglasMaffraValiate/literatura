package com.douglasmv.literatura.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeAutor;
    private Integer dataNascimento;
    private Integer dataObto;
    private String tituloLivro;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Livro> livros;

    public Autor() {
    }

    @Override
    public String toString() {
        return """
                --------------------------------------
                Nome: %s
                Ano nascimento: %d
                Ano obto: %d
                --------------------------------------
                """.formatted(this.getNomeAutor(),this.getDataNascimento(),this.getDataObto());
    }

    public Autor(String nomeAutor, Integer dataNascimento, Integer dataObto, String tituloLivro) {
        this.nomeAutor = nomeAutor;
        this.dataNascimento = dataNascimento;
        this.dataObto = dataObto;
        this.tituloLivro = tituloLivro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public Integer getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Integer dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getDataObto() {
        return dataObto;
    }

    public void setDataObto(Integer dataObto) {
        this.dataObto = dataObto;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
