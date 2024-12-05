package org.FremeWork_Playwright.tests;

import com.microsoft.playwright.Page;
import org.FremeWork_Playwright.pageobjects.CadastroPage;
import org.FremeWork_Playwright.pageobjects.PageFactory;
import org.FremeWork_Playwright.utils.Evidencias;
import org.FremeWork_Playwright.Config.PlaywrightDriver;
import org.FremeWork_Playwright.utils.FakeEmailGenerator;
import org.FremeWork_Playwright.DatabaseConnection.*; // Adicionado para importar a classe DatabaseOperations
import com.github.javafaker.Faker; // Adicionado para importar a classe Faker
import org.junit.*;

public class CadastroTest {

    private static Page page;
    private static PageFactory pageFactory;
    private Evidencias evidencias;
    private DatabaseConnection databaseOperations; // Adicionado para criar uma instância de DatabaseOperations
    private Faker faker; // Adicionado para criar uma instância de Faker

    @Before
    public void setUp() {
        PlaywrightDriver.iniciarBrowser();
        page = PlaywrightDriver.getPage();
        pageFactory = new PageFactory(page);
        evidencias = new Evidencias("CadastroTest");
     //   DatabaseConnection databaseConnection = new DatabaseConnection(); // Inicialização da instância de DatabaseOperations
        faker = new Faker(); // Inicialização da instância de Faker
    }

    @Test
    public void cadastroUser() {
        CadastroPage cadastroPage = pageFactory.getCadastroPage();

        // Gerar nome, sobrenome, email e senha usando o Faker e FakeEmailGenerator
        String firstNameGerado = faker.name().firstName();
        String lastNameGerado = faker.name().lastName();
        String emailGerado =FakeEmailGenerator.gerarEmailFake();
        String senhaGerada = FakeEmailGenerator.gerarSenhaFake();

        // Acessar a tela de cadastro
        cadastroPage.acessarTelaCadastro();
        evidencias.capturarEvidencia(page, "01_Tela_Cadastro");
        // Preencher os campos de cadastro com os dados gerados
        cadastroPage.preencherCadastro(firstNameGerado, lastNameGerado, emailGerado, senhaGerada);
        evidencias.capturarEvidencia(page, "02_Campos_Preenchidos");
        // Confirmar cadastro
        cadastroPage.confirmarCadastro();
        evidencias.capturarEvidencia(page, "03_Cadastro_Confirmado");
        // Validar sucesso no cadastro
        cadastroPage.validarCadastroComSucesso();

        // Salvar os dados no banco de dados
        UsuarioDAO.cadastroUser(firstNameGerado, lastNameGerado, emailGerado, senhaGerada);
        System.out.println("Usuário cadastrado e salvo no banco de dados.");
    }

    @After
    public void tearDown() {
        PlaywrightDriver.fecharBrowser();
    }
}
