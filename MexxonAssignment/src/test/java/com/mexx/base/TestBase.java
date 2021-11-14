package com.mexx.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	
	
	

	@BeforeMethod
	public void setUp() {
		
          if (driver == null) {
			
			
        	  try {
  				fis = new FileInputStream(
  						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
  			} catch (FileNotFoundException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
  			try {
  				config.load(fis);
  				
  			} catch (IOException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}

  			try {
  				fis = new FileInputStream(
  						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
  			} catch (FileNotFoundException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
  			try {
  				OR.load(fis);
  				
  			} catch (IOException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
          }

          if (config.getProperty("browser").equals("firefox")) {

				// System.setProperty("webdriver.gecko.driver", "gecko.exe");
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();

			} else if (config.getProperty("browser").equals("chrome")) {

				/*System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
			*/	
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				
			} 

          driver.get(config.getProperty("testsiteurl"));
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
		//	wait = new WebDriverWait(driver, 5);
		
		
	}
	
	@AfterMethod
	public void tearDown( ) {
		
		driver.quit();
	}
	
	
	
}
