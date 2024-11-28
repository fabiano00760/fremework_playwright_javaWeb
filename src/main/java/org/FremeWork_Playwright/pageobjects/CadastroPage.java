package org.FremeWork_Playwright.pageobjects;

import com.microsoft.playwright.Page;

public class CadastroPage extends BasePage {

    // XPaths dos elementos na página
    private final String campoCreateAnAccount = "(//a[contains(.,'Create an Account')])[1]";
    private final String campoFirstName = "//input[@id='firstname']";
    private final String campoLastName = "//input[@name='lastname']";
    private final String campoEmail = "//input[contains(@id,'email_address')]";
    private final String campoPassword = "//input[@id='password']";
    private final String campoConfirmPassword = "//input[@id='password-confirmation']";
    private final String btnCreateAnAccount = "//button[contains(.,'Create an Account')]";
    private final String loginSucesso = "(//div[contains(.,'Thank you for registering with Main Website Store.')])[6]";

    public CadastroPage(Page page) {
        super(page); // Passa o objeto Page para a BasePage
    }

    public void acessarTelaCadastro() {
        clicar(campoCreateAnAccount);
        esperarVisibilidade(campoFirstName);
    }

    public void preencherCadastro(String nome, String sobrenome, String email, String senha) {
        preencher(campoFirstName, nome);
        preencher(campoLastName, sobrenome);
        preencher(campoEmail, email);
        preencher(campoPassword, senha);
        preencher(campoConfirmPassword, senha);
    }

    public void confirmarCadastro() {
        clicar(btnCreateAnAccount);
    }

    public void validarCadastroComSucesso() {
        // Use o locator correto para procurar pelo texto de sucesso
        page.locator("text='Thank you for registering with Main Website Store.'").waitFor();
        System.out.println("Cadastro realizado com sucesso!");
    }
}
