package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Wait_IV_Static {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	By startButton = By.cssSelector("#start>button");
	By loadingIcon = By.id("loading");
	By helloworldText = By.id("finish");
	
	@BeforeClass
	public void beforeClass() {		
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");

		driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void TC_01_Less() {
		driver.get("http://the-internet.herokuapp.app/dynamic_loading/2");
		
		driver.findElement(startButton).click();
		
		//Thoi gian cho it hon thoi gian step tiep theo dc ready
		// thieu time > timeout -->Fail
		sleepInSecond(3);

		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());
	}

	
	@Test
	public void TC_02_Enough() {	
		driver.get("http://the-internet.herokuapp.app/dynamic_loading/2");
				
		// du thoi gian
		// passed
		sleepInSecond(6);

		driver.findElement(startButton).click();

		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());
	}
	
	
	@Test
	public void TC_03_More() {	
		driver.get("http://the-internet.herokuapp.app/dynamic_loading/2");
		
		//Du mat 4s
		sleepInSecond(10);
		
		driver.findElement(startButton).click();

		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());

	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	// Static wait / Dead wait / Hard wait
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
