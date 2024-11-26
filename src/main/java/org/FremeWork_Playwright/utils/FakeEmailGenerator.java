package org.FremeWork_Playwright.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FakeEmailGenerator {

    // Lista que armazenará todos os emails e senhas gerados
    private static List<EmailSenha> emailsGerados = new ArrayList<>();

    public static void main(String[] args) {
        // Gerar email e senha
        String email = gerarEmailFake();
        String senha = gerarSenhaFake();

        // Salvar email e senha em arquivo JSON
        salvarEmailESenhaEmArquivo(email, senha);
    }

    /**
     * Gera um email fake único baseado em UUID.
     *
     * @return um email aleatório.
     */
    public static String gerarEmailFake() {
        // Gerar um UUID único para garantir um email único
        String uuid = UUID.randomUUID().toString();
        // Montar o email no formato <username>-<UUID>@example.com
        return "username-" + uuid + "@example.com";
    }

    /**
     * Gera uma senha aleatória e simples.
     * A senha gerada é "Senha@123" para fins de exemplo. Você pode personalizar conforme necessário.
     *
     * @return uma senha aleatória.
     */
    public static String gerarSenhaFake() {
        // Senha simples gerada com um formato fixo
        return "Senha@123";
    }

    /**
     * Salva o email e a senha gerada em um arquivo JSON. O arquivo não será sobrescrito, os novos registros são adicionados.
     *
     * @param email o email gerado.
     * @param senha a senha gerada.
     */
    public static void salvarEmailESenhaEmArquivo(String email, String senha) {
        // Criar um objeto para salvar o email e senha
        EmailSenha emailSenhaObj = new EmailSenha(email, senha);

        // Criar a pasta onde os arquivos serão armazenados (caso não exista)
        File pasta = new File("massa");
        if (!pasta.exists()) {
            pasta.mkdir();  // Cria a pasta "massa"
        }

        // Criar o caminho do arquivo para salvar os dados
        File arquivo = new File(pasta, "emailGerado.json");

        // Se o arquivo já existe, tentamos ler os dados existentes
        List<EmailSenha> emailsExistentes = new ArrayList<>();
        if (arquivo.exists()) {
            // Ler os dados existentes no arquivo
            try (FileReader reader = new FileReader(arquivo)) {
                // Definir o tipo da lista a ser lida
                Type listType = new TypeToken<List<EmailSenha>>(){}.getType();
                Gson gson = new Gson();
                emailsExistentes = gson.fromJson(reader, listType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Adicionar o novo email e senha à lista existente
        emailsExistentes.add(emailSenhaObj);

        // Usar o Gson para converter a lista de registros em JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();  // Usando setPrettyPrinting para formatação
        String json = gson.toJson(emailsExistentes);

        // Salvar o JSON em um arquivo
        try (FileWriter fileWriter = new FileWriter(arquivo)) {
            fileWriter.write(json);  // Sobrescreve o conteúdo com todos os registros, incluindo o novo
            System.out.println("Email e senha salvos com sucesso: " + email + " / " + senha);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Classe para representar a estrutura de email e senha
    static class EmailSenha {

        private String email;
        private String senha;

        public EmailSenha(String email, String senha) {
            this.email = email;
            this.senha = senha;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }
    }
}
