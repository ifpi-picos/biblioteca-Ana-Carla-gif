// Classe Main
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Livro> livros = new ArrayList<>();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Emprestimo> emprestimos = new ArrayList<>();

        int opcao;
        do {
            // Menu
            System.out.println("\n Biblioteca ");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Cadastrar Usuario");
            System.out.println("3. Realizar Emprestimo");
            System.out.println("4. Listar Livros");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    // Cadastrar Livro
                    System.out.print("Titulo: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ano: ");
                    int ano = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha
                    System.out.print("Editora: ");
                    String editora = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();

                    livros.add(new Livro(titulo, ano, editora, autor));
                    System.out.println("Livro cadastrado com sucesso!");
                    break;

                case 2:
                    // Cadastrar Usuário
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("E-mail: ");
                    String email = scanner.nextLine();

                    usuarios.add(new Usuario(nome, cpf, email));
                    System.out.println("Usuario cadastrado com sucesso!");
                    break;

                case 3:
                    // Realizar Empréstimo
                    System.out.println("Usuarios cadastrados:");
                    for (int i = 0; i < usuarios.size(); i++) {
                        System.out.println((i + 1) + ". " + usuarios.get(i).getNome());
                    }
                    System.out.print("Escolha o usuário (número): ");
                    int usuarioIndex = scanner.nextInt() - 1;

                    System.out.println("Livros disponiveis:");
                    for (int i = 0; i < livros.size(); i++) {
                        if (livros.get(i).isDisponivel()) {
                            System.out.println((i + 1) + ". " + livros.get(i).getTitulo());
                        }
                    }
                    System.out.print("Escolha o livro (numero): ");
                    int livroIndex = scanner.nextInt() - 1;
                    scanner.nextLine();

                    Livro livroEscolhido = livros.get(livroIndex);
                    if (livroEscolhido.isDisponivel()) {
                        livroEscolhido.setDisponivel(false);
                        LocalDate dataEmprestimo = LocalDate.now();
                        LocalDate dataDevolucao = dataEmprestimo.plusDays(14); // Prazo de 14 dias

                        emprestimos.add(new Emprestimo(usuarios.get(usuarioIndex), livroEscolhido, dataEmprestimo, dataDevolucao));
                        System.out.println("Empréstimo realizado! Data de devolução: " + dataDevolucao);
                    } else {
                        System.out.println("Livro indisponível.");
                    }
                    break;

                case 4:
                    // Listar Livros
                    System.out.println("\n--- Lista de Livros ---");
                    for (Livro livro : livros) {
                        System.out.println("Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor() + ", Disponível: " + livro.isDisponivel());
                    }
                    break;

                case 5:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);

        scanner.close();
    }
}