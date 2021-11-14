package com.mexx.testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mexx.base.TestBase;

public class UiTap extends TestBase {

	WebDriverWait wait;

	@Test
	public void classAttribute() throws InterruptedException {

		driver.findElement(By.xpath(OR.getProperty("clsAtb"))).click();
		driver.findElement(By.xpath(OR.getProperty("btnPrm"))).click();
		Thread.sleep(3000);

		// Switching to Alert
		Alert alert = driver.switchTo().alert();

		// Capturing alert message.
		String alertMessage = driver.switchTo().alert().getText();

		// Displaying alert message
		System.out.println(alertMessage);
		Thread.sleep(3000);

		Assert.assertEquals(alertMessage, "Primary button pressed");

	}

//	@Test
//	public void hiddenLayer() {
//		
//		
//		driver.findElement(By.xpath(OR.getProperty("hdnLyr"))).click();
//		
//		// identify element and click
//		
//	
//		
//		
//		driver.findElement(By.xpath(OR.getProperty("grnbtn"))).click();
//		
//		driver.findElement(By.xpath(OR.getProperty("grnbtn"))).;
//	      

	// }

	@Test
	public void ajaxData() {

		driver.findElement(By.xpath(OR.getProperty("ajxdata"))).click();
		driver.findElement(By.xpath(OR.getProperty("ajxbtn"))).click();

		// adding implicit wait because element is not immediately present
		wait = new WebDriverWait(driver, 50);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement TextElement = driver.findElement(By.className("bg-success"));

		wait.until(ExpectedConditions.visibilityOf(TextElement));
		String textAfter = TextElement.getText().trim();
		// System.out.println(textAfter);

		Assert.assertEquals(textAfter, "Data loaded with AJAX get request.");
	}

	@Test
	public void scrollBars() {

		driver.findElement(By.xpath(OR.getProperty("sclbar"))).click();

		try {
			WebElement element = driver.findElement(By.id("hidingButton"));
			Actions actions = new Actions(driver);
			actions.moveToElement(element);
			actions.perform();
			Assert.assertNotNull(element);

		} catch (Exception e) {
			Assert.fail("Element not Found");

		}

	}

	@Test
	public void dynamicTable() {

		driver.findElement(By.xpath(OR.getProperty("dyntbl"))).click();

		String cpuTableValue = null;
		List<WebElement> we = driver.findElements(By.xpath(OR.getProperty("tbltsk")));
		String[] webElementList = we.get(0).getText().split("\n");

		for (String w : webElementList) {

			if (w.contains("Chrome")) {

				String str = w;
				cpuTableValue = getSplittedValues(str);
				System.out.println("Chrome CPU value from Table:" + cpuTableValue);

			}
		}

		// Taking value of CPU from yellow Label
		String S = driver.findElement(By.xpath("//p[@class='bg-warning']")).getText();

		String cpuYellowLableValue = getSplittedValues(S);
		System.out.println("Chrome CPU value from Yellow Lable: " + cpuYellowLableValue);
		Assert.assertEquals(cpuTableValue, cpuYellowLableValue);

	}

	// Function for slippting the string and get the value
	String getSplittedValues(String str) {
		String[] splited = str.split("\\s+");
		String cpuValue = null;
		for (String element : splited) {
			if (element.contains("%")) {

				cpuValue = element;
			}
		}
		return cpuValue;

	}

	@Test
	public void progressBar() throws InterruptedException {
		String percentage = null;
		driver.findElement(By.xpath(OR.getProperty("prgbar"))).click();

		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement progressbar = driver.findElement(By.cssSelector("#progressBar"));
		String s = progressbar.getAttribute("aria-valuenow");
		System.out.println("before click start button:" + s);
		driver.findElement(By.xpath(OR.getProperty("strbtn"))).click();
		Thread.sleep(1000);

		while (true) {

			 percentage = driver.findElement(By.cssSelector("#progressBar")).getAttribute("aria-valuenow");
			if (percentage.equals("75")) {
				System.out.println("reached on 75 sucess");
				driver.findElement(By.xpath(OR.getProperty("stpbtn"))).click();
				WebElement progressbar1 = driver.findElement(By.cssSelector("#progressBar"));
				String s1 = progressbar1.getAttribute("aria-valuenow");
				System.out.println("After click stop button:" + s1);

				break;
			} else {

				System.out.println("Still Waiting to reach 75");
			}

		}

		String result = driver.findElement(By.xpath("//p[@id='result']")).getText();
		System.out.println(result);
		int spltresult= Integer.parseInt(getSplittedResult(result)); 
		if(Integer.parseInt(percentage)>=75) {
			Assert.assertTrue(spltresult>=0);
		}else {
			Assert.fail();
		}

	}

	// Function for slippting the string and get the value
		String getSplittedResult(String str) {
			String[] splited = str.split("\\s+");
			String cpuValue = null;
			for (String element : splited) {
				if (element.contains(",")) {

					cpuValue = element.replace(",", "");
				}
			}
			return cpuValue;

		}
	
////Function for slippting the string and get the value
//	String getSplittedResult(String str) {
//		str = str.substring(str.indexOf("\t") + 1);
//		str = str.substring(0, str.indexOf(","));
//		return str;
//	}

	@Test

