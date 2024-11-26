package org.FremeWork_Playwright.utils;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

public class PlaywrightDriver {

    private static Browser browser;
    private static Page page;

    public static void iniciarBrowser() {
        // Inicia o Playwright
        Playwright playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)); // Para visibilidade
        page = browser.newPage();

        // Navegar para a página e aguardar até que a página esteja completamente carregada
        page.navigate("https://magento.softwaretestingboard.com/#");

        // Espera até que o estado da página seja "load" (completo)
        page.waitForLoadState(LoadState.NETWORKIDLE);  // Garantir que a página e seus recursos tenham sido carregados
    }

    public static Page getPage() {
        return page;
    }

    public static void fecharBrowser() {
        if (browser != null) {
            browser.close();
        }


    }
}
