public interface Notificacao {
    void enviarNotificacao(Usuario usuario, String mensagem);
}
class NotificacaoEmail implements Notificacao {
    @Override
    public void enviarNotificacao(Usuario usuario, String mensagem) {
        System.out.println("Notificação por e-mail enviada para " + usuario.getNome() + ": " + mensagem);
    }
}
class NotificacaoSMS implements Notificacao {
    @Override
    public void enviarNotificacao(Usuario usuario, String mensagem) {
        System.out.println("Notificação por SMS enviada para " + usuario.getNome() + ": " + mensagem);
    }
}
class NotificacaoWhatsApp implements Notificacao {
    @Override
    public void enviarNotificacao(Usuario usuario, String mensagem) {
        System.out.println("Notificação por WhatsApp enviada para " + usuario.getNome() + ": " + mensagem);
    }
}
