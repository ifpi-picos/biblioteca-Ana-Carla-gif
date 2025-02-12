
package com.example.dao;

import com.example.entity.Livro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDao {
    private Connection conexao;

    public LivroDao(Connection conexao) {
        this.conexao = conexao;
    }

    public void adicionar(Livro livro) {
        String sql = "INSERT INTO livros (id, titulo, autor, editora, ano, disponivel) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, livro.getId());
            stmt.setString(2, livro.getTitulo());
            stmt.setString(3, livro.getAutor());
            stmt.setString(4, livro.getEditora());
            stmt.setInt(5, livro.getAno());
            stmt.setBoolean(6, livro.isDisponivel());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Livro> consultar() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";
        try (Statement stm = conexao.createStatement(); ResultSet result = stm.executeQuery(sql)) {
            while (result.next()) {
                livros.add(new Livro(
                    result.getInt("id"),
                    result.getString("titulo"),
                    result.getInt("ano"),
                    result.getString("editora"),
                    result.getString("autor"),
                    result.getBoolean("disponivel")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public Livro consultarPorId(int livroId) {
        String sql = "SELECT * FROM livros WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, livroId);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return new Livro(
                    result.getInt("id"),
                    result.getString("titulo"),
                    result.getInt("ano"),
                    result.getString("editora"),
                    result.getString("autor"),
                    result.getBoolean("disponivel")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void alterar(Livro livro) {
        String sql = "UPDATE livros SET titulo = ?, autor = ?, editora = ?, ano = ?, disponivel = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getEditora());
            stmt.setInt(4, livro.getAno());
            stmt.setBoolean(5, livro.isDisponivel());
            stmt.setInt(6, livro.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int livroId) {
        String sql = "DELETE FROM livros WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, livroId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

