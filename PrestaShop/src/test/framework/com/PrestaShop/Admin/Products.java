package com.PrestaShop.Admin;

import static com.PrestaShop.InitialConfiguration.InitialConfiguration.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Products{
	
	private RemoteWebDriver driver;
	
	@FindBy(how = How.XPATH, xpath = "//a[@id = 'page-header-desc-configuration-add']")
	private WebElement buttonNewProduct;

	public Products(RemoteWebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
		log.debug("Создание нового товара.");
	}
	
	public CreateProduct clickOnNewProductButton() {

		log.debug("Нажатие на кнопку \"Новый товар\".");		
		buttonNewProduct.click();
		return new CreateProduct(driver);
	}

}
