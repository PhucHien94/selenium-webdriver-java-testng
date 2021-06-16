package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Part_I_Locator {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//Mở AUT,SUT (System,Application under testing)
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
	}

	@Test
	public void TC_01_ID() {
		//Nhập giá trị vào firstname textbox
		driver.findElement(By.id("FirstName")).sendKeys("AutomationTest");
		
		//Click vào male radio button
		driver.findElement(By.id("gender-male")).click();
	}
	
	@Test
	public void TC_02_Class() {
		// Refresh
		driver.navigate().refresh();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}


}
