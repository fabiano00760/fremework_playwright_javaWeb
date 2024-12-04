package org.FremeWork_Playwright.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    // Método para inserir um novo usuário
    public void inserirUsuario(String nome, String email) {
        String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, email);
            int rowsInserted = stmt.executeUpdate();
            System.out.println("Usuário inserido com sucesso! Linhas afetadas: " + rowsInserted);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os usuários
    public void listarUsuarios() {
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                System.out.println("ID: " + id + ", Nome: " + nome + ", Email: " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar o nome de um usuário
    public void atualizarUsuario(int id, String novoNome) {
        String sql = "UPDATE usuarios SET nome = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoNome);
            stmt.setInt(2, id);
            int rowsUpdated = stmt.executeUpdate();
            System.out.println("Usuário atualizado com sucesso! Linhas afetadas: " + rowsUpdated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar um usuário pelo ID
    public void deletarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("O usuário com ID " + id + " foi deletado com sucesso!");
            } else {
                System.out.println("Nenhum usuário encontrado com o ID " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar todos os usuários
    public void deletarTodosUsuarios() {
        String sql = "DELETE FROM usuarios";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            int rowsDeleted = stmt.executeUpdate();
            System.out.println("Todos os usuários foram deletados. Linhas afetadas: " + rowsDeleted);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
