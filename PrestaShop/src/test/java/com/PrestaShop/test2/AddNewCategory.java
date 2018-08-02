package com.PrestaShop.test2;

import static com.PrestaShop.DataResources.ProcessingData.*;
import static com.PrestaShop.Report.Report.*;

import org.testng.annotations.Test;

import com.PrestaShop.Admin.Category;
import com.PrestaShop.Admin.UserPage;
import com.PrestaShop.DataResources.ProcessingData;
import com.PrestaShop.InitialConfiguration.InitialConfiguration;
import com.PrestaShop.LoginInAdmin.LoginPage;

import io.qameta.allure.Step;

public class AddNewCategory extends InitialConfiguration{
	
	private String newCategory = "Jacket";
	
	private UserPage userPage;
	
	private Category category;
	
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
		
	@Step("Переходим в раздел категории товаров.")
	@Test(dependsOnMethods = "signIn", description = "Переход в раздел категории.")
	public void categorySection() {	
		
		category = 
				userPage.
					selectCatalog().
					goToCategories();
	}
	
	@Step("Создаем новую категорию.")
	@Test(dependsOnMethods = "categorySection", description = "Добавление новой категории.")
	public void addNewCategory() {	
		
		addAttachmentToReport("Название новой категории.", newCategory);
		
		category.
			addNewCategory().
			setNameAndSaveCategoty(newCategory).
			clickOnCloseButtonAlert();
	}
	
	@Step("Проверяем, что указанная категория создалась.")
	@Test(dependsOnMethods = "addNewCategory", description = "Проверка созданной категории.")
	public void checkingNewCategory() {	
		
		addAttachmentToReport("Проверка новой категории.", newCategory);
		
		category.
			setFilterByNameCategory(newCategory).
			filterSearch().
			containsCategory(newCategory);
	}
	
	@Test(dependsOnMethods = "checkingNewCategory", description = "Выход с аккаунта.")
	public void logOut() {		
		
		userPage.
			clickOnImageProfile().
			clickOnLogOut();		
	}
}