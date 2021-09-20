package com.inetBankingClone.testCase;
import java.io.File;
import java.io.IOException;


import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBankingClone.utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readconfig = new ReadConfig();
	
	public String baseURL = readconfig.getApplicationURL();
	public String username = readconfig.getUserName();
	public String password = readconfig.getPassword();
	public  WebDriver driver;
	public static Logger log;

	
	@Parameters({"browser"})   
	@BeforeClass
	public void setup(String br) throws InterruptedException
	{
		log = LogManager.getLogger(BaseClass.class.getName());	
		if(br.equals("chrome"))
		{
			DesiredCapabilities ch= new DesiredCapabilities();
			ch.setAcceptInsecureCerts(true);
			ch.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			ch.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			ChromeOptions c= new ChromeOptions();
			c.merge(ch);
			
			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
			driver = new ChromeDriver(c);
		}
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver", readconfig.getIEPath());
			driver = new InternetExplorerDriver();
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
			driver = new  FirefoxDriver();
		}	
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.get(baseURL);
		log.info("URL is opened");
		
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	public void getScreenShotPath(String testCaseName,WebDriver driver) throws IOException
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationFilePath = System.getProperty("user.dir")+"testCaseName"+".png";
		FileUtils.copyFile(src, new File(destinationFilePath));
		
	}
	
}
