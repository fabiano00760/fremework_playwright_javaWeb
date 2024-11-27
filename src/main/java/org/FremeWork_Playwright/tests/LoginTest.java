package org.FremeWork_Playwright.tests;

import com.microsoft.playwright.Page;
import org.FremeWork_Playwright.pageobjects.LoginPage;
import org.FremeWork_Playwright.utils.Evidencias;
import org.FremeWork_Playwright.utils.PlaywrightDriver;
import org.junit.*;

public class LoginTest {

    private static Page page;

    // Este metodo será executado antes de cada teste, iniciando o navegador
    @Before
    public void setUp() {
        PlaywrightDriver.iniciarBrowser(); // Inicializa o browser
        page = PlaywrightDriver.getPage(); // Obtém a página inicializada
    }

    Evidencias evidencias = new Evidencias("Login");

    @Test
    public void testeLoginValido() {

        LoginPage loginPage = new LoginPage(PlaywrightDriver.getPage());
        loginPage.SingnIn();
        evidencias.capturarEvidencia(page, "01_Acesso_Pagina_Inicial");
        loginPage.preencherDadosLogin();
        evidencias.capturarEvidencia(page, "02_Acesso_Pagina_Inicial");
        loginPage.isLoginBemSucedido();

    }

    @Test
    public void testeLoginInvalido(){
        LoginPage loginPage = new LoginPage(PlaywrightDriver.getPage());
        evidencias.capturarEvidencia(page, "01_Acesso_Pagina_Inicial");

        loginPage.SingnIn();
        evidencias.capturarEvidencia(page, "02_Singn_In");

        loginPage.preencherDadosLogin1();
        evidencias.capturarEvidencia(page, "03_preencher_DadosLogin");


    }

    // Este método será executado depois de cada teste, fechando o navegador
    @After
    public void tearDown() {
        PlaywrightDriver.fecharBrowser(); // Fecha o navegador
    }
}
