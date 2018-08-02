package com.PrestaShop.test3;

import static com.PrestaShop.DataResources.ProcessingData.*;
import static com.PrestaShop.Report.Report.*;

import org.testng.annotations.Test;

import com.PrestaShop.Admin.*;
import com.PrestaShop.DataResources.*;
import com.PrestaShop.InitialConfiguration.InitialConfiguration;
import com.PrestaShop.LoginInAdmin.LoginPage;
import com.PrestaShop.Store.*;

import io.qameta.allure.Step;

public class CreatingANewProduct extends InitialConfiguration {

	private ProductData productData = new ProductData();

	private UserPage userPage;
	
	private Products product;
	
	private CreateProduct createProduct;
	
	private String nameProduct;
	
	private String priceProduct;
	
	private String quantityProduct;

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
		
		productData.generateNamePriceProductQuantity();		
		
		nameProduct = productData.getNameProduct();		
		priceProduct = productData.getPriceProduct();		
		quantityProduct = productData.getQuantityProduct();
	}

	@Step("Переходим в раздел товаров.")
	@Test(dependsOnMethods = "signIn", description = "Переход в раздел товаров.")
	public void productSection() {

		product =
				userPage.
					selectCatalog().
					goToProduct();
	}
	
	@Step("Создаем новый товар по сгенерированным данным.")
	@Test(dependsOnMethods = "productSection", description = "Создание нового товара.")
	public void newProduct() {
		
		addAttachmentToReport("Название нового продукта.", nameProduct);
		addAttachmentToReport("Цена нового продукта.", priceProduct);
		addAttachmentToReport("Количество нового продукта.", quantityProduct);
		
		createProduct =
				product.
					clickOnNewProductButton().
					inputNameNewProduct(nameProduct).
					inputQuantityNewProduct(quantityProduct).
					inputPriceNewProduct(priceProduct);		
	}
	
	@Step("Сохраняем и активируем товар.")
	@Test(dependsOnMethods = "productSection", description = "Сохранение и активация нового товара.")
	public void productActivation() {
		
		createProduct.
				clickOnActivateANewProduct().
				closeSuccessfulUpdate().
				clickOnButtonSaveNewProduct().
				closeSuccessfulUpdate();		
	}
	
	@Test(dependsOnMethods = "productActivation", description = "Выход с аккаунта.")
	public void logOut() {		
		
		userPage.
			clickOnImageProfile().
			clickOnLogOut();		
	}
	
	@Step("Проверяем созданный товар на странице маганзина.")
	@Test(dependsOnMethods = "logOut", description = "Проверка созданного товара.")
	public void checkNewProduct() {

		setURL(getUrlSite());
		HomePage homePage = new HomePage(getDriver());
		
		homePage.
			clickOnAllProduct().
			clickOnProduct(nameProduct).
			checkNameProduct(nameProduct).
			checkPriceProduct(priceProduct).
			checkQuantityProduct(quantityProduct);
		
		addAttachmentToReport("Название нового продукта на странице магазина.", nameProduct);
		addAttachmentToReport("Цена нового продукта на странице магазина.", priceProduct);
		addAttachmentToReport("Количество нового продукта на странице магазина.", quantityProduct);
	}

}
