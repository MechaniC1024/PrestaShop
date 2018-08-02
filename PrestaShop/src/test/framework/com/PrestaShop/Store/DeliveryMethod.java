package com.PrestaShop.Store;

import static com.PrestaShop.InitialConfiguration.InitialConfiguration.log;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class DeliveryMethod{
	
	private RemoteWebDriver driver;
	
	@FindBy(how = How.XPATH, xpath = "//form[@id='js-delivery']/button")
	private WebElement buttonContinueDelivery;

	public DeliveryMethod(RemoteWebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);		
	}
	
	public PaymentOfAnOrder clickOnButtonContinueDelivery() {

		log.debug("Клик по кнопке \"Продолжить\".");
		buttonContinueDelivery.click();
		return new PaymentOfAnOrder(driver);
	}
}
