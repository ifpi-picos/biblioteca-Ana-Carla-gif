
// Importações necessárias
import java.time.LocalDate; // Para manipulação de datas
import java.util.ArrayList; // Para criação de listas 
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
            // Menu
            System.out.println("\n Biblioteca ");
            System.out.println("1. Cadastrar Livro");// OK
            System.out.println("2. Cadastrar Usuário"); // OK
            System.out.println("3. Realizar Empréstimo"); // OK
            System.out.println("4. Devolver livro"); // OK
            System.out.println("5. Listar Livros");// OK
            System.out.println("6. Listar livros emprestados e disponíveis"); // OK
            System.out.println("7. Listar histórico de empréstimo");// OK
            System.out.println("8. Sair"); // OK
            System.out.print("Escolha uma opção: ");
            // COLOCAR AS VERIFICAÇOES

            // Leitura da opção escolhida pelo usuário
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consome a quebra de linha pendente

            // Estrutura condicional para tratar cada opção do menu
            switch (opcao) {
                case 1:
                    // Cadastrar Livro

                    // DECLARANDO OS TIPOS
                    String Titulo;
                    int Ano;
                    String Editora;
                    String Autor;
                    String preferenciaNotificacao;
            

                    while (true) {
                        System.out.print("TÍTULO: ");
                        Titulo = scanner.nextLine().trim();
                        if (Titulo.isEmpty()) {
                            System.out.println("O título é obrigatório!");
                            continue;
                        }
                        break;
                    }
                    while (true) {
                        System.out.print("ANO DE PUBLICAÇÃO: ");
                        String anoStr = scanner.nextLine().trim(); // Lê o valor como String
                        if (anoStr.isEmpty()) { // Verifica se o campo está vazio
                            System.out.println("O ano de publicação é obrigatório!");
                            continue; // Volta ao início do loop se estiver vazio
                        }
                        Ano = Integer.parseInt(anoStr); // Converte o valor para inteiro
                        break; // Sai do loop se o campo estiver preenchido
                    }
                    while (true) {
                        System.out.print("EDITORA: ");
                        Editora = scanner.nextLine().trim();
                        if (Editora.isEmpty()) {
                            System.out.println("A editora é obrigatória!");
                            continue;
                        }
                        break;
                    }
                    while (true) {
                        System.out.print("AUTOR: ");
                        Autor = scanner.nextLine().trim();
                        if (Autor.isEmpty()) {
                            System.out.println("O autor é obrigatório!");
                            continue;
                        }
                        break;
                    }

                    // Cria um novo objeto Livro e o adiciona à lista de livros
                    livros.add(new Livro(Titulo, Ano, Editora, Autor));
                    System.out.println("Livro cadastrado com sucesso!");

                    break;
                case 2:
                    // Cadastrar Usuário com validações simples
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine().trim(); // Recebe o nome do usuário e remove espaços desnecessários
                    if (nome.isEmpty()) {
                        System.out.println("O nome não pode ser vazio!");
                        break;
                    }

                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine().trim(); // Recebe o CPF do usuário
                    if (cpf.isEmpty() || cpf.length() != 11 || !cpf.matches("\\d+")) {
                        System.out.println("O CPF deve conter 11 números!");
                        break;
                    }
                    System.out.print("E-mail: ");
                    String email = scanner.nextLine(); // Recebe o e-mail do usuário

                    // NOVAS LINHAS (WHILE P PREFERENCIA)
                    while (true) {
                        System.out.println("\nEscolha como deseja receber as suas notificações");
                        System.out.println("1 - SMS");
                        System.out.println("2 - WhatsApp");
                        System.out.println("3 - E-mail");
                        System.out.print("Opção: ");
                        preferenciaNotificacao = scanner.nextLine().trim();
                        if (email.isEmpty()) {
                            System.out.println("A preferência de notificações é obrigatório!");
                            continue;
                        }
                        break;
                    }
                    // Cria um novo objeto Usuario e o adiciona à lista de usuários
                    usuarios.add(new Usuario(nome, cpf, email, preferenciaNotificacao)); // novo:preferencia
                    break;

                case 3:
                    System.out.println("Usuários cadastrados:");
                    for (int i = 0; i < usuarios.size(); i++) {
                        System.out.println((i + 1) + ". " + usuarios.get(i).getNome());
                    }
                    System.out.print("Escolha o usuário (número): ");
                    int usuarioIndex = scanner.nextInt() - 1;
                    scanner.nextLine();

                    System.out.println("Livros disponíveis:");
                    for (int i = 0; i < livros.size(); i++) {
                        if (livros.get(i).isDisponivel()) {
                            System.out.println((i + 1) + ". " + livros.get(i).getTitulo());
                        }
                    }
                    System.out.print("Escolha o livro (número): ");
                    int livroIndex = scanner.nextInt() - 1;
                    scanner.nextLine();

                    Livro livroEscolhido = livros.get(livroIndex);
                    livroEscolhido.setDisponivel(false);

                    LocalDate dataEmprestimo = LocalDate.now();
                    LocalDate dataDevolucao = dataEmprestimo.plusDays(7);

                    Emprestimo novoEmprestimo = new Emprestimo(usuarios.get(usuarioIndex), livroEscolhido,
                            dataEmprestimo, dataDevolucao);
                    emprestimos.add(novoEmprestimo);

                    System.out.println("Empréstimo realizado! Data de devolução: " + dataDevolucao);

                    notificarUsuario(usuarios.get(usuarioIndex),
                            "Empréstimo realizado. Livro: " + livroEscolhido.getTitulo());
                    break;

                case 8:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 8);

        scanner.close();
    }

    private static void notificarUsuario(Usuario usuario, String mensagem) {
        Notificacao notificacao = criarNotificacao(usuario.getPreferenciaNotificacao());
        notificacao.enviarNotificacao(usuario, mensagem);
    }

    private static Notificacao criarNotificacao(String preferencia) {
        switch (preferencia) {
            case "1":
                return new NotificacaoSMS();
            case "2":
                return new NotificacaoWhatsApp();
            default:
                return new NotificacaoEmail();
        }
    }
}

