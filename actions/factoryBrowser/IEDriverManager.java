package factoryBrowser;
import  static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IEDriverManager implements BrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		if(!IS_OS_WINDOWS) {
			throw new BrowserNotSupportedException("IE is not support on:"+ System.getProperty("os.name"));
		}
		WebDriverManager.iedriver().arch32().setup();
		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
		capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capability.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, true);
		capability.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		capability.setCapability("ignoreProtectedModeSettings", true);
		capability.setCapability("ignoreZoomSetting", true);
		capability.setCapability("requireWindowFocus", true);
		capability.setJavascriptEnabled(true);
		capability.setCapability("enableElementCacheCleanup", true);
		capability.setBrowserName("internet explorer");
		capability.setPlatform(org.openqa.selenium.Platform.ANY);
		return new InternetExplorerDriver(capability);
		
	}

}

