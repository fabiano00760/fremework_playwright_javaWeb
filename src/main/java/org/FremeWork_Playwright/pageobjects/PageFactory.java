package org.FremeWork_Playwright.pageobjects;

import com.microsoft.playwright.Page;


public class PageFactory {

    private Page page;

    public PageFactory(Page page) {
        this.page = page;
    }

    public CadastroPage getCadastroPage() {
        return new CadastroPage(page);
    }

    public LoginPage getLoginPage() {
        return new LoginPage(page);
    }
}
