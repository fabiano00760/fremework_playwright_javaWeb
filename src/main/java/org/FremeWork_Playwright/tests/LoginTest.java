package org.FremeWork_Playwright.tests;

import org.FremeWork_Playwright.pageobjects.LoginPage;
import org.FremeWork_Playwright.utils.PlaywrightDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginTest {

    // Este método será executado antes de cada teste, iniciando o navegador
    @Before
    public void setUp() {
        PlaywrightDriver.iniciarBrowser(); // Inicializa o browser
    }

    @Test
    public void testeLoginValido() {
        LoginPage loginPage = new LoginPage(PlaywrightDriver.getPage());
        loginPage.SingnIn();
        loginPage.preencherDadosLogin();
        loginPage.isLoginBemSucedido();
    }

    @Test
    public void testeLoginInvalido(){
        LoginPage loginPage = new LoginPage(PlaywrightDriver.getPage());
        loginPage.SingnIn();
        loginPage.preencherDadosLogin1();
        loginPage.isLoginBemSucedido();
    }

    // Este método será executado depois de cada teste, fechando o navegador
    @After
    public void tearDown() {
        PlaywrightDriver.fecharBrowser(); // Fecha o navegador
    }
}
