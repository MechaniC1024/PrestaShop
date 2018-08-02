package com.PrestaShop.Admin;

import static com.PrestaShop.InitialConfiguration.InitialConfiguration.log;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class Category{
	
	private RemoteWebDriver driver;
	
	@FindBy(how = How.XPATH, xpath = "//input[@name='categoryFilter_name']")
	private WebElement categoryFilterName;
	
	@FindBy(how = How.XPATH, xpath = "//td[@class='pointer' and position() = 3]")
	private List<WebElement> categoryNames;
		
	@FindBy(how = How.XPATH, xpath = "//button[@name='submitFilter']")
	private WebElement searchFilter;

	@FindBy(how = How.XPATH, xpath = "//div[@class='alert alert-success']/button[@class='close']")
	private WebElement closeButtonAlertSuccess;
	
	@FindBy(how = How.XPATH, xpath = "//a[@id = 'page-header-desc-category-new_category']")
	private WebElement addCategories;

	public Category(RemoteWebDriver driver) {		
		
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
	}
	
	public NewCategory addNewCategory() {

		log.debug("Клик на кнопку \"Добавить категорию\".");
		addCategories.click();
		return new NewCategory(driver);
	}

	public Category clickOnCloseButtonAlert() {
		
		WebElement parentElement = closeButtonAlertSuccess.findElement(By.xpath("./.."));
		
		log.debug("Проверка уведомления, что категория создалась.");
		String text = parentElement.getText();
		String textCheck = text.substring(1, text.length()).trim();
		Assert.assertEquals("Создано", textCheck);
		log.debug("Закрытие уведомления, что категория создалась.");
		closeButtonAlertSuccess.click();
		
		return this;
	}

	public Category setFilterByNameCategory(String nameCategory) {
		
		log.debug("Ввод в поле имя категории для фильтрации " + nameCategory + ".");
		categoryFilterName.sendKeys(nameCategory);		
		return this;
	}
	
	public Category filterSearch() {
		
		log.debug("Нажатие на кнопку \"Поиск\".");
		searchFilter.click();		
		return this;
	}
	
	public Category containsCategory(String nameCategory) {
		
		log.debug("Проверка категории: " + nameCategory + " на поиск среди других категорий.");
		
		List<String> listNames = new LinkedList<>(); 
		for(WebElement element: categoryNames)
			listNames.add(element.getText());
		
		Assert.assertEquals(true, listNames.contains(nameCategory));		
		return this;
	}
}
