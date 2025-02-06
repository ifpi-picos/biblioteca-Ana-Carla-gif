
//Importações necessárias
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
