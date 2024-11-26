package org.FremeWork_Playwright.tests;

import org.FremeWork_Playwright.pageobjects.LoginPage;
import org.FremeWork_Playwright.utils.PlaywrightDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoginTest {

    @BeforeClass
    public static void setUp() {
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

    @AfterClass
    public static void tearDown() {
        PlaywrightDriver.fecharBrowser(); // Fecha o navegador
    }
}

