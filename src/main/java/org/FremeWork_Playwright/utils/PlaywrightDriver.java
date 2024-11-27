package org.FremeWork_Playwright.utils;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

public class PlaywrightDriver {

    private static Browser browser;
    private static Page page;
    private static Playwright playwright;

    // Este metodo será chamado para iniciar o navegador
    public static void iniciarBrowser() {
        try {
            // Inicializa o Playwright
            playwright = Playwright.create();
            // Lançamento do browser de forma controlada
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            page = browser.newPage();
            // Navegar para a página e aguardar até que a página esteja completamente carregada
            page.navigate("https://magento.softwaretestingboard.com/#");
            page.waitForLoadState(LoadState.NETWORKIDLE);  // Garantir que a página e seus recursos tenham sido carregados
        } catch (Exception e) {
            throw new RuntimeException("Erro ao iniciar o navegador: " + e.getMessage(), e);
        }
    }

    // Retorna a página para ser utilizada nos testes
    public static Page getPage() {
        if (page == null) {
            throw new RuntimeException("A página não foi inicializada corretamente.");
        }
        return page;
    }

    // Este método será chamado para fechar o navegador após cada teste
    public static void fecharBrowser() {
        try {
            if (browser != null) {
                browser.close();
            }
            if (playwright != null) {
                playwright.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fechar o navegador: " + e.getMessage(), e);
        }
    }
}
