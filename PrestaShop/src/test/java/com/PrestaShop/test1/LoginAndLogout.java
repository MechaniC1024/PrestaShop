package com.PrestaShop.test1;

import static com.PrestaShop.DataResources.ProcessingData.*;

import org.testng.annotations.Test;

import com.PrestaShop.Admin.UserPage;
import com.PrestaShop.DataResources.ProcessingData;
import com.PrestaShop.InitialConfiguration.InitialConfiguration;
import com.PrestaShop.LoginInAdmin.LoginPage;

import io.qameta.allure.Step;

public class LoginAndLogout extends InitialConfiguration{
	
	@Step("Логин и пароль.")
	@Test(dataProvider = "getLoginAndPassword", dataProviderClass = ProcessingData.class, description = "Вход в админ панель сайта и выход из нее.")
	public void loginAndLogout(String login, String password) {
		
		setURL(getUrlAdminPanel());
		LoginPage loginPage = new LoginPage(getDriver());
		
		loginPage.
				inputLogin(login).
				inputPassword(password).
				clickOnLoginButton();
		
		UserPage userPage = new UserPage(getDriver());
		
		userPage.
				clickOnImageProfile().
				clickOnLogOut();
		
	}	
}
