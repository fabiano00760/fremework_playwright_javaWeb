package org.FremeWork_Playwright.utils;

import com.github.javafaker.Faker;
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

public class FakeEmailGenerator {

    // Lista que armazenará todos os emails e senhas gerados
    private static List<EmailSenha> emailsGerados = new ArrayList<>();

    public static void main(String[] args) {
        // Gerar email e senha usando Faker
        String email = gerarEmailFake();
        String senha = gerarSenhaFake();

        // Salvar email e senha em arquivo JSON
        salvarEmailESenhaEmArquivo(email, senha);
    }

    /**
     * Gera um email fake utilizando a biblioteca Faker.
     *
     * @return um email aleatório.
     */
    public static String gerarEmailFake() {
        // Usando Faker para gerar um nome e combiná-lo com um domínio fictício
        Faker faker = new Faker();
        String nome = faker.name().firstName().toLowerCase();  // Nome fictício
        String dominio = faker.internet().domainName();  // Domínio fictício

        // Monta o email no formato nome@dominio
        return nome + "@" + dominio;
    }

    /**
     * Gera uma senha aleatória utilizando a biblioteca Faker.
     * A senha gerada tem uma combinação de letras, números e símbolos.
     *
     * @return uma senha aleatória.
     */
    public static String gerarSenhaFake() {
        Faker faker = new Faker();
        // Usando Faker para gerar uma senha aleatória com letras, números e símbolos
        return faker.internet().password(8, 16, true, true);  // Senha de 8 a 16 caracteres
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
