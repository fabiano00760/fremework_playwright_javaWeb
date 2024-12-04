package org.FremeWork_Playwright.DatabaseConnection;

public class Main {

    public static void main(String[] args) {
        // Criando um objeto de DAO para manipulação de usuários
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // Inserir um novo usuário
        //  usuarioDAO.inserirUsuario("João", "joao@example.com");
        // usuarioDAO.inserirUsuario("Maria", "maria@example.com");

        // Listar todos os usuários
        // System.out.println("Lista de usuários:");
        //   usuarioDAO.listarUsuarios();

        // Atualizar o nome de um usuário
        // usuarioDAO.atualizarUsuario(1, "Fabiano Atualizado");
        //   usuarioDAO.listarUsuarios();
        // usuarioDAO.inserirUsuario("jojo", "jojo@teste.com");
        //  usuarioDAO.deletarUsuario(91);
        usuarioDAO.deletarTodosUsuarios();

        // Listar novamente após a atualização
        System.out.println("Lista de usuários após atualização:");
        usuarioDAO.listarUsuarios();
    }
}
