package com.ddf.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.ddf.base.TestBase;

public class LoginTest extends TestBase {

	
	
	@Test
	public void loginAsBankManager() throws InterruptedException {
		
		driver.findElement(By.cssSelector(OR.getProperty("bmlBtn_CSS"))).click();
		Thread.sleep(3000);
		
	} 
	
}
