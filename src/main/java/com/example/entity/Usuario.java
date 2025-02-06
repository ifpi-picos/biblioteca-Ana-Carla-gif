package com.example.entity;
// Classe Usuario
public class Usuario {
    private String nome;
    private String cpf;
    private String email;
    private String preferenciaNotificacao; // nova linha

    // Construtor da classe Usuario
    public Usuario(String nome, String cpf, String email, String preferenciaNotificacao) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.preferenciaNotificacao = preferenciaNotificacao; 
    }

    // Métodos getter
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getPreferenciaNotificacao() {
        return preferenciaNotificacao;
    }

    public void setPreferenciaNotificacao(String preferenciaNotificacao) {
        this.preferenciaNotificacao = preferenciaNotificacao;
    }
}
