
// package com.example.dao;
package com.example.dao;

import com.example.entity.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    private Connection conexao;

    public UsuarioDao(Connection conexao) {
        this.conexao = conexao;
    }

    public void adicionar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (id, nome, cpf, email, preferencia_notificacao) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, usuario.getId());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getCpf());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getPreferenciaNotificacao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> consultar() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Statement stm = conexao.createStatement(); ResultSet result = stm.executeQuery(sql)) {
            while (result.next()) {
                usuarios.add(new Usuario(
                    result.getInt("id"),
                    result.getString("nome"),
                    result.getString("cpf"),
                    result.getString("email"),
                    result.getString("preferencia_notificacao")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public Usuario consultarPorId(int usuarioId) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return new Usuario(
                    result.getInt("id"),
                    result.getString("nome"),
                    result.getString("cpf"),
                    result.getString("email"),
                    result.getString("preferencia_notificacao")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void alterar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome = ?, cpf = ?, email = ?, preferencia_notificacao = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getPreferenciaNotificacao());
            stmt.setInt(5, usuario.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int usuarioId) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
