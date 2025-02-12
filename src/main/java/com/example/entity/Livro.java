
package com.example.entity;

public class Livro {
    private int id;
    private String titulo;
    private int ano;
    private String editora;
    private String autor;
    private boolean disponivel;

    public Livro(int id, String titulo, int ano, String editora, String autor, boolean disponivel) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        this.editora = editora;
        this.autor = autor;
        this.disponivel = disponivel;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAno() {
        return ano;
    }

    public String getEditora() {
        return editora;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
