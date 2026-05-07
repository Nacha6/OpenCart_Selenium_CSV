package driverfactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import utilities.ConfigReader;

public class DriverFactory {
  public WebDriver driver;
  public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/**
	 * This method is used to initialize the threadlocal driver on the basis of given
	 * browser
	 * 
	 * @param browser
	 * @return this will return tldriver.
	 */
	public WebDriver init_driver(String browser) {

		System.out.println("browser value is: " + browser);

		if (browser.equals("chrome")) {
			//WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			if (ConfigReader.isChromeHeadless())
				chromeOptions.addArguments("--headless");
			driver = new ChromeDriver(chromeOptions);
			tlDriver.set(driver);
		} else if (browser.equals("firefox")) {
			//WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if (ConfigReader.isFireFoxHeadless())
				firefoxOptions.addArguments("--headless");
			driver = new FirefoxDriver(firefoxOptions);
			
			tlDriver.set(driver);
		}
		else if (browser.equals("edge"))
		{
			EdgeOptions edgeOptions = new EdgeOptions();
			if (ConfigReader.isEdgeHeadless())
				edgeOptions.addArguments("--headless");
			driver = new EdgeDriver(edgeOptions);
			tlDriver.set(driver);
		} 
		else {
			System.out.println("Please pass the correct browser value: " + browser);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();

	}

	/**
	 * this is used to get the driver with ThreadLocal
	 * 
	 * @return
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
}


