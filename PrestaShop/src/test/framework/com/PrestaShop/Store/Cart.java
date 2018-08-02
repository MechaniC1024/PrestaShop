package com.PrestaShop.Store;

import static com.PrestaShop.InitialConfiguration.InitialConfiguration.log;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class Cart{
	
	private RemoteWebDriver driver;

	@FindBy(how = How.XPATH, xpath = "//div[@class='product-line-info']/a")
	private WebElement nameProduct;

	@FindBy(how = How.XPATH, xpath = "//div[@class='product-line-grid-body col-md-4 col-xs-8']/div[2]/span")
	private WebElement priceProduct;

	@FindBy(how = How.XPATH, xpath = "//span[@class='label js-subtotal']")
	private WebElement quantityProduct;

	@FindBy(how = How.XPATH, xpath = "//div[@class = 'text-xs-center']/a")
	private WebElement ordering;

	public Cart(RemoteWebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);		
	}
	
	public Cart checkNameProductInCart(StringBuilder name) {
		
		log.debug("Проверка имени товара в корзине: " + name + ".");
		Assert.assertEquals(nameProduct.getText().toUpperCase(), name.toString().toUpperCase(), "Different name of the product.");
		return this;
	}
	
	public Cart checkPriceProductInCart(StringBuilder price) {
		
		log.debug("Проверка цены товара в корзине: " + price + ".");
		Assert.assertEquals(priceProduct.getText().substring(0, priceProduct.getText().length() - 2).replace(',', '.'), price.toString(),
				"Different price of goods.");
		return this;
	}
	
	public Cart checkQuantityProductInCart(String quantity) {
		
		log.debug("Проверка имени товара в корзине: " + quantity + ".");
		Assert.assertEquals(quantityProduct.getText().substring(0, quantityProduct.getText().length() - 4), quantity,
				"Different quantity of goods.");
		return this;
	}

	public PersonalOrderData clickOnPlaceOrder() {

		log.debug("Клик на кнопку \"Оформление заказа\".");
		ordering.click();
		return new PersonalOrderData(driver);
	}
}
