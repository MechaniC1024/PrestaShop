package com.PrestaShop.Store;

import static com.PrestaShop.InitialConfiguration.InitialConfiguration.log;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class DeliveryAddress{
	
	private RemoteWebDriver driver;

	@FindBy(how = How.XPATH, xpath = "//input[@name='address1']")
	private WebElement fieldAddress;

	@FindBy(how = How.XPATH, xpath = "//input[@name='postcode']")
	private WebElement fieldPostcode;

	@FindBy(how = How.XPATH, xpath = "//input[@name='city']")
	private WebElement fieldCity;

	@FindBy(how = How.XPATH, xpath = "//div[@id='delivery-address']//button")
	private WebElement buttonContinueAddress;
	
	public DeliveryAddress(RemoteWebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
	}

	public DeliveryAddress inputAddress(String address) {

		log.debug("Ввод адреса доставки: " + address + ".");
		fieldAddress.clear();
		fieldAddress.sendKeys(address);
		return this;
	}

	public DeliveryAddress inputPostcode(String postcode) {
		
		log.debug("Ввод почтового индекса доставки: " + postcode + ".");
		fieldPostcode.clear();
		fieldPostcode.sendKeys(postcode);
		return this;
	}

	public DeliveryAddress inputCity(String city) {

		log.debug("Ввод города доставки: " + city + ".");
		fieldCity.clear();
		fieldCity.sendKeys(city);
		return this;
	}

	public DeliveryMethod clickOnButtonContinueAddress() {

		log.debug("Клик по кнопке \"Продолжить\".");
		buttonContinueAddress.click();
		return new DeliveryMethod(driver);
	}	
}
