package DevOPS.devOPS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class baseDriver {

	public baseDriver() {
		// Constructor - driver initialization handled in Chromedriver() method
	}

	public static WebDriver Chromedriver() {
		// Use WebDriverManager to automatically handle driver setup
		WebDriverManager.chromedriver().setup();

		// Configure Chrome options for Jenkins Windows slave
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless"); // Run without GUI for Jenkins
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--window-size=1920,1080");

		WebDriver driver = new ChromeDriver(options);
		return driver;
	}
}