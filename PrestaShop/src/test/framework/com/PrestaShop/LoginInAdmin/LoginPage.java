package com.PrestaShop.LoginInAdmin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static com.PrestaShop.InitialConfiguration.InitialConfiguration.*;

public class LoginPage{

	@FindBy(how = How.XPATH, xpath = "//input[@id = 'email']")
	private WebElement loginField;

	@FindBy(how = How.XPATH, xpath = "//input[@id = 'passwd']")
	private WebElement passwordField;

	@FindBy(how = How.XPATH, xpath = "//button[@name = 'submitLogin']")
	private WebElement buttonLogin;

	public LoginPage(RemoteWebDriver driver) {
		
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
		log.debug("Вход в учетную запись.");
	}

	public LoginPage inputLogin(String login) {

		log.debug("Ввод логина: " + login + ".");
		loginField.clear();
		loginField.sendKeys(login);
		return this;

	}

	public LoginPage inputPassword(String password) {

		log.debug("Ввод пароля: " + password + ".");
		passwordField.clear();
		passwordField.sendKeys(password);
		return this;

	}

	public void clickOnLoginButton() {

		log.debug("Нажатие на кнопку \"Вход\".");
		buttonLogin.click();
	}
}
