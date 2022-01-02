package common;

import java.io.File;

public class GlobalConstants {
	public static final long SHORT_TIMEOUT=5;
	public static final long LONG_TIMEOUT=30;
	public static final String PROJECT_PATH=System.getProperty("user.dir");
	public static final String SEPERATOR=File.separator;
	public static final String UPLOAD_FOLDER_PATH= PROJECT_PATH+ SEPERATOR+"uploadFiles"+SEPERATOR;
	public static final String BROWSER_LOG_FIREFOX= PROJECT_PATH + SEPERATOR + "browserLogs"+ SEPERATOR+"firefox.log";
	public static final String DOWNLOAD_FILES= PROJECT_PATH+SEPERATOR +"downloadFiles"+SEPERATOR;
	public static final String BROWSER_STACK_USER_NAME="";
	public static final String BROWSER_STACK_AUTOMATE_KEY="";
	public static final String BROWSER_STACK_URL="https://"+BROWSER_STACK_USER_NAME+":"+
	BROWSER_STACK_AUTOMATE_KEY+"hub-cloud.browserstack.com/wd/hub";
	public static final String SAUCE_LAB_USERNAME="";
	public static final String SAUCE_LAB_AUTOMATE_KEY="";
	public static final String SAUCE_LAB_URL="https:"+SAUCE_LAB_USERNAME+":"+ SAUCE_LAB_AUTOMATE_KEY
	+ "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";
	public static final String CROSS_USERNAME="";
	public static final String CROSS_AUTOMATE_KEY="";
	public static final String CROSS_URL="";
			
}