	public void visibility() {

		driver.findElement(By.xpath(OR.getProperty("vsblt"))).click();

		boolean rmvbool = driver.findElement(By.xpath(OR.getProperty("rmvbtn"))).isDisplayed();
		System.out.println("\n" + "Removed button is present: " + rmvbool);

		boolean zerbool = driver.findElement(By.xpath(OR.getProperty("zerbtn"))).isDisplayed();
		System.out.println("\n" + "Zero Width button is present: " + zerbool);

		boolean ovrbool = driver.findElement(By.xpath(OR.getProperty("ovrlpd"))).isDisplayed();
		System.out.println("\n" + "Overlapped button is present: " + ovrbool);

		boolean opabool = driver.findElement(By.xpath(OR.getProperty("opacty"))).isDisplayed();
		System.out.println("\n" + "Opacity 0 button is present: " + opabool);

		boolean vhdbool = driver.findElement(By.xpath(OR.getProperty("vhdbtn"))).isDisplayed();
		System.out.println("\n" + "Visibility Hidden button is present: " + vhdbool);

		boolean dspbool = driver.findElement(By.xpath(OR.getProperty("dspnon"))).isDisplayed();
		System.out.println("\n" + "Display None button is present: " + dspbool);

		boolean offbool = driver.findElement(By.xpath(OR.getProperty("offscr"))).isDisplayed();
		System.out.println("\n" + "Offscreen button is present: " + offbool);

		driver.findElement(By.xpath(OR.getProperty("hidbtn"))).click();
		System.out.println("\n" + "---------------Click on the Hide Button to hide all The Buttons------------ ");

		// checking the Remove Button Visibility
		boolean rmvbool1 = isElementPresent("//button[@id='removedButton']");

		if (rmvbool1 == false) {
			System.out.println("\n" + "Removed button is not Visible on the Playground ");
		} else {
			System.out.println("\n" + "Removed button is Visible and you are doing something Wrong");
		}
		Assert.assertFalse(rmvbool1);

		// Checking the Zero Width Visibility
		String width = driver.findElement(By.xpath(OR.getProperty("zerbtn"))).getCssValue("width");
		if (width.equals("0px")) {
			System.out.println("\n" + "Zero Width is not Visible on the Playground ");
		} else {
			System.out.println("\n" + "Zero Width is Visible and you are doing something Wrong");
		}
		Assert.assertEquals(width, "0px");

		// Checking the Overlapped Visibility
		boolean ovrbool1 = isElementPresent("//div[@id='hidingLayer']");
		if (ovrbool1 == true) {
			System.out.println("\n" + "Overlapped button is not Visible on the Playground ");
		} else {
			System.out.println("\n" + "Overlapped button is Visible and you are doing something Wrong");
		}
		Assert.assertTrue(ovrbool1);

		// Checking the Opacity 0 Visibility
		String opa = driver.findElement(By.id("transparentButton")).getAttribute("style");
		if (opa.equals("opacity: 0;")) {
			System.out.println("\n" + "Opacity 0 Button is not Visible on the Playground");
		} else {
			System.out.println("\n" + "Opacity 0 Button is Visible and you are doing something Wrong");
		}
		Assert.assertEquals(opa, "opacity: 0;");

		// Checking the Visibility of Hidden button
		String visibility = driver.findElement(By.id("invisibleButton")).getAttribute("style");
		if (visibility.equals("visibility: hidden;")) {
			System.out.println("\n" + "Visibility Hidden button is not Visible on the Playground ");
		} else {
			System.out.println("\n" + "Visibility Hidden Button is Visible and you are doing something Wrong");
		}
		Assert.assertEquals(visibility, "visibility: hidden;");

		// Checking the Display None visibility
		String ndpbtn = driver.findElement(By.id("notdisplayedButton")).getAttribute("style");
		if (ndpbtn.equals("display: none;")) {
			System.out.println("\n" + "Display None button is not Visible on the Playground ");
		} else {
			System.out.println("\n" + "Display None button is Visible and you are doing something Wrong");
		}
		Assert.assertEquals(ndpbtn, "display: none;");

		// Checking the Offscreen visibility
		WebElement offscr = driver.findElement(By.id("offscreenButton"));

		if (driver.findElement(By.id("offscreenButton")) != null) {
			System.out.println("\n" + "Offscreen button is not Visible on the Playground ");
		} else {
			System.out.println("\n" + "Offscreen button is Visible and you are doing something Wrong");
		}
		Assert.assertNotNull(offscr);
	}

	public static boolean isElementPresent(String locator) {

		try {

			driver.findElement(By.xpath((locator))).isDisplayed();
			return true;
		} catch (Throwable t) {
			return false;
		}
	}

	// part 4

	@Test
	public void nonBreakingSpace() {
		String expectedMessage = "no such element: Unable to locate element";
		driver.findElement(By.xpath(OR.getProperty("nbksp"))).click();

		String S1 = driver.findElement(By.xpath("//button[text()='My\u00A0Button']")).getText();
		assertEquals(S1, "My Button", "Testcase Pass");

		try {
			String S2 = driver.findElement(By.xpath("//button[text()='My Button']")).getText();

		} catch (Exception e) {
			String actualMessage = e.getMessage();
			assertTrue(actualMessage.contains(expectedMessage));

		}
	}
}
