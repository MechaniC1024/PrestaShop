package com.PrestaShop.Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import static com.PrestaShop.Wait.Wait.*;
import static com.PrestaShop.InitialConfiguration.InitialConfiguration.*;
import static com.PrestaShop.Report.Report.*;

public class UserPage {

	private RemoteWebDriver driver;

	private By loading = By.xpath("//span[@id='ajax_running']");

	@FindBy(how = How.XPATH, xpath = "//span[@class = 'employee_avatar_small']/img")
	private WebElement imageProfile;
	
	@FindBy(how = How.XPATH, xpath = "	//div[@class='img-circle person']/i")
	private WebElement imageProfileSmall;

	@FindBy(how = How.XPATH, xpath = "//li[@data-submenu = '1']/a")
	private WebElement dashboard;

	@FindBy(how = How.XPATH, xpath = "//li[@data-submenu = '3']/a")
	private WebElement orders;

	@FindBy(how = How.XPATH, xpath = "//li[@data-submenu = '9']/a")
	protected WebElement catalog;

	@FindBy(how = How.XPATH, xpath = "//li[@data-submenu = '23']/a")
	private WebElement customer;

	@FindBy(how = How.XPATH, xpath = "//li[@data-submenu = '27']/a")
	private WebElement customerService;

	@FindBy(how = How.XPATH, xpath = "//li[@data-submenu = '31']/a")
	private WebElement statistics;

	@FindBy(how = How.XPATH, xpath = "//li[@data-submenu = '42']/a")
	private WebElement modules;

	@FindBy(how = How.XPATH, xpath = "//li[@data-submenu = '46']/a")
	private WebElement design;

	@FindBy(how = How.XPATH, xpath = "//li[@data-submenu = '52']/a")
	private WebElement delivery;

	@FindBy(how = How.XPATH, xpath = "//li[@data-submenu = '55']/a")
	private WebElement payment;

	@FindBy(how = How.XPATH, xpath = "//li[@data-submenu = '58']/a")
	private WebElement international;

	@FindBy(how = How.XPATH, xpath = "//li[@data-submenu = '73']/a")
	private WebElement shopParameters;

	@FindBy(how = How.XPATH, xpath = "//li[@data-submenu = '95']/a")
	private WebElement advancedParameters;

	public UserPage(RemoteWebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
	}

	private void waitingLoading() {

		log.debug("Ожидание загрузки страницы.");
		waitingForVisibilityOfElementLocated(driver, loading, 60);
		waitingForInvisibilityOfElementLocated(driver, loading, 60);
	}

	public Catalog selectCatalog() {

		waitingLoading();
		
		log.debug("Переход в раздел \"Каталог\".");
		Actions actions = new Actions(driver);
		actions.moveToElement(catalog).perform();

		return new Catalog(driver, actions);
	}

	public Profile clickOnImageProfile() {
		
		log.debug("Клик на изображение профиля.");
		try {
			imageProfile.click();					
		}
		catch(NoSuchElementException e) {
			imageProfileSmall.click();	
		}
		
		return new Profile(driver);

	}

	private void clickOnParagraphAndVerifyTitle(WebElement element) {
		
		log.debug("Клик по элементу " + element + ".");
		element.click();
		
		String title = driver.getTitle();
		log.debug("Взятие титула страницы: " + title + ".");

		addAttachmentToReport("Заголовок страницы.", title);

		driver.navigate().refresh();
		String titleRefresh = driver.getTitle();
		log.debug("Обновление страницы и взятие титула страницы после обновления: " + title + ".");

		Assert.assertEquals(title, titleRefresh);
	}

	public UserPage clickOnDashboardGetTitleAndRefresh() {

		clickOnParagraphAndVerifyTitle(dashboard);
		return this;
	}

	public UserPage clickOnOrdersGetTitleAndRefresh() {

		clickOnParagraphAndVerifyTitle(orders);
		return this;
	}

	public UserPage clickOnCatalogGetTitleAndRefresh() {

		clickOnParagraphAndVerifyTitle(catalog);
		return this;
	}

	public UserPage clickOnCustomerGetTitleAndRefresh() {

		clickOnParagraphAndVerifyTitle(customer);
		return this;
	}

	public UserPage clickOnCustomerServiceGetTitleAndRefresh() {

		clickOnParagraphAndVerifyTitle(customerService);
		return this;
	}

	public UserPage clickOnStatisticsGetTitleAndRefresh() {

		clickOnParagraphAndVerifyTitle(statistics);
		return this;
	}

	public UserPage clickOnModulesGetTitleAndRefresh() {

		clickOnParagraphAndVerifyTitle(modules);
		return this;
	}

	public UserPage clickOnDesignGetTitleAndRefresh() {

		clickOnParagraphAndVerifyTitle(design);
		return this;
	}

	public UserPage clickOnDeliveryGetTitleAndRefresh() {

		clickOnParagraphAndVerifyTitle(delivery);
		return this;
	}

	public UserPage clickOnPaymentGetTitleAndRefresh() {

		clickOnParagraphAndVerifyTitle(payment);
		return this;
	}

	public UserPage clickOnInternationalGetTitleAndRefresh() {

		clickOnParagraphAndVerifyTitle(international);
		return this;
	}

	public UserPage clickOnShopParametersGetTitleAndRefresh() {

		clickOnParagraphAndVerifyTitle(shopParameters);
		return this;
	}

	public UserPage clickOnAdvancedParametersGetTitleAndRefresh() {

		clickOnParagraphAndVerifyTitle(advancedParameters);
		return this;
	}
}
