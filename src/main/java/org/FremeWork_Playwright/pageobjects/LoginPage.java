package org.FremeWork_Playwright.pageobjects;

import com.microsoft.playwright.Page;
import org.FremeWork_Playwright.utils.CredenciaisUtils;
import org.junit.Assert;

public class LoginPage extends BasePage {

    // XPaths dos elementos na página
    private final String signIn = "(//a[contains(.,'Sign In')])[1]";
    private final String campoEmail = "//input[@id='email']";
    private final String campoSenha = "//input[@title='Password']";
    private final String btnSingIn = "(//span[contains(.,'Sign In')])[1]";
    private final String loginSucesso = "(//span[@class='logged-in'][contains(.,'Welcome, Fabiano Silva!')])[1]";

    public LoginPage(Page page) {
        super(page); // Passa o objeto Page para a BasePage
    }

    public void acessarTelaLogin() {
        clicar(signIn);
        esperarVisibilidade(campoEmail);
    }

    public void preencherDadosLogin(String email, String senha) {
        preencher(campoEmail, email);
        preencher(campoSenha, senha);
        clicar(btnSingIn);
    }

    public void preencherDadosLoginComCredenciais() {
        // Aqui você pode utilizar um método utilitário, se necessário, para preencher com credenciais de um arquivo JSON
        // Por exemplo, se você tiver uma classe CredenciaisUtils:
        CredenciaisUtils.EmailSenha credenciais = CredenciaisUtils.obterEmailESenha();

        if (credenciais != null) {
            preencherDadosLogin(credenciais.getEmail(), credenciais.getSenha());
        } else {
            throw new IllegalStateException("Credenciais não encontradas no arquivo JSON.");
        }
    }
    public void preencherDadosLoginInvalido(String email, String senha){
        preencher(campoEmail, email);
        preencher(campoSenha, senha);
        clicar(btnSingIn);
    }

    public void validarLoginBemSucedido() {
        // Validação de sucesso no login
        BasePage.esperarVisibilidade(loginSucesso);
        Assert.assertTrue("Welcome, Fabiano Silva!", isVisivel(loginSucesso));

    }

}
