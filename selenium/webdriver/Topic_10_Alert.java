package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_10_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver,10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_Accept_Alert_01() {
		driver.get("http://demo.guru99.com/v4/");
		driver.findElement(By.name("btnLogin")).click();
		
		//Wait cho alert xuat hien + Switch qua alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		//Accept
		alert.accept();
		
		//cancel
		alert.dismiss();
		
		//get text
		alert.getText();
		
		//send key
		alert.sendKeys("");

	}
	
	//@Test
	public void TC_01_Accept_Alert_02() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals("I am a JS Alert",alert.getText());
		
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
	}

	//@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals("I am a JS Confirm",alert.getText());
		
		alert.dismiss();
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Ok");
	}

	//@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String prompt = "test";
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals("I am a JS prompt",alert.getText());

		alert.sendKeys("test");
		
		alert.dismiss();
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: null");

		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		alert.sendKeys(prompt);
		
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: " + prompt );

	}

	//@Test
	public void TC_04_Authentication_Alert() {
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}

	//@Test
	public void TC_05_Authentication_Alert() {
		driver.get("http://the-internet.herokuapp.com");
		
		String href = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		passValueToUrl(href, "admin", "admin");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

	public void passValueToUrl (String url, String username, String password) {
		String[] hrefValue = url.split("//");

		url = hrefValue[0] + "//" + username + ":" + password + "@" + hrefValue[1];
		driver.get(url);
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
