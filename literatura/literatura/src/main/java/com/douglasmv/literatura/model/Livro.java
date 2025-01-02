package com.douglasmv.literatura.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String nomeAutor;
    private Integer downloads;
    private String linguagens;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Autor autor;

    public Livro() {
    }

    public Livro(String titulo, Integer downloads, List<String> linguagens, Autor autor) {
        this.titulo = titulo;
        this.nomeAutor = autor.getNomeAutor();
        this.downloads = downloads;
        this.linguagens = linguagens.getFirst();
        this.autor = autor;
    }

    public Livro(Livro livroResposta){
        this.titulo = livroResposta.getTitulo();
        this.nomeAutor = livroResposta.getAutor().getNomeAutor();
        this.downloads = livroResposta.getDownloads();
        this.linguagens = livroResposta.getLinguagens();
    }

    @Override
    public String toString() {
        return """
                ++++++++++++++++++++++++++
                Titulo: %s
                Autor: %s
                Lingua: %s
                Downloads: %d
                ++++++++++++++++++++++++++
                """.formatted(this.getTitulo(),this.getAutor(),this.getLinguagens(),this.getDownloads());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public String getLinguagens() {
        return linguagens;
    }

    public void setLinguagens(String linguagens) {
        this.linguagens = linguagens;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
