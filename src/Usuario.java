// Classe Usuario
public class Usuario {
    private String nome;
    private String cpf;
    private String email;

    // Construtor da classe Usuario
    public Usuario(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
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
}

