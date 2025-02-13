
package com.example.dao;

import com.example.entity.Emprestimo;
import com.example.entity.Usuario;
import com.example.entity.Livro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDao {
    private Connection conexao;

    public EmprestimoDao(Connection conexao) {
         this.conexao = conexao;
     }

    public void adicionar(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimos (data_emprestimo, data_devolucao, usuario_id, livro_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.setDate(2, Date.valueOf(emprestimo.getDataDevolucao()));
            stmt.setInt(3, emprestimo.getUsuario().getId());
            stmt.setInt(4, emprestimo.getLivro().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Emprestimo> consultar() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT e.*, l.titulo AS livro_titulo, u.nome AS usuario_nome " +
                     "FROM emprestimos e " +
                     "JOIN livros l ON e.livro_id = l.id " +
                     "JOIN usuarios u ON e.usuario_id = u.id";
        try (Statement stm = conexao.createStatement(); ResultSet result = stm.executeQuery(sql)) {
            while (result.next()) {
                // Cria o livro com os dados reais do banco de dados
                Livro livro = new Livro(
                    result.getInt("livro_id"),
                    result.getString("livro_titulo"),
                    0, // Ano não é necessário no histórico
                    "", // Editora não é necessária no histórico
                    "", // Autor não é necessário no histórico
                    false // Disponibilidade não é necessária no histórico
                );

                // Cria o usuário com os dados reais do banco de dados
                Usuario usuario = new Usuario(
                    result.getInt("usuario_id"),
                    result.getString("usuario_nome"),
                    "", // CPF não é necessário no histórico
                    "", // Email não é necessário no histórico
                    ""  // Preferência de notificação não é necessária no histórico
                );

                // Cria o empréstimo com os dados reais
                Emprestimo emprestimo = new Emprestimo(
                    usuario,
                    livro,
                    result.getDate("data_emprestimo").toLocalDate(),
                    result.getDate("data_devolucao").toLocalDate()
                );
                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emprestimos;
    }

    public void remover(int usuarioId, int livroId) {
        String sql = "DELETE FROM emprestimos WHERE usuario_id = ? AND livro_id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, livroId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




























// package com.example.dao;

// import com.example.entity.Emprestimo;
// import com.example.entity.Usuario;
// import com.example.entity.Livro;
// import java.sql.*;
// import java.util.ArrayList;
// import java.util.List;

// public class EmprestimoDao {
//     private Connection conexao;

//     public EmprestimoDao(Connection conexao) {
//          this.conexao = conexao;
//      }

//     public void adicionar(Emprestimo emprestimo) {
//         String sql = "INSERT INTO emprestimos (data_emprestimo, data_devolucao, usuario_id, livro_id) VALUES (?, ?, ?, ?)";
//         try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
//             stmt.setDate(1, Date.valueOf(emprestimo.getDataEmprestimo()));
//             stmt.setDate(2, Date.valueOf(emprestimo.getDataDevolucao()));
//             stmt.setInt(3, emprestimo.getUsuario().getId());
//             stmt.setInt(4, emprestimo.getLivro().getId());
//             stmt.executeUpdate();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

    
//     public List<Emprestimo> consultar() {
//         List<Emprestimo> emprestimos = new ArrayList<>();
//         String sql = "SELECT * FROM emprestimos";
//         try (Statement stm = conexao.createStatement(); ResultSet result = stm.executeQuery(sql)) {
//             while (result.next()) {
//                 Usuario usuario = new Usuario(result.getInt("usuario_id"), "", "", "", "");
//                 Livro livro = new Livro(result.getInt("livro_id"), "", 0, "", "", true);
//                 Emprestimo emprestimo = new Emprestimo(usuario, livro, 
//                     result.getDate("data_emprestimo").toLocalDate(), 
//                     result.getDate("data_devolucao").toLocalDate()
//                 );
//                 emprestimos.add(emprestimo);
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//         return emprestimos;
//     }

//     public void remover(int usuarioId, int livroId) {
//         String sql = "DELETE FROM emprestimos WHERE usuario_id = ? AND livro_id = ?";
//         try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
//             stmt.setInt(1, usuarioId);
//             stmt.setInt(2, livroId);
//             stmt.executeUpdate();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }
// }
