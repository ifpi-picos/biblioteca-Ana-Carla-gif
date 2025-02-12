package com.example.entity;

public class NotificacaoSMS implements Notificacao {
    @Override
    public void enviarNotificacao(Usuario usuario, String mensagem) {
        System.out.println("Notificação por SMS enviada para " + usuario.getNome() + " (ID: " + usuario.getId() + "): " + mensagem);
    }
}