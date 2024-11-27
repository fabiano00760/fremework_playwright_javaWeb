package org.FremeWork_Playwright.pageobjects;

import com.microsoft.playwright.Page;
import org.junit.Assert;

public class CadastroPage {

    private Page page;

    // XPaths dos elementos na página
    private String campoCreateAnAccount = "(//a[contains(.,'Create an Account')])[1]";
    private String campoFirstName = "//input[@id='firstname']";
    private String campoLastName = "//input[@name='lastname']";
    private String campoEmail = "//input[contains(@id,'email_address')]";
    private String campoPassword = "//input[@id='password']";
    private String campoConfirmPassword = "//input[@id='password-confirmation']";
    private String btnCreateAnAccount = "//button[contains(.,'Create an Account')]";
    private String loginSucesso = "(//div[contains(.,'Thank you for registering with Main Website Store.')])[6]";


    public CadastroPage(Page page) {
        this.page = page;
    }
    public void campoCreateAnAccount(){
        page.locator(campoCreateAnAccount).click();
        page.waitForTimeout(2000);
    }

    private void preencherCampo(String campoLocator, String valor) {
        page.locator(campoLocator).fill(valor);
        page.waitForTimeout(2000);
    }


    public void btnCreateAnAccount() {
        page.locator(btnCreateAnAccount).click();
        page.waitForTimeout(2000);
    }


    public void preencherCadastro(String nome, String sobrenome, String emailEmail, String senha) {
        preencherCampo(campoFirstName, nome);
        preencherCampo(campoLastName, sobrenome);
        preencherCampo(campoEmail, emailEmail);
        preencherCampo(campoPassword, senha);
        preencherCampo(campoConfirmPassword, senha);
        page.waitForTimeout(2000);
    }



    public void validarCadastroComSucesso() {
        Assert.assertTrue("Thank you for registering with Main Website Store.", page.locator(loginSucesso).isVisible());
    }


}
