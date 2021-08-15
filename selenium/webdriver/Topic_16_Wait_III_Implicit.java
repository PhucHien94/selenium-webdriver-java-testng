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

public class Topic_16_Wait_III_Implicit {
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
	public void TC_01_Dont_Set_Implicit() {
		driver.get("http://the-internet.herokuapp.app/dynamic_loading/2");
		
		driver.findElement(startButton).click();

		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());
	}

	
	@Test
	public void TC_02_Set_3s() {	
		driver.get("http://the-internet.herokuapp.app/dynamic_loading/2");
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		driver.findElement(startButton).click();

		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());
	}
	
	
	@Test
	public void TC_03_Set_6s() {	
		driver.get("http://the-internet.herokuapp.app/dynamic_loading/2");
		
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		
		driver.findElement(startButton).click();

		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());

	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
