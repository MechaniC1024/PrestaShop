package com.PrestaShop.Store;

import static com.PrestaShop.Report.Report.*;
import static com.PrestaShop.InitialConfiguration.InitialConfiguration.*;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {

	private RemoteWebDriver driver;

	@FindBy(how = How.XPATH, xpath = "//div[@id = '_mobile_logo']/a")
	private WebElement mobileLogo;

	@FindBy(how = How.XPATH, xpath = "//section[@id = 'content']/section/a")
	private WebElement allProduct;

	public HomePage(RemoteWebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
	}

	public HomePage checkingTheVersionOfTheSite() {

		try {

			if (mobileLogo.isDisplayed() == true && driver.getCapabilities().getPlatform() == platform) {
				addAttachmentToReport("Version: ", "Mobile version.");
				log.debug("Мобильная версия сайта.");
			} else {
				addAttachmentToReport("Version: ", "Error version.");
				log.debug("Ошибка версии сайта.");
			}

		} catch (NoSuchElementException e) {

			if (driver.getCapabilities().getPlatform() != platform) {
				addAttachmentToReport("Version: ", "Desktop version.");
				log.debug("Десктопная версия сайта.");
			} else {
				addAttachmentToReport("Version: ", "Mobile version.");
				log.debug("Мобильная версия сайта.");
			}
		}

		return this;
	}

	public AllProduct clickOnAllProduct() {

		log.debug("Клик на ссылку \"Все товары\".");
		allProduct.click();
		return new AllProduct(driver);
	}
}
