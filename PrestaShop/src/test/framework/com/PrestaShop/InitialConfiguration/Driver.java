package com.PrestaShop.InitialConfiguration;

import org.openqa.selenium.remote.RemoteWebDriver;

class Driver {
	private boolean flag;
	private RemoteWebDriver driver;

	public boolean getFlag() {
		return flag;
	}

	public Driver setFlag(boolean flag) {
		this.flag = flag;
		return this;
	}

	public RemoteWebDriver getDriver() {
		return driver;
	}

	public Driver setDriver(RemoteWebDriver driver) {
		this.driver = driver;
		return this;
	}
}
