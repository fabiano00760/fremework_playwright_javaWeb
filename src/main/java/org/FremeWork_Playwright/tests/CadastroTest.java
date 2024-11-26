package org.FremeWork_Playwright.tests;

import org.FremeWork_Playwright.pageobjects.CadastroPage;
import org.FremeWork_Playwright.utils.PlaywrightDriver;
import org.FremeWork_Playwright.utils.FakeEmailGenerator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;

public class CadastroTest {

    @BeforeClass
    public static void setUp() {
        PlaywrightDriver.iniciarBrowser(); // Inicializa o browser
    }

    @Test
    public void CadastroUser() throws FileNotFoundException {
        // Gerar email e senha
        String emailFake = FakeEmailGenerator.gerarEmailFake();
        String senhaFake = FakeEmailGenerator.gerarSenhaFake();

        // Salvar o email e senha gerados em um arquivo JSON
        FakeEmailGenerator.salvarEmailESenhaEmArquivo(emailFake, senhaFake);

        // A partir daqui, use o emailFake no cadastro
        CadastroPage cadastroPage = new CadastroPage(PlaywrightDriver.getPage());

        // Preencher o formulário de cadastro com os dados gerados
        cadastroPage.campoCreateAnAccount();
        cadastroPage.preencherCadastro("Fabiano", "Silva", emailFake, senhaFake);
        cadastroPage.btnCreateAnAccount();
        cadastroPage.validarCadastroComSucesso();
    }

    @AfterClass
    public static void tearDown() {
        PlaywrightDriver.fecharBrowser(); // Fecha o navegador
    }
}
