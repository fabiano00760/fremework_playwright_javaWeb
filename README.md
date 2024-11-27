# Framework de Automação com Playwright em Java

Este é um framework de automação para testes de UI usando o Playwright, uma ferramenta poderosa para automação de navegadores, em conjunto com a linguagem de programação Java. O projeto utiliza o **Playwright** para simular interações com navegadores e o **JUnit** ou **TestNG** para a execução de testes.

## Requisitos

Antes de começar, você precisa ter as seguintes dependências instaladas:

- **Java 11 ou superior**: Playwright e o código Java exigem pelo menos o JDK 11.
- **Maven**: Para gerenciar dependências e construir o projeto.
- **Playwright**: Para automação do navegador.
- **IDE**: Recomendamos o uso do IntelliJ IDEA ou Eclipse.

## Como Configurar o Projeto

1. **Clone o repositório**:

   Se você ainda não tem o projeto, clone-o para sua máquina local usando o comando:

   ```bash
   git clone https://github.com/seu_usuario/seu_repositorio.git
   cd seu_repositorio
Configuração do Maven:

O projeto usa o Maven para gerenciar as dependências. Se você ainda não tiver o Maven configurado em seu ambiente, siga as instruções aqui para instalar.

O arquivo pom.xml contém todas as dependências necessárias para o Playwright e para execução dos testes.

Instalar as dependências:

Execute o seguinte comando para instalar todas as dependências do projeto:

bash
Copiar código
mvn install
Instalar o Playwright:

Embora o Playwright seja configurado como dependência no pom.xml, você precisa executar um comando para baixar os navegadores que o Playwright utiliza.

Execute o seguinte comando:

bash
Copiar código
mvn playwright:install
Estrutura do Projeto
Aqui está uma visão geral da estrutura do projeto:

bash
Copiar código
framework-playwright/
├── src/
│   ├── main/java/
│   │   ├── org/
│   │   │   └── FremeWork_Playwright/
│   │   │       ├── pageobjects/      # Contém os objetos de página (page objects) como LoginPage
│   │   │       ├── utils/            # Utilitários, como a classe de geração de emails
│   │   │       └── tests/            # Casos de teste de login e outros testes
│   └── resources/                     # Arquivos de configuração e recursos
├── pom.xml                            # Arquivo de configuração do Maven
├── README.md                          # Este arquivo
└── target/                            # Diretório gerado após a construção do projeto
Como Usar o Framework
Executando os Testes
Via IDE (IntelliJ IDEA / Eclipse):

Abra o projeto na sua IDE.
Localize a classe de teste que deseja executar (por exemplo, LoginTest).
Clique com o botão direito na classe ou no método de teste e selecione a opção "Run".
Via Linha de Comando (Maven):

Para rodar todos os testes com o Maven, execute o seguinte comando:

bash
Copiar código
mvn test
Se você quiser rodar um teste específico, por exemplo, o teste LoginTest, use o seguinte comando:

bash
Copiar código
mvn test -Dtest=LoginTest
O Maven irá compilar o código, baixar as dependências necessárias e executar os testes automaticamente.

Estrutura de Teste
Os testes são organizados de forma a seguir o padrão Page Object Model (POM). Isso significa que, para cada página do seu site, você criará uma classe que interage com os elementos dessa página. Por exemplo, a classe LoginPage é responsável por todas as ações de login, como preencher o email e senha e clicar no botão de login.

Exemplo de Caso de Teste:
java
Copiar código
@Test
@DisplayName("Teste de login bem-sucedido")
public void testLoginBemSucedido() {
// Navega para a página de login
page.navigate("https://www.exemplo.com/login");

    // Cria o objeto de login
    LoginPage loginPage = new LoginPage(page);

    // Preenche os dados de login e realiza o login
    loginPage.preencherDadosLogin();

    // Verifica se o login foi bem-sucedido
    Assertions.assertTrue(loginPage.isLoginBemSucedido(), "O login deveria ser bem-sucedido.");
}
Este exemplo de teste navega até a página de login, preenche os dados e valida se o login foi bem-sucedido.

Funções Utilizadas
page.locator(): Método usado para localizar elementos na página.
page.fill(): Preenche um campo de entrada com um valor.
page.click(): Clica em um elemento.
page.waitForTimeout(): Espera por um determinado período de tempo (em milissegundos).
Assertions.assertTrue() e Assertions.assertFalse(): Validações para garantir que os testes estão funcionando corretamente.
Gerenciamento de Dados de Login
Os dados de login (email e senha) podem ser gerados e salvos automaticamente durante a execução do teste, ou você pode criar um arquivo json com informações já salvas e utilizá-las durante o login.

Exemplo de Arquivo JSON de Dados de Login:
json
Copiar código
[
{
"email": "username-1234@example.com",
"senha": "Senha@123"
}
]
Esses dados podem ser usados nos testes para realizar o login de forma automatizada.

Como Contribuir
Se você deseja contribuir para este projeto, sinta-se à vontade para fazer um fork do repositório e submeter pull requests. Algumas sugestões de contribuições incluem:

Melhorias na estrutura dos testes.
Criação de novos casos de teste.
Atualização de dependências.
Licença
Este projeto é licenciado sob a Licença MIT - veja o arquivo LICENSE para mais detalhes.

Considerações Finais
Esse framework foi criado para facilitar a automação de testes de UI utilizando o Playwright em Java. A combinação do Playwright com JUnit ou TestNG proporciona uma experiência poderosa e eficiente para testar a interação do usuário com a interface de uma aplicação web.

Se você tiver alguma dúvida ou sugestão, fique à vontade para abrir uma issue no repositório.