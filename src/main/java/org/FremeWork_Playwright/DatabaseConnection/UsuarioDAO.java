package org.FremeWork_Playwright.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAO {

    // Método para criar o banco de dados 'Cadastro_db' se não existir
    private static void criarBancoDeDados() {
        String sqlCriarBanco = "CREATE DATABASE IF NOT EXISTS Cadastro_db";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Cria o banco de dados caso não exista
            stmt.executeUpdate(sqlCriarBanco);
            System.out.println("Banco de dados 'Cadastro_db' criado ou já existe.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para criar a tabela 'usuarios' caso não exista
    private static void criarTabelaUsuarios() {
        String sqlCriarTabela = "CREATE TABLE IF NOT EXISTS usuarios (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "FirstName VARCHAR(100), " +
                "LastName VARCHAR(100), " +
                "Email VARCHAR(100), " +
                "Password VARCHAR(100))";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Cria a tabela de usuários caso não exista
            stmt.executeUpdate(sqlCriarTabela);
            System.out.println("Tabela 'usuarios' criada ou já existe.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para salvar o usuário na tabela 'usuarios'
    public static void cadastroUser(String firstName, String lastName, String email, String password) {
        // Criar banco de dados e tabela se não existirem
        criarBancoDeDados();
        criarTabelaUsuarios();

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

    // Metodo para listar todos os usuários
    public void listarUsuarios() {
        String sql = "SELECT * FROM usuarios";  // Alterado para 'usuarios'

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

    // Metodo para atualizar o nome de um usuário
    public void atualizarUsuario(int id, String novoNome) {
        String sql = "UPDATE usuarios SET FirstName = ? WHERE id = ?";  // Alterado para 'usuarios'

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

    // Metodo para deletar um usuário pelo ID
    public void deletarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";  // Alterado para 'usuarios'

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

    // Metodo para deletar todos os usuários
    public void deletarTodosUsuarios() {
        String sql = "DELETE FROM usuarios";  // Alterado para 'usuarios'
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int rowsDeleted = stmt.executeUpdate();
            System.out.println("Todos os usuários foram deletados. Linhas afetadas: " + rowsDeleted);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
