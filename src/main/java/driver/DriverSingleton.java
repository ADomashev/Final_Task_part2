package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public enum DriverSingleton {

	DRIVER_INSTANCE;
	private static final String CHROME = "webdriver.chrome.driver";
	private static final String CHROME_PATH = "C:\\WebDrivers\\chromedriver_win32\\chromedriver.exe";
	private WebDriver driver;

	public WebDriver getDriver() {
		if (driver == null) {
			System.setProperty(CHROME, CHROME_PATH);
			driver = new ChromeDriver();
			return driver;
		}
		return driver;
	}

	public void closeDriver() {
		driver.quit();
	}
}
