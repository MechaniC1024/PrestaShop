package com.PrestaShop.InitialConfiguration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public enum InstanceDriver {

	INSTANCE_DRIVER {

		private Map<String, List<Driver>> mapOfDrivers = new HashMap<>();
		
		public Map<String, List<Driver>> getDriver() {
			return mapOfDrivers;
		}

		public void addBrowser(String browser, int counterOfBrowser, DesiredCapabilities capabilities) {

			List<Driver> listOfDrivers = new ArrayList<>();

			for (int i = 0; listOfDrivers.size() < counterOfBrowser; i++) {

				try {
					listOfDrivers.add(i, new Driver().setFlag(true)
							.setDriver(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), capabilities)));
					
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}

			mapOfDrivers.put(browser, listOfDrivers);

		}

	};

	public void addBrowser(String browser, int counterOfBrowser, DesiredCapabilities capabilities) {
	};

	public Map<String, List<Driver>> getDriver() {
		return null;
	};

}
