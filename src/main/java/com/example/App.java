package com.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.example.dao.Conexao;
import com.example.dao.EmprestimoDao;
import com.example.dao.LivroDao;
import com.example.dao.UsuarioDao;
import com.example.entity.Emprestimo;
import com.example.entity.Livro;
import com.example.entity.Usuario;

public class App {
    public static void main(String[] args) {
        try (Connection conexao = Conexao.conectar()) {
            LivroDao livroDao = new LivroDao(conexao);
            UsuarioDao usuarioDao = new UsuarioDao(conexao);
            EmprestimoDao emprestimoDao = new EmprestimoDao(conexao);

            Scanner scanner = new Scanner(System.in);
            int opcao;

            do {
                System.out.println("\n Biblioteca ");
                System.out.println("1. Cadastrar Livro");
                System.out.println("2. Cadastrar Usuário");
                System.out.println("3. Realizar Empréstimo");
                System.out.println("4. Devolver livro");
                System.out.println("5. Listar Livros");
                System.out.println("6. Listar livros emprestados e disponíveis");
                System.out.println("7. Listar histórico de empréstimo");
                System.out.println("8. Sair");
                System.out.print("Escolha uma opção: ");

                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarLivro(scanner, livroDao);
                        break;
                    case 2:
                        cadastrarUsuario(scanner, usuarioDao);
                        break;
                    case 3:
                        realizarEmprestimo(scanner, usuarioDao, livroDao, emprestimoDao);
                        break;
                    case 4:
                        devolverLivro(scanner, emprestimoDao, livroDao);
                        break;
                    case 5:
                        listarLivros(livroDao);
                        break;
                    case 6:
                        listarDisponiveisEmprestados(livroDao);
                        break;
                    case 7:
                        listarHistoricoEmprestimos(emprestimoDao);
                        break;
                    case 8:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } while (opcao != 8);
            scanner.close();

        } catch (SQLException e) {
            System.err.println("Erro na conexão com o banco: " + e.getMessage());
        }
    }

