package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import common.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeHeadlessDriverManager implements BrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		
		ChromeOptions chromeOptions= new ChromeOptions();
		chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("--window-size=1920x1080");
		return new ChromeDriver(chromeOptions);
		
	}

}

