package com.PrestaShop.InitialConfiguration;

import java.net.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;
import org.testng.annotations.*;

import static com.PrestaShop.DataResources.ProcessingData.*;

import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Step;

public class InitialConfiguration {

	private RemoteWebDriver driver;

	public static final Platform platform = Platform.LINUX;
	public static Logger log = Logger.getLogger("LoggerReport");
	
	static {
		String log4jConfigFile = "src" + sep + "test" + sep + "resources" + sep + "propertiesLog4j" + sep + "log4j.properties";
        PropertyConfigurator.configure(log4jConfigFile);
	}	
	
	@Step("Браузер на котором выполняются тесты {browser}.")
	@BeforeTest(description = "Инициализация браузера.")
	@Parameters("browser")
	public void setUp(@Optional("Chrome") String browser) {

//		System.setProperty("browser.app", browser);
		
		//Layout pattern = new PatternLayout("%d{MM-dd-yyyy HH:mm:ss} %-5p %c:%m%n");
//		RollingFileAppender appender = new RollingFileAppender();
		//pattern,		"src" + sep + "test" + sep + "resources" + sep + "outputLog4j" + sep + "Selenium_" + browser,		false
//		try {
//			appender.setWriter(new FileWriter(new File("src" + sep + "test" + sep + "resources" + sep + "outputLog4j" + sep + "Selenium_" + browser), true));
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		//appender.setMaxBackupIndex(5);
		//appender.setMaxFileSize("10MB");
		//appender.setEncoding("Cp1251");
		//appender.setAppend(false);
//		appender.activateOptions();

		//log.setAdditivity(false);
		//log.setLevel(Level.DEBUG);
//		log.addAppender(appender);

		switch (browser) {
		case "Chrome": {
			System.setProperty("webdriver.chrome.driver", "Drivers" + sep + "chromedriver.exe");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			;
			driver.manage().window().maximize();
			log.debug("Запущен браузер " + browser + ".");
			break;

		}
		case "Firefox": {
			System.setProperty("webdriver.gecko.driver", "Drivers" + sep + "geckodriver.exe");
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			;
			driver.manage().window().maximize();
			log.debug("Запущен браузер " + browser + ".");
			break;

		}
		case "Android": {
			DesiredCapabilities cap = new DesiredCapabilities();

			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1");
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "192.168.152.101:5555");
			cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			log.debug("Запущен на платформе " + browser + ".");
			break;
		}
		}

	}

	public void setURL(String url) {

		log.debug("Переход на страницу по адресу: " + url + ".");
		driver.get(url);
	}

	public RemoteWebDriver getDriver() {

		return driver;
	}

	@AfterTest(description = "Закрытие браузера.")
	public void closeBrowser() {
				
		if (driver != null) {
			driver.quit();
			log.debug("Работа браузера завершена.\n\n\n");
		}
	}
}