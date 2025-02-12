package com.example.entity;

public class NotificacaoWhatsApp implements Notificacao {
    @Override
    public void enviarNotificacao(Usuario usuario, String mensagem) {
        System.out.println("Notificação por WhatsApp enviada para " + usuario.getNome() + ": " + mensagem);
    }
}