package com.PrestaShop.Store;

import static com.PrestaShop.InitialConfiguration.InitialConfiguration.log;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class ConfirmationOfAnOrder{
	
	private RemoteWebDriver driver;
	
	@FindBy(how = How.XPATH, xpath = "//h3[@class='h1 card-title']")
	private WebElement titleOrderConfirmed;

	@FindBy(how = How.XPATH, xpath = "//div[@class='col-xs-5 text-sm-right text-xs-left']")
	private WebElement price;

	@FindBy(how = How.XPATH, xpath = "//div[@class='col-xs-2']")
	private WebElement quantity;

	@FindBy(how = How.XPATH, xpath = "//div[@class='col-sm-4 col-xs-9 details']/span")
	private WebElement name;

	@FindBy(how = How.XPATH, xpath = "//section/a")
	private WebElement allProducts;

	public ConfirmationOfAnOrder(RemoteWebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
	}
	
	public ConfirmationOfAnOrder checkOrderNameProduct(StringBuilder nameProduct) {
		
		log.debug("Проверка имени товара в заказе: " + nameProduct.toString() + ".");
		Assert.assertEquals(name.getText().substring(0, nameProduct.length()).toUpperCase(), nameProduct.toString().toUpperCase(),
				"Different name of the product.");
		
		return this;
	}
	
	public ConfirmationOfAnOrder checkOrderPriceOrder(StringBuilder priceProduct) {
		
		log.debug("Проверка цены товара в заказе: " + priceProduct.toString() + ".");
		Assert.assertEquals(price.getText().substring(0, price.getText().length() - 2).replace(',', '.'), priceProduct.toString(),
				"Different price of goods.");
		
		return this;
	}
	
	public ConfirmationOfAnOrder checkOrderQuantityProduct(String quantityProduct) {
		
		log.debug("Проверка количества товара в заказе: " + quantityProduct + ".");
		Assert.assertEquals(quantity.getText(), quantityProduct, "Different quantity of goods.");
		
		return this;
	}
	
	public ConfirmationOfAnOrder checkOrderTitle() {
		
		log.debug("Проверка подтверждения заказа.");
		Assert.assertEquals(titleOrderConfirmed.getText().substring(1, titleOrderConfirmed.getText().length()),
				"ВАШ ЗАКАЗ ПОДТВЕРЖДЁН", "Different order message.");
		
		return this;
	}
	
	public AllProduct selectAllProduct() {

		log.debug("Клик на ссылку \"Все товары\".");
		allProducts.click();
		return new AllProduct(driver);
	}

}