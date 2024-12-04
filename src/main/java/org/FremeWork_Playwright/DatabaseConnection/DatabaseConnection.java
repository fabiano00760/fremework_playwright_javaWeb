package org.FremeWork_Playwright.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseConnection {
    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());

    // URL, usuário e senha agora são variáveis para flexibilidade
    private static final String URL = "jdbc:mysql://localhost:3306/Cadastro_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Edu00760@";

    // Metodo para obter a conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            logger.severe("Erro ao conectar ao banco de dados: " + e.getMessage());
            throw e;  // Propaga a exceção após o log
        }
    }
}
