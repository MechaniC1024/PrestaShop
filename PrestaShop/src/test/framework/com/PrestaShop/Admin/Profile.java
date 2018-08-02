package com.PrestaShop.Admin;

import static com.PrestaShop.InitialConfiguration.InitialConfiguration.log;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Profile{
	
	@FindBy(how = How.XPATH, xpath = "//a[@id = 'header_logout']")
	private WebElement logOut;

	public Profile(RemoteWebDriver driver) {
		
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
	}

	public void clickOnLogOut() {
		
		log.debug("Нажатие на кнопку \"Выйти\" в профиле.");
		logOut.click();
	}

}
