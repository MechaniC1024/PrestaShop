package com.PrestaShop.Store;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import static com.PrestaShop.InitialConfiguration.InitialConfiguration.log;
import static com.PrestaShop.Wait.Wait.*;

public class Product{
	
	private RemoteWebDriver driver;
	
	private int numberProduct;
	
	@FindBy(how = How.XPATH, xpath = "//h1[@class = 'h1']")
	private WebElement nameProduct;
	
	@FindBy(how = How.XPATH, xpath = "//ol/li[last()]//span")
	private WebElement nameProductTitle;

	@FindBy(how = How.XPATH, xpath = "//span[@itemprop = 'price']")
	private WebElement priceProduct;
	
	@FindBy(how = How.XPATH, xpath = "//a[@href = '#product-details']")
	private WebElement productDetails;

	@FindBy(how = How.XPATH, xpath = "//button[@data-button-action = 'add-to-cart']")
	private WebElement addToCart;
	
	private By quantityProduct = By.xpath("//div[@class = 'product-quantities']/span");
	
	public Product(RemoteWebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
	}
	
	public Product(RemoteWebDriver driver, int numberProduct) {

		this(driver);
		this.numberProduct = numberProduct;
	}
	
	public int getNumberProduct() {
		
		return numberProduct;
	}

	private void productDetails() {

		log.debug("Клик на ссылку \"Подробнее о товаре\".");
		productDetails.click();
	}
	
	private WebElement getWebElementQuantityProduct() {

		return waitingForVisibilityOfElementLocated(driver, this.quantityProduct, 60);
	}
	
	private String getTextNameProduct() {
		
		return this.nameProduct.getText();
	}
	
	private String getTextPriceProduct() {
		
		return this.priceProduct.getText().substring(0, priceProduct.getText().length() - 2).replace(',', '.');
	}
	
	private String getTextQuantityProduct() {
		
		productDetails();
		WebElement quantityProductElement = getWebElementQuantityProduct();
		String quantity = quantityProductElement.getText().substring(0, quantityProductElement.getText().length() - 7);
		
		return quantity;
	}
	
	public Product getNameProduct(StringBuilder nameProduct) {
				
		nameProduct.append(getTextNameProduct());
		log.debug("Получение имени продукта: " + getTextNameProduct() + ".");
		
		return this;
	}
	
	public Product getPriceProduct(StringBuilder priceProduct) {
		
		priceProduct.append(getTextPriceProduct());
		log.debug("Получение имени продукта: " + getTextPriceProduct() + ".");
		
		return this;
	}
	
	public Product getQuantityProduct(StringBuilder quantityProduct) {
		
		quantityProduct.append(getTextQuantityProduct());
		log.debug("Получение имени продукта: " + getTextQuantityProduct() + ".");
		
		return this;
	}
	
	public ProductDesign addToCard() {

		log.debug("Клик на кнопку \"В корзину\".");
		addToCart.click();
		return new ProductDesign(driver);
	}
	
	public Product checkNameProduct(String nameProduct) {
		
		log.debug("Проверка имени продукта: " + nameProduct + ".");
		String name = getTextNameProduct();
		Assert.assertEquals(name, nameProduct.toUpperCase(), "Different product name.");
		
		return this;
	}
	
	public Product checkPriceProduct(String priceProduct) {
		
		log.debug("Проверка цены продукта: " + priceProduct + ".");
		String price = getTextPriceProduct();
		Assert.assertEquals(price, priceProduct, "Different product price.");
		
		return this;
	}	
	
	public Product checkQuantityProduct(String quantityProduct) {
		
		log.debug("Проверка количества продукта: " + quantityProduct + ".");
		String quantity = getTextQuantityProduct();
		Assert.assertEquals(quantity, quantityProduct, "Different quantity of product.");
		
		return this;
	}
	
	public Product checkTheRestOfTheProduct(String quantityProduct, StringBuilder quantityAllProduct) {
		
		Integer quantity = Integer.parseInt(getTextQuantityProduct());		
		Integer balance = Integer.parseInt(quantityAllProduct.toString()) - Integer.parseInt(quantityProduct);
		log.debug("Проверка баланса продукта. Текущий баланс товара: " + balance + ".");	
		
		Assert.assertEquals(quantity, balance, "The quantity of goods has not changed or has changed by more than the specified count.");
		
		return this;
	}
}
