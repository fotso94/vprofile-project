package DevOPS.devOPS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class baseDriver {

    public baseDriver() {
        Chromedriver();
    }

    public static WebDriver Chromedriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        // options.addArguments("--headless=new"); // Optional: enable for headless EC2

        WebDriver driver = new ChromeDriver(options);
        return driver;
    }
}
