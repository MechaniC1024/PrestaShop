package com.PrestaShop.Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static com.PrestaShop.InitialConfiguration.InitialConfiguration.log;
import static com.PrestaShop.Wait.Wait.*;

public class CreateProduct{
	
	private RemoteWebDriver driver;
	
	@FindBy(how = How.XPATH, xpath = "//input[@id = 'form_step1_name_1']")
	private WebElement fieldNameNewProduct;

	@FindBy(how = How.XPATH, xpath = "//input[@id = 'form_step1_qty_0_shortcut']")
	private WebElement fieldQuantityNewProduct;

	@FindBy(how = How.XPATH, xpath = "//input[@id = 'form_step1_price_shortcut']")
	private WebElement fieldPriceNewProduct;

	@FindBy(how = How.XPATH, xpath = "//div[@class = 'col-lg-5']/div")
	private WebElement activateNewProduct;

	@FindBy(how = How.XPATH, xpath = "//input[@id = 'submit']")
	private WebElement buttonSaveNewProduct;

	private By buttonCloseSuccessfulUpdate = By.xpath("//div[@id = 'growls']//div[@class = 'growl-close']");
	
	public CreateProduct(RemoteWebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
		log.debug("Создание нового товара.");
	}

	public CreateProduct inputNameNewProduct(String nameNewProduct) {
		
		log.debug("Ввод имени нового товара: " + nameNewProduct + ".");
		fieldNameNewProduct.clear();
		fieldNameNewProduct.sendKeys(nameNewProduct);
		return this;
	}

	public CreateProduct inputQuantityNewProduct(String quantityNewProduct) {

		log.debug("Ввод количества нового товара: " + quantityNewProduct + ".");
		fieldQuantityNewProduct.clear();
		fieldQuantityNewProduct.sendKeys(quantityNewProduct);
		return this;
	}

	public CreateProduct inputPriceNewProduct(String priceNewProduct) {

		log.debug("Ввод цены нового товара: " + priceNewProduct + ".");
		fieldPriceNewProduct.clear();
		fieldPriceNewProduct.sendKeys(priceNewProduct);
		return this;
	}

	public CreateProduct clickOnActivateANewProduct() {

		log.debug("Нажатие на кнопку активации товара.");
		activateNewProduct.click();
		return this;
	}

	public CreateProduct clickOnButtonSaveNewProduct() {

		log.debug("Нажатие на кнопку сохранения товара.");
		buttonSaveNewProduct.click();
		return this;
	}

	public CreateProduct closeSuccessfulUpdate() {

		log.debug("Закрытие уведомление, что товар сохранен.");
		waitingForVisibilityOfElementLocated(driver, buttonCloseSuccessfulUpdate, 60).click();
		waitingForInvisibilityOfElementLocated(driver, buttonCloseSuccessfulUpdate, 60);
		return this;
	}
}