    private static void cadastrarLivro(Scanner scanner, LivroDao livroDao) {
        System.out.print("ID DO LIVRO: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("TÍTULO: ");
        String titulo = scanner.nextLine().trim();

        System.out.print("ANO DE PUBLICAÇÃO: ");
        int ano = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("EDITORA: ");
        String editora = scanner.nextLine().trim();

        System.out.print("AUTOR: ");
        String autor = scanner.nextLine().trim();

        Livro livro = new Livro(id, titulo, ano, editora, autor, true);
        livroDao.adicionar(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    private static void cadastrarUsuario(Scanner scanner, UsuarioDao usuarioDao) {
        System.out.print("ID DO USUÁRIO: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();

        System.out.print("CPF (11 dígitos): ");
        String cpf = scanner.nextLine().trim();

        System.out.print("E-mail: ");
        String email = scanner.nextLine().trim();

        String preferencia;
        while (true) {
            System.out.println("Preferência de notificação:");
            System.out.println("1 - SMS");
            System.out.println("2 - WhatsApp");
            System.out.println("3 - E-mail");
            System.out.print("Opção: ");
            preferencia = scanner.nextLine().trim();

            if (preferencia.matches("[1-3]"))
                break;
            System.out.println("Opção inválida!");
        }

        Usuario usuario = new Usuario(id, nome, cpf, email, preferencia);
        usuarioDao.adicionar(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void realizarEmprestimo(Scanner scanner, UsuarioDao usuarioDao,
            LivroDao livroDao, EmprestimoDao emprestimoDao) {
        List<Usuario> usuarios = usuarioDao.consultar();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado!");
            return;
        }

        System.out.println("\nUsuários:");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + ". " + usuarios.get(i).getNome() + " (ID: " + usuarios.get(i).getId() + ")");
        }
        System.out.print("Escolha o usuário: ");
        int userIdx = scanner.nextInt() - 1;

        List<Livro> disponiveis = livroDao.consultar().stream()
                .filter(Livro::isDisponivel)
                .collect(Collectors.toList());
        if (disponiveis.isEmpty()) {
            System.out.println("Nenhum livro disponível!");
            return;
        }

        System.out.println("\nLivros disponíveis:");
        for (int i = 0; i < disponiveis.size(); i++) {
            System.out.println((i + 1) + ". " + disponiveis.get(i).getTitulo() + " (ID: " + disponiveis.get(i).getId() + ")");
        }
        System.out.print("Escolha o livro: ");
        int livroIdx = scanner.nextInt() - 1;
        scanner.nextLine();

        Usuario usuario = usuarios.get(userIdx);
        Livro livro = disponiveis.get(livroIdx);

        livro.setDisponivel(false);
        livroDao.alterar(livro);

        Emprestimo emp = new Emprestimo(
                usuario,
                livro,
                LocalDate.now(),
                LocalDate.now().plusDays(7));
        emprestimoDao.adicionar(emp);

        System.out.println("Empréstimo registrado!");
    }

    private static void devolverLivro(Scanner scanner, EmprestimoDao emprestimoDao, LivroDao livroDao) {
        List<Emprestimo> emprestimos = emprestimoDao.consultar();
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo ativo!");
            return;
        }

        System.out.println("\nEmpréstimos ativos:");
        for (int i = 0; i < emprestimos.size(); i++) {
            Emprestimo emp = emprestimos.get(i);
            System.out.println((i + 1) + ". " + emp.getLivro().getTitulo() +
                    " - " + emp.getUsuario().getNome() + " (ID Usuário: " + emp.getUsuario().getId() + ", ID Livro: " + emp.getLivro().getId() + ")");
        }
        System.out.print("Escolha o empréstimo para devolver: ");
        int empIdx = scanner.nextInt() - 1;
        scanner.nextLine();

        Emprestimo emp = emprestimos.get(empIdx);
        emprestimoDao.remover(emp.getUsuario().getId(), emp.getLivro().getId());

        Livro livro = livroDao.consultarPorId(emp.getLivro().getId());
        livro.setDisponivel(true);
        livroDao.alterar(livro);

        System.out.println("Livro devolvido com sucesso!");
    }

    private static void listarLivros(LivroDao livroDao) {
        List<Livro> livros = livroDao.consultar();
        System.out.println("\nTodos os livros:");
        for (Livro l : livros) {
            System.out.println(l.getId() + " - " + l.getTitulo() + " - " + l.getAutor() +
                    " (" + l.getAno() + ") - " +
                    (l.isDisponivel() ? "Disponível" : "Emprestado"));
        }
    }

    private static void listarDisponiveisEmprestados(LivroDao livroDao) {
        List<Livro> livros = livroDao.consultar();

        System.out.println("\nLivros disponíveis:");
        livros.stream()
                .filter(Livro::isDisponivel)
                .forEach(l -> System.out.println(l.getId() + " - " + l.getTitulo() + " - " + l.getAutor()));

        System.out.println("\nLivros emprestados:");
        livros.stream()
                .filter(l -> !l.isDisponivel())
                .forEach(l -> System.out.println(l.getId() + " - " + l.getTitulo() + " - " + l.getAutor()));
    }

    private static void listarHistoricoEmprestimos(EmprestimoDao emprestimoDao) {
        List<Emprestimo> emprestimos = emprestimoDao.consultar();
        System.out.println("\nHistórico de empréstimos:");
        for (Emprestimo emp : emprestimos) {
            System.out.println(emp.getLivro().getId() + " - " + emp.getLivro().getTitulo() + " - " +
                    emp.getUsuario().getId() + " - " + emp.getUsuario().getNome() + " - " +
                    emp.getDataEmprestimo() + " a " + emp.getDataDevolucao());
        }
    }
}
