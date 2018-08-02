package com.PrestaShop.test1;

import static com.PrestaShop.DataResources.ProcessingData.*;

import org.testng.annotations.Test;

import com.PrestaShop.Admin.UserPage;
import com.PrestaShop.DataResources.ProcessingData;
import com.PrestaShop.InitialConfiguration.InitialConfiguration;
import com.PrestaShop.LoginInAdmin.LoginPage;

import io.qameta.allure.Step;

public class Titles extends InitialConfiguration {
	
	private UserPage userPage;
	
	@Step("Логин и пароль.")
	@Test(dataProvider = "getLoginAndPassword", dataProviderClass = ProcessingData.class, description = "Вход в админ панель.")
	public void signIn(String login, String password) {
		
		setURL(getUrlAdminPanel());
		LoginPage loginPage = new LoginPage(getDriver());
		
		loginPage.
			inputLogin(login).
			inputPassword(password).
			clickOnLoginButton();
		
		userPage = new UserPage(getDriver());
	}
	
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Dashboard\" после обновления страницы.")
	public void checkTitleDashboard() {		
		userPage.clickOnDashboardGetTitleAndRefresh();
	}
	
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Заказы\" после обновления страницы.")
	public void checkTitleOrder() {
		userPage.clickOnOrdersGetTitleAndRefresh();
	}
	
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Каталог\" после обновления страницы.")
	public void checkTitleCatalog() {
		userPage.clickOnCatalogGetTitleAndRefresh();
	}
	
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Клиенты\" после обновления страницы.")
	public void checkTitleCustomer() {
		userPage.clickOnCustomerGetTitleAndRefresh();
	}
	
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Служба поддержки\" после обновления страницы.")
	public void checkTitleCustomerService() {
		userPage.clickOnCustomerServiceGetTitleAndRefresh();
	}
	
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Статистика\" после обновления страницы.")
	public void checkTitleStatistics() {
		userPage.clickOnStatisticsGetTitleAndRefresh();
	}
	
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Modules\" после обновления страницы.")
	public void checkTitleModules() {
		userPage.clickOnModulesGetTitleAndRefresh();
	}
	
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Design\" после обновления страницы.")
	public void checkTitleDesign() {
		userPage.clickOnDesignGetTitleAndRefresh();
	}
	
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Delivery\" после обновления страницы.")
	public void checkTitleDelivery() {
		userPage.clickOnDeliveryGetTitleAndRefresh();
	}
	
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Способ оплаты\" после обновления страницы.")
	public void checkTitlePayment() {
		userPage.clickOnPaymentGetTitleAndRefresh();
	}
	
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"International\" после обновления страницы.")
	public void checkTitleInternational() {
		userPage.clickOnInternationalGetTitleAndRefresh();
	}
	
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Shop Parameters\" после обновления страницы.")
	public void checkTitleShopParameters() {
		userPage.clickOnShopParametersGetTitleAndRefresh();
	}
	
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Конфигурация\" после обновления страницы.")
	public void checkTitleAdvancedParameters() {
		userPage.clickOnAdvancedParametersGetTitleAndRefresh();		
	}
	
	@Test(dependsOnMethods = "signIn", dependsOnGroups = "title", description = "Выход с аккаунта.")
	public void logOut() {		
		
		userPage.
			clickOnImageProfile().
			clickOnLogOut();		
	}
}
