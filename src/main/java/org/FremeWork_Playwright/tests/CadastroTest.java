package org.FremeWork_Playwright.tests;

import com.microsoft.playwright.Page;
import org.FremeWork_Playwright.pageobjects.CadastroPage;
import org.FremeWork_Playwright.utils.Evidencias;
import org.FremeWork_Playwright.utils.PlaywrightDriver;
import org.FremeWork_Playwright.utils.FakeEmailGenerator;
import org.junit.*;

import java.io.FileNotFoundException;

public class CadastroTest {

    private static Page page;

    @Before
    public void setUp() {
        PlaywrightDriver.iniciarBrowser(); // Inicializa o browser
        page = PlaywrightDriver.getPage(); // Obtém a página inicializada
    }

    @Test
    public void CadastroUser() throws FileNotFoundException {
        Evidencias evidencias = new Evidencias("Teste_Login");

        // Gerar email e senha
        String emailFake = FakeEmailGenerator.gerarEmailFake();
        String senhaFake = FakeEmailGenerator.gerarSenhaFake();

        // Salvar o email e senha gerados em um arquivo JSON
        FakeEmailGenerator.salvarEmailESenhaEmArquivo(emailFake, senhaFake);

        // A partir daqui, use o emailFake no cadastro
        CadastroPage cadastroPage = new CadastroPage(page);

        // Preencher o formulário de cadastro com os dados gerados
        cadastroPage.campoCreateAnAccount();
        evidencias.capturarEvidencia(page, "01_Acesso_Pagina_Inicial");
        cadastroPage.preencherCadastro("Fabiano", "Silva", emailFake, senhaFake);
        evidencias.capturarEvidencia(page, "02_Preenchimento_Usuario");
        cadastroPage.btnCreateAnAccount();
        cadastroPage.validarCadastroComSucesso();
        evidencias.capturarEvidencia(page, "03_validar_Cadastro_Com_Sucesso");

    }

    @After
    public void tearDown() {
        PlaywrightDriver.fecharBrowser(); // Fecha o navegador
    }
}
