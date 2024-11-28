package org.FremeWork_Playwright.Config;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import org.FremeWork_Playwright.Config.Config;

public class PlaywrightDriver {

    private static Browser browser;
    private static Page page;
    private static Playwright playwright;

    public static void iniciarBrowser() {
        try {
            playwright = Playwright.create();
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            page = browser.newPage();
            page.navigate(Config.getBaseUrl());
            page.waitForLoadState(LoadState.NETWORKIDLE);
            System.out.println("Browser iniciado com sucesso na URL: " + Config.getBaseUrl());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao iniciar o navegador: " + e.getMessage(), e);
        }
    }

    public static Page getPage() {
        if (page == null) {
            throw new RuntimeException("A página não foi inicializada corretamente.");
        }
        return page;
    }

    public static void fecharBrowser() {
        try {
            if (browser != null) browser.close();
            if (playwright != null) playwright.close();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fechar o navegador: " + e.getMessage(), e);
        }
    }
}
