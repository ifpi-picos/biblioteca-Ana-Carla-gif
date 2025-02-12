package com.example.entity;

public class NotificacaoEmail implements Notificacao {
    @Override
    public void enviarNotificacao(Usuario usuario, String mensagem) {
        System.out.println("Notificação por e-mail enviada para " + usuario.getNome() + " (ID: " + usuario.getId() + "): " + mensagem);
    }
}