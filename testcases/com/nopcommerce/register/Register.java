package com.nopcommerce.register;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import common.BaseTest;
import serverConfig.IServer;

public class Register extends BaseTest{

WebDriver driver;
IServer server;

//String envName, String browserName, String urlValue, String ipAddress, String  portNumber, String osName, String osVersion
 @Parameters({ "environment","server","browser", "ipAddress","portNumber","osName","osVersion" })
  @BeforeClass
  public void beforeClass(@Optional("local") String environment,@Optional("testing") String serverName,@Optional("chrome")String browserName,@Optional("localhost") String ipAddress,@Optional("4444") String portNumber, @Optional("windows")String osName,@Optional("10") String osVersion) {
	 
	 ConfigFactory.setProperty("server", serverName);
	 server =ConfigFactory.create(IServer.class);
	 log.info("Preconditon: Open browser "+browserName+ " and navigate to "+server.url());
	 driver= getBrowserDriver(environment,server.url(),browserName, ipAddress, portNumber, osName, osVersion);
  }


@Test
  public void Register_Empty_Data() {
  }
  
  @Test
  public void Register_Invalid_Email() {
  }
    
  @Test
  public void Register_Invalid_Information() {
  }
  
  @Test
  public void Register_Existed_Email() {
  }
  
  @Test
  public void Register_Password_Less_6_Characters() {
  }
  
  @Test
  public void Register_Wrong_ConfirmPassword() {
  }
  @AfterClass
  public void afterClass() {
  }

}
