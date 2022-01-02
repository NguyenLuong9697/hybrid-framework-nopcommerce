package factoryEnvironment;

import org.openqa.selenium.WebDriver;

import browserList.BROWSER;
import factoryBrowser.BrowserNotSupportedException;
import factoryBrowser.ChromeDriverManager;
import factoryBrowser.ChromeHeadlessDriverManager;
import factoryBrowser.EdgeChromiumDriverManager;
import factoryBrowser.FirefoxDriverManager;
import factoryBrowser.FirefoxHeadlessDriverManager;
import factoryBrowser.IEDriverManager;
import factoryBrowser.OperaDriverManager;

public class LocalFactory {
	private WebDriver driver;
	private String browserName;
	public LocalFactory(String browserName) {
		this.browserName = browserName;
	}
	
	public WebDriver createDriver() {
		BROWSER browser= BROWSER.valueOf(browserName.toUpperCase());
		switch (browser) {
		case H_CHROME:
			driver = new ChromeHeadlessDriverManager().getBrowserDriver();
			break;
			
		case H_FIREFOX:
			driver =new FirefoxHeadlessDriverManager().getBrowserDriver();
			break;
			
		case FIREFOX: 
			driver = new FirefoxDriverManager().getBrowserDriver();
			break;
		
		case CHROME:
			driver = new ChromeDriverManager().getBrowserDriver();
			break;
		case IE:
			driver =new IEDriverManager().getBrowserDriver();
			break;
		case EDGE_CHROMIUM:
			driver = new EdgeChromiumDriverManager().getBrowserDriver();
			break;
		case OPERA:
			driver = new OperaDriverManager().getBrowserDriver();
			break;
		default:
			throw new BrowserNotSupportedException(browserName);
		
		}
		return driver;
	}
}
		