// case 3:

// // Realizar Empréstimo ARRUMAR
// System.out.println("Usuários cadastrados:");
// // Lista os usuários cadastrados
// for (int i = 0; i < usuarios.size(); i++) {
// System.out.println((i + 1) + ". " + usuarios.get(i).getNome());
// }
// System.out.print("Escolha o usuario (numero): ");
// int usuarioIndex = scanner.nextInt() - 1; // Escolha do usuário pelo índice

// System.out.println("Livros disponiveis:");
// // Lista os livros disponíveis para empréstimo
// for (int i = 0; i < livros.size(); i++) {
// if (livros.get(i).isDisponivel()) {
// System.out.println((i + 1) + ". " + livros.get(i).getTitulo());
// }
// }
// System.out.print("Escolha o livro (numero): ");
// int livroIndex = scanner.nextInt() - 1; // Escolha do livro pelo índice
// scanner.nextLine(); // Consome a quebra de linha

// // Recupera o livro escolhido
// Livro livroEscolhido = livros.get(livroIndex);
// if (livroEscolhido.isDisponivel()) { // Verifica se o livro está disponível
// livroEscolhido.setDisponivel(false); // Marca o livro como indisponível

// // Calcula as datas de empréstimo e devolução
// LocalDate dataEmprestimo = LocalDate.now(); // Data atual
// LocalDate dataDevolucao = dataEmprestimo.plusDays(7); // Prazo

// // Cria um novo objeto Emprestimo e o adiciona à lista de empréstimos
// emprestimos.add(new Emprestimo(usuarios.get(usuarioIndex), livroEscolhido,
// dataEmprestimo, dataDevolucao));
// System.out.println("Emprestimo realizado! Data de devoluçao: " +
// dataDevolucao);
// } else {
// System.out.println("Livro indisponível.");

// }

// //nova linha
// // notificarUsuario(usuarioIndex, "Emprestimo realizado. Livro: " +
// livroEscolhido.getTitulo());

// //NOVAS LINHAS
// private static void notificarUsuario(Usuario usuario, String mensagem) {
// Notificacao notificacao =
// criarNotificacao(usuario.getPreferenciaNotificacao());
// notificacao.enviarNotificacao(usuario, mensagem);
// }

// private static Notificacao criarNotificacao(String preferencia) {
// switch (preferencia) {
// case "1":
// return new NotificacaoSMS();
// case "2":
// return new NotificacaoWhatsApp();
// case "3":
// default:
// return new NotificacaoEmail();
// }
// }

// break;

// case 4:
// // DEVOLVER LIVRO
// System.out.print("Digite o título do livro que deseja devolver: ");
// String tituloLivro = scanner.nextLine();

// Livro livroSelecionado = null;

// for (Livro livro : livros) {
// if (livro.getTitulo().equalsIgnoreCase(tituloLivro)) {
// livroSelecionado = livro;
// break;
// }
// }

// if (livroSelecionado == null) {
// System.out.println("Livro não encontrado!");
// } else if (livroSelecionado.isDisponivel()) {
// System.out.println("Este livro não precisa ser devolvido, já está
// disponível!");
// } else {
// livroSelecionado.setDisponivel(true);
// System.out.println(
// "Devolução do livro " + livroSelecionado.getTitulo() + " realizada com
// sucesso!");
// }
// break;
// case 5:
// // Listar Livros
// System.out.println("\nLista de Livros");
// // Exibe os detalhes de cada livro na lista
// for (Livro livro : livros) {
// System.out.println("Título: " + livro.getTitulo() + ", Autor: " +
// livro.getAutor()
// + ", Disponível: " + livro.isDisponivel());
// }
// break;

// case 6:
// // Listar livros emprestados e disponíveis
// System.out.println("\nLivros Disponíveis:");
// for (Livro livro : livros) {
// if (livro.isDisponivel()) {
// System.out.println("- " + livro.getTitulo() + " (Autor: " + livro.getAutor()
// + ")");
// }
// }

// System.out.println("\nLivros Emprestados:");
// for (Livro livro : livros) {
// if (!livro.isDisponivel()) {
// System.out.println("- " + livro.getTitulo() + " (Autor: " + livro.getAutor()
// + ")");
// }
// }
// break;

// case 7: // FAZER Listar histórico de empréstimo

// System.out.println("\nHistórico de Empréstimos:");
// for (Emprestimo emprestimo : emprestimos) {
// System.out.println("Livro: " + emprestimo.getLivro().getTitulo()
// + " | Usuário: " + emprestimo.getUsuario().getNome()
// + " | Data de Empréstimo: " + emprestimo.getDataEmprestimo()
// + " | Data de Devolução: " + emprestimo.getDataDevolucao());
// }
// break;
// case 8:
// // Sair do sistema
// System.out.println("Saindo do sistema...");
// break;

// default:
// // Caso o usuário escolha uma opção inválida
// System.out.println("Opção inválida. Tente novamente.");
// }
// } while (opcao != 8); // Continua exibindo o menu até que o usuário escolha a
// opção de sair

// scanner.close(); // Fecha o scanner para liberar recursos
// }

// private static void notificarUsuario(int usuarioIndex, String string) {
// // TODO Auto-generated method stub
// throw new UnsupportedOperationException("Unimplemented method
// 'notificarUsuario'");
// }
// }