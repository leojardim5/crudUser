package com.leonardojardim.crudLojaLivro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Define o tipo de geração do ID
    private Long id;

    @Column(name = "titulo", nullable = false)  // Define a coluna "titulo" na tabela
    private String titulo;

    @Column(name = "autor")  // Define a coluna "autor" na tabela
    private String autor;

    @Column(name = "preco")  // Define a coluna "preco" na tabela
    private Double preco;

    // Construtor padrão
    public Livro() {
    }

    // Construtor com todos os atributos
    public Livro(Long id, String titulo, String autor, Double preco) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.preco = preco;
    }

    // Getters e Setters
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Livro{"
                + "id=" + id
                + ", titulo='" + titulo + '\''
                + ", autor='" + autor + '\''
                + ", preco=" + preco
                + '}';
    }
}
