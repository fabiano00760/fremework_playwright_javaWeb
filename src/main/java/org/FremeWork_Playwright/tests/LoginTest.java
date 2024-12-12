package org.FremeWork_Playwright.tests;

import com.microsoft.playwright.Page;
import org.FremeWork_Playwright.page.LoginPage;
import org.FremeWork_Playwright.utils.Evidencias;
import org.FremeWork_Playwright.Config.PlaywrightDriver;
import org.FremeWork_Playwright.utils.FakeEmailGenerator;
import org.junit.*;

public class LoginTest {
    String emailGerado = FakeEmailGenerator.gerarEmailFake();
    String senhaGerada = FakeEmailGenerator.gerarSenhaFake();

    private static Page page;
    private LoginPage loginPage;
    private Evidencias evidencias;

    @Before
    public void setUp() {
        PlaywrightDriver.iniciarBrowser();
        page = PlaywrightDriver.getPage();
        loginPage = new LoginPage(page);
        evidencias = new Evidencias("LoginTest");
    }

    @Test
    public void testeLoginValido() {
        // Acessar a tela de login
        loginPage.acessarTelaLogin();
        evidencias.capturarEvidencia(page, "01_Tela_Login");

        // Preencher dados de login
        loginPage.preencherDadosLoginComCredenciais();
        evidencias.capturarEvidencia(page, "02_Dados_Login");

        // Validar login bem-sucedido
        loginPage.validarLoginBemSucedido();
    }

    @Test
    public void testeLoginInvalido() {

        // Acessar a tela de login
        loginPage.acessarTelaLogin();
        evidencias.capturarEvidencia(page, "01_Tela_Login");

        // Preencher dados de login inválido
        loginPage.preencherDadosLoginInvalido(senhaGerada,emailGerado);
        evidencias.capturarEvidencia(page, "02_Dados_Login_Invalido");

        // Validar que o login falhou
    }

    @After
    public void tearDown() {
        PlaywrightDriver.fecharBrowser();
    }
}
