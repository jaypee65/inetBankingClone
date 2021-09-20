package com.inetBankingClone.testCase;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBankingClone.pageObjects.LoginPage;
import com.inetBankingClone.utilities.XLUtils;

public class TC_LoginDDTClone_002 extends BaseClass {
	
	@Test(dataProvider = "LoginData")
	public void loginDDT(String user,String pwd) throws InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		log.info("user name provided");
		lp.setPassword(pwd);
		log.info("password provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			log.warn("Login failed");
		}
		else
		{
			Assert.assertTrue(true);
			log.info("Login passed");
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();//close logout alert
			driver.switchTo().defaultContent();
			
		}
		
	}
	
	public boolean isAlertPresent() //user defined method created to check alert is presetn or not
	{
		try
		{
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
		
	}
	
	@DataProvider(name="LoginData")
	String [][]getData() throws IOException
	{
		String path = System.getProperty("user.dir")+"/src/test/java/com/inetBankingClone/testData/LoginData.xlsx";
		int totalrows = XLUtils.getRowCount(path, "Sheet1");
		int totalcols = XLUtils.getCellCount(path, "Sheet1", 1);
		String logindata[][] = new String[totalrows][totalcols];
		for(int i=1; i<=totalrows;i++)
		{
			for(int j=0;j<totalcols;j++)
			{
				logindata[i-1][j]=	XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return logindata;
		
	}
}
