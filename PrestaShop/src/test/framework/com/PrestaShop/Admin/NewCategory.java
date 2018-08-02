package com.PrestaShop.Admin;

import static com.PrestaShop.InitialConfiguration.InitialConfiguration.log;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class NewCategory{
	
	private RemoteWebDriver driver;
	
	@FindBy(how = How.XPATH, xpath = "//input[@id = 'name_1']")
	private WebElement nameFieldCategories;

	@FindBy(how = How.XPATH, xpath = "//button[@id = 'category_form_submit_btn']")
	private WebElement buttonSave;

	public NewCategory(RemoteWebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
		log.debug("Создаиние новой категории.");
	}

	public Category setNameAndSaveCategoty(String nameCategory) {
		
		log.debug("Ввод имени новой категории: " + nameCategory + ".");
		nameFieldCategories.sendKeys(nameCategory);
		log.debug("Нажатие на кнопку \"Сохранить\".");
		buttonSave.click();		
		return new Category(driver);
	}
}
