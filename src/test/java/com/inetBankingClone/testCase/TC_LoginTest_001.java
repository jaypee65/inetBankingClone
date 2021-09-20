package com.inetBankingClone.testCase;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBankingClone.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass
{
	
	@Test()
	public void loginTest() throws InterruptedException, IOException
	{
		LoginPage lp = new LoginPage(driver);
		Thread.sleep(8000);
		lp.setUserName(username);
		log.info("Entered UserName");
		
		lp.setPassword(password);
		log.info("Entered Password");
		
		lp.clickSubmit();
		log.info("Submit button is clicked");
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			log.info("Login test pass");
		}
		else
		{
			
			getScreenShotPath("loginTest",driver);
			Assert.assertTrue(false);
			log.info("Login test failed");
		}
	}
	
	
}
