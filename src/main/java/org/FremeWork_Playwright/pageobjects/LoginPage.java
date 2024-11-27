package org.FremeWork_Playwright.pageobjects;

import com.microsoft.playwright.Page;
import org.FremeWork_Playwright.utils.CredenciaisUtils;

public class LoginPage {

    private Page page;

    // XPath dos elementos na página
    private String signIn = "(//a[contains(.,'Sign In')])[1]";  // XPath para o campo de usuário
    private String campoEmail = "//input[@id='email']";    // XPath para o campo de email
    private String campoSenha = "//input[@title='Password']"; // XPath para o campo de senha
    private String btnSingIn = "(//span[contains(.,'Sign In')])[1]";      // XPath para o botão de login
    private String loginSucesso ="(//span[@class='logged-in'][contains(.,'Welcome, Fabiano Silva!')])[1]";// XPath para validar login com sucesso

    public LoginPage(Page page) {
        this.page = page;
    }

    // Método para clicar no link "Sign In"
    public void SingnIn(){
        page.locator(signIn).click();
        page.waitForTimeout(2000);
    }

    // Método para preencher o email e senha usando as credenciais do JSON
    public void preencherDadosLogin() {
        // Obter as credenciais salvas no arquivo JSON
        CredenciaisUtils.EmailSenha credenciais = CredenciaisUtils.obterEmailESenha();

        if (credenciais != null) {
            // Preencher os campos de email e senha com as credenciais
            page.locator(campoEmail).fill(credenciais.getEmail());
            page.locator(campoSenha).fill(credenciais.getSenha());
            page.locator(btnSingIn).click();
            page.waitForTimeout(2000);
            page.locator(loginSucesso).textContent();
            page.waitForTimeout(2000);  // Aguardar a navegação ou qualquer outro processo após o login
        } else {
            System.out.println("Credenciais não encontradas no arquivo JSON.");
        }

    }


    // Método para verificar se o login foi realizado com sucesso, verificando a URL ou um elemento específico
    public boolean isLoginBemSucedido() {
        return page.url().contains("dashboard");  // Verifica se a URL contém "dashboard", indicando que o login foi bem-sucedido
    }

    // Método para preencher o email e senha usando as credenciais do JSON
    public void preencherDadosLogin1() {
        // Obter as credenciais salvas no arquivo JSON
        CredenciaisUtils.EmailSenha credenciais = CredenciaisUtils.obterEmailESenha();

        if (credenciais != null) {
            // Preencher os campos de email e senha com as credenciais
            page.locator(campoEmail).fill(credenciais.getSenha());
            page.locator(campoSenha).fill(credenciais.getEmail());
            page.locator(btnSingIn).click();
            page.waitForTimeout(2000);  // Aguardar a navegação ou qualquer outro processo após o login
        } else {
            System.out.println("Credenciais não encontradas no arquivo JSON.");
        }

    }
}
