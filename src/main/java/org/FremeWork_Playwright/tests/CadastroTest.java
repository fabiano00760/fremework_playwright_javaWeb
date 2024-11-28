package org.FremeWork_Playwright.tests;

import com.microsoft.playwright.Page;
import org.FremeWork_Playwright.pageobjects.BasePage;
import org.FremeWork_Playwright.pageobjects.CadastroPage;
import org.FremeWork_Playwright.pageobjects.PageFactory;
import org.FremeWork_Playwright.utils.Evidencias;
import org.FremeWork_Playwright.Config.PlaywrightDriver;
import org.FremeWork_Playwright.utils.FakeEmailGenerator;
import org.junit.*;

public class CadastroTest {

    private static Page page;
    private static PageFactory pageFactory;
    private Evidencias evidencias;

    @Before
    public void setUp() {
        PlaywrightDriver.iniciarBrowser();
        page = PlaywrightDriver.getPage();
        pageFactory = new PageFactory(page);
        evidencias = new Evidencias("CadastroTest");
    }

    @Test
    public void cadastroUser() {
        CadastroPage cadastroPage = pageFactory.getCadastroPage();

        // Gerar email e senha usando o FakeEmailGenerator
        String emailGerado = FakeEmailGenerator.gerarEmailFake();
        String senhaGerada = FakeEmailGenerator.gerarSenhaFake();
        // Acessar a tela de cadastro
        cadastroPage.acessarTelaCadastro();
        evidencias.capturarEvidencia(page, "01_Tela_Cadastro");
        // Preencher os campos de cadastro com o email e senha gerados
        cadastroPage.preencherCadastro("Fabiano", "Silva", emailGerado, senhaGerada);
        evidencias.capturarEvidencia(page, "02_Campos_Preenchidos");
        // Confirmar cadastro
        cadastroPage.confirmarCadastro();
        evidencias.capturarEvidencia(page, "03_Cadastro_Confirmado");
        // Validar sucesso no cadastro
        cadastroPage.validarCadastroComSucesso();
    }



    @After
    public void tearDown() {
        PlaywrightDriver.fecharBrowser();
    }
}
