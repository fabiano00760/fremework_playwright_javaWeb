package org.FremeWork_Playwright.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        // URL de conexão com o banco MySQL
        // configuração com o nome da tabela cadastri_db
        String url = "jdbc:mysql://localhost:3306/Cadastro_db";
        String user = "root"; // Seu usuário
        String password = "Edu00760@"; // Sua senha

        // Retorna a conexão
        return DriverManager.getConnection(url, user, password);
    }
}
