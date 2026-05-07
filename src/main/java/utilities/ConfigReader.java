package utilities;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private static Properties prop;

	/**
	 * This method is used to load the properties from config.properties file
	 * @return it returns Properties prop object
	 */
	public Properties init_prop() {

		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}

	public static boolean isChromeHeadless() {
		return Boolean.parseBoolean(prop.getProperty("browser.chrome.headless", "false"));
	}
	
	public static boolean isFireFoxHeadless() {
		return Boolean.parseBoolean(prop.getProperty("browser.firefox.headless", "false"));
	}
	
	public static boolean isEdgeHeadless() {
		return Boolean.parseBoolean(prop.getProperty("browser.edge.headless", "false"));
	}
	public static void setBrowser(String browserName) {
		prop.setProperty("browser", browserName);
	}

	public static String getBrowser() {
		return prop.getProperty("browser");
	}

	public static String getBaseUrl() {
		return prop.getProperty("base.url");
	}
}
