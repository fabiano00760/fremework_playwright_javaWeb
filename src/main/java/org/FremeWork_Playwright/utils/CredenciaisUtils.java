package org.FremeWork_Playwright.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class CredenciaisUtils {

    // Classe para representar a estrutura do email e senha
    public static class EmailSenha {
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

    /**
     * Lê o arquivo JSON e retorna o primeiro email e senha para login.
     *
     * @return EmailSenha contendo o email e senha para login.
     */
    public static EmailSenha obterEmailESenha() {
        // Caminho do arquivo JSON onde as credenciais estão armazenadas
        String caminhoArquivo = "massa/emailGerado.json";

        // Ler os dados do arquivo
        try (FileReader reader = new FileReader(caminhoArquivo)) {
            Type listType = new TypeToken<List<EmailSenha>>(){}.getType();
            Gson gson = new Gson();
            List<EmailSenha> emailsGerados = gson.fromJson(reader, listType);

            // Retorna o primeiro item encontrado no JSON
            if (emailsGerados != null && !emailsGerados.isEmpty()) {
                return emailsGerados.get(0);  // Retorna o primeiro objeto de email e senha
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;  // Retorna null caso não encontre o arquivo ou não haja credenciais
    }
}
