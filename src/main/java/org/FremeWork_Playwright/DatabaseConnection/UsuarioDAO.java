package org.FremeWork_Playwright.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {

    // Metodo para salvar o usuário na tabela 'cadastro'
    public static void cadastroUser(String firstName, String lastName, String email, String password) {
        // Corrigindo o nome da tabela para 'cadastro' dentro do banco 'Cadastro_db'
        String sql = "INSERT INTO usuarios (FirstName, LastName, Email, Password) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, password);

            int rowsInserted = stmt.executeUpdate();
            System.out.println("Cadastro inserido com sucesso! Linhas afetadas: " + rowsInserted);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os usuários
    public void listarUsuarios() {
        String sql = "SELECT * FROM cadastro";  // Corrigido para 'cadastro'

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             var rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("FirstName") + " " + rs.getString("LastName");
                String email = rs.getString("Email");
                System.out.println("ID: " + id + ", Nome: " + nome + ", Email: " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar o nome de um usuário
    public void atualizarUsuario(int id, String novoNome) {
        String sql = "UPDATE cadastro SET FirstName = ? WHERE id = ?";  // Corrigido para 'cadastro'

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
        String sql = "DELETE FROM cadastro WHERE id = ?";  // Corrigido para 'cadastro'

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Usuário com ID " + id + " foi deletado com sucesso!");
            } else {
                System.out.println("Nenhum usuário encontrado com o ID " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar todos os usuários
    public void deletarTodosUsuarios() {
        String sql = "DELETE FROM cadastro";  // Corrigido para 'cadastro'
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int rowsDeleted = stmt.executeUpdate();
            System.out.println("Todos os usuários foram deletados. Linhas afetadas: " + rowsDeleted);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
