// Classe Livro
public class Livro {
    private String titulo;
    private int ano;
    private String editora;
    private boolean disponivel;
    private String autor;

    // Construtor da classe Livro
    public Livro(String titulo, int ano, String editora, String autor) {
        this.titulo = titulo;
        this.ano = ano;
        this.editora = editora;
        this.autor = autor;
        this.disponivel = true; // Livro começa como disponível 
    }

    // Métodos getter e setter
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