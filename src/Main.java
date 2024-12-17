
// Importações necessárias
import java.time.LocalDate; // Para manipulação de datas
import java.util.ArrayList; // Para criação de listas dinâmicas
import java.util.Scanner; // Para entrada de dados pelo teclado

// Classe principal do programa
public class Main {
    public static void main(String[] args) {
        // Criação do scanner para capturar entradas do usuário
        Scanner scanner = new Scanner(System.in);

        // Listas para armazenar dados de livros, usuários e empréstimos
        ArrayList<Livro> livros = new ArrayList<>(); // Lista de livros cadastrados
        ArrayList<Usuario> usuarios = new ArrayList<>(); // Lista de usuários cadastrados
        ArrayList<Emprestimo> emprestimos = new ArrayList<>(); // Lista de empréstimos realizados

        int opcao; // Variável para armazenar a escolha do usuário no menu

        // Estrutura de repetição para exibir o menu até o usuário sair
        do {
            // Exibição do menu
            System.out.println("\n Biblioteca ");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Cadastrar Usuario");
            System.out.println("3. Realizar Emprestimo");
            System.out.println("4. Listar Livros");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            // Leitura da opção escolhida pelo usuário
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consome a quebra de linha pendente

            // Estrutura condicional para tratar cada opção do menu
            switch (opcao) {
                case 1:
                    // Cadastrar Livro
                    System.out.print("Titulo: ");
                    String titulo = scanner.nextLine(); // Recebe o título do livro
                    System.out.print("Ano: ");
                    int ano = scanner.nextInt(); // Recebe o ano do livro
                    scanner.nextLine(); // Consome a quebra de linha
                    System.out.print("Editora: ");
                    String editora = scanner.nextLine(); // Recebe a editora do livro
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine(); // Recebe o autor do livro

                    // Cria um novo objeto Livro e o adiciona à lista de livros
                    livros.add(new Livro(titulo, ano, editora, autor));
                    System.out.println("Livro cadastrado com sucesso!");
                    break;

                case 2:
                    // Cadastrar Usuário
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine(); // Recebe o nome do usuário
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine(); // Recebe o CPF do usuário
                    System.out.print("E-mail: ");
                    String email = scanner.nextLine(); // Recebe o e-mail do usuário

                    // Cria um novo objeto Usuario e o adiciona à lista de usuários
                    usuarios.add(new Usuario(nome, cpf, email));
                    System.out.println("Usuario cadastrado com sucesso!");
                    break;

                case 3:
                    // Realizar Empréstimo
                    System.out.println("Usuarios cadastrados:");
                    // Lista os usuários cadastrados
                    for (int i = 0; i < usuarios.size(); i++) {
                        System.out.println((i + 1) + ". " + usuarios.get(i).getNome());
                    }
                    System.out.print("Escolha o usuário (número): ");
                    int usuarioIndex = scanner.nextInt() - 1; // Escolha do usuário pelo índice

                    System.out.println("Livros disponiveis:");
                    // Lista os livros disponíveis para empréstimo
                    for (int i = 0; i < livros.size(); i++) {
                        if (livros.get(i).isDisponivel()) {
                            System.out.println((i + 1) + ". " + livros.get(i).getTitulo());
                        }
                    }
                    System.out.print("Escolha o livro (numero): ");
                    int livroIndex = scanner.nextInt() - 1; // Escolha do livro pelo índice
                    scanner.nextLine(); // Consome a quebra de linha

                    // Recupera o livro escolhido
                    Livro livroEscolhido = livros.get(livroIndex);
                    if (livroEscolhido.isDisponivel()) { // Verifica se o livro está disponível
                        livroEscolhido.setDisponivel(false); // Marca o livro como indisponível

                        // Calcula as datas de empréstimo e devolução
                        LocalDate dataEmprestimo = LocalDate.now(); // Data atual
                        LocalDate dataDevolucao = dataEmprestimo.plusDays(14); // Prazo de 14 dias

                        // Cria um novo objeto Emprestimo e o adiciona à lista de empréstimos
                        emprestimos.add(new Emprestimo(usuarios.get(usuarioIndex), livroEscolhido, dataEmprestimo,
                                dataDevolucao));
                        System.out.println("Empréstimo realizado! Data de devolução: " + dataDevolucao);
                    } else {
                        System.out.println("Livro indisponível.");
                    }
                    break;

                case 4:
                    // Listar Livros
                    System.out.println("\n--- Lista de Livros ---");
                    // Exibe os detalhes de cada livro na lista
                    for (Livro livro : livros) {
                        System.out.println("Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor()
                                + ", Disponível: " + livro.isDisponivel());
                    }
                    break;

                case 5:
                    // Sair do sistema
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    // Caso o usuário escolha uma opção inválida
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5); // Continua exibindo o menu até que o usuário escolha a opção de sair

        scanner.close(); // Fecha o scanner para liberar recursos
    }
}
