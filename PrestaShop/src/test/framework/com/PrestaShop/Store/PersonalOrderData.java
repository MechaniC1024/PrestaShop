package com.PrestaShop.Store;

import static com.PrestaShop.InitialConfiguration.InitialConfiguration.log;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class PersonalOrderData{
	
	private RemoteWebDriver driver;

	@FindBy(how = How.XPATH, xpath = "//input[@name='firstname']")
	private WebElement fieldFirstName;

	@FindBy(how = How.XPATH, xpath = "//input[@name='lastname']")
	private WebElement fieldLastName;

	@FindBy(how = How.XPATH, xpath = "//div[@id='checkout-guest-form']//input[@name='email']")
	private WebElement fieldEmail;

	@FindBy(how = How.XPATH, xpath = "//form[@id='customer-form']/footer/button")
	private WebElement buttonContinueInform;

	public PersonalOrderData(RemoteWebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
	}

	public PersonalOrderData inputFirstName(String firstName) {

		log.debug("Ввод имени получателя: " + firstName + ".");
		fieldFirstName.clear();
		fieldFirstName.sendKeys(firstName);
		return this;
	}

	public PersonalOrderData inputLastName(String lastName) {

		log.debug("Ввод фамилии получателя: " + lastName + ".");
		fieldLastName.clear();
		fieldLastName.sendKeys(lastName);
		return this;
	}

	public PersonalOrderData inputEmail(String email) {

		log.debug("Ввод e-mail: " + email + ".");
		fieldEmail.clear();
		fieldEmail.sendKeys(email);
		return this;
	}

	public DeliveryAddress clickOnButtonContinueInform() {

		log.debug("Клик по кнопке \"Продолжить\".");
		buttonContinueInform.click();
		return new DeliveryAddress(driver);
	}

}
