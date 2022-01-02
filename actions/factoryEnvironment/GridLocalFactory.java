package factoryEnvironment;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import browserList.BROWSER;
import io.github.bonigarcia.wdm.WebDriverManager;

public class GridLocalFactory {
	private WebDriver driver;
	private String browserName;
	private String ipAddress;
	private String portNumber;
	
	public GridLocalFactory(String browserName, String ipAddress, String portNumber) {		
		this.browserName = browserName;
		this.ipAddress = ipAddress;
		this.portNumber = portNumber;
	}
	
	public WebDriver createDriver() {
		BROWSER browser= BROWSER.valueOf(browserName.toUpperCase());
		DesiredCapabilities capabilities =null;
		
		switch (browser) {
		case FIREFOX: 
			WebDriverManager.firefoxdriver().setup();
			capabilities = DesiredCapabilities.firefox();
			capabilities.setBrowserName("firefox");
			capabilities.setPlatform(Platform.ANY);
			FirefoxOptions f_options = new FirefoxOptions();
			f_options.merge(capabilities);
			break;
		
		case CHROME:
			WebDriverManager.chromedriver().setup();
			capabilities = DesiredCapabilities.chrome();
			capabilities.setBrowserName("chrome");
			capabilities.setPlatform(Platform.ANY);
			ChromeOptions c_options = new ChromeOptions();
			c_options.merge(capabilities);
			break;
			
		case EDGE_CHROMIUM:
			WebDriverManager.edgedriver().setup();
			capabilities = DesiredCapabilities.edge();
			capabilities.setBrowserName("edge");
			capabilities.setPlatform(Platform.ANY);
			EdgeOptions e_options = new EdgeOptions();
			e_options.merge(capabilities);
			break;
		case IE:
			WebDriverManager.iedriver().arch32().setup();
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, true);
			capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			capabilities.setCapability("ignoreProtectedModeSettings", true);
			capabilities.setCapability("ignoreZoomSetting", true);
			capabilities.setCapability("requireWindowFocus", true);
			capabilities.setJavascriptEnabled(true);
			capabilities.setCapability("enableElementCacheCleanup", true);
			capabilities.setBrowserName("internet explorer");
			capabilities.setPlatform(org.openqa.selenium.Platform.ANY);
			
		default:
			throw new RuntimeException("Please check brower name");
		
		}
		try {
			driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub",ipAddress,portNumber)), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return driver;
	}
	
	

}
