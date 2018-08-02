package com.PrestaShop.Store;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static com.PrestaShop.InitialConfiguration.InitialConfiguration.log;
import static com.PrestaShop.Wait.Wait.*;

public class PaymentOfAnOrder{
	
	private RemoteWebDriver driver;
	
	@FindBy(how = How.XPATH, xpath = "//input[@id='payment-option-1']")
	private WebElement paymentByCheck;

	@FindBy(how = How.XPATH, xpath = "//input[@id='payment-option-2']")
	private WebElement paymentByCard;

	@FindBy(how = How.XPATH, xpath = "//input[@id='conditions_to_approve[terms-and-conditions]']")
	private WebElement termsOfAgreement;

	private By buttonOrder = By.xpath("//div[@id='payment-confirmation']//button");
	
	public PaymentOfAnOrder(RemoteWebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
	}
	
	public PaymentOfAnOrder paymentMethod(int paymentMethod_One_Or_Two) {

		log.debug("Выбор способа оплаты.");
		switch (paymentMethod_One_Or_Two) {
		case 1:
		default: {
			paymentByCheck.click();
			break;
		}
		case 2: {
			paymentByCard.click();
			break;
		}
	  }
		
		return this;
	}

	public PaymentOfAnOrder termsOfAgreement() {

		log.debug("Подтверждение соглашения.");
		termsOfAgreement.click();
		return this;
	}

	public ConfirmationOfAnOrder clickOnButtonOrder() {

		log.debug("Клик по кнопке \"Заказ с обязательством оплаты\".");
		waitingForElementToBeClickable(driver, buttonOrder, 60).click();
		return new ConfirmationOfAnOrder(driver);
	}
}
