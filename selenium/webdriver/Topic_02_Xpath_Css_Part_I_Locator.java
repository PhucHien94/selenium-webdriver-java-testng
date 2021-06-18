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
		sleepInSecond(3);
	}
	
	@Test
	public void TC_02_Class() {
		// Refresh
		driver.navigate().refresh();
		driver.findElement(By.className("search-box-text ")).sendKeys("Macbook");
		driver.findElement(By.className("search-box-text ")).click();
		sleepInSecond(3);
	}
	
	@Test
	public void TC_03_Name5() {
		
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		driver.findElement(By.name("Email")).sendKeys("phuchien94@gmail.com");
		sleepInSecond(3);
		
		driver.findElement(By.name("Newsletter")).click();
		sleepInSecond(3);		
	}
	
	
	@Test
	public void TC_04_Tagname() {
		System.out.println("Sum link = " + driver.findElements(By.tagName("a")).size());
		System.out.println("Sum link = " + driver.findElements(By.tagName("input")).size());
		sleepInSecond(3);
	}
	
	@Test
	public void TC_05_LinkText() {
		driver.findElement(By.linkText("Log in")).click();
		sleepInSecond(3);
	}
	
	@Test
	public void TC_06_Partial_LinkText() {
		driver.findElement(By.partialLinkText("Recently viewed products")).click();
		sleepInSecond(3);
		
		driver.findElement(By.partialLinkText("viewed products")).click();
		sleepInSecond(3);
		
		driver.findElement(By.partialLinkText("Recently viewed")).click();
		sleepInSecond(3);
	}
	
	
	@Test
	public void TC_07_Css() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		driver.findElement(By.cssSelector("input[id='FirstName']")).sendKeys("AutomationFC");
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("input[class='search-box-text ui-autocomplete-input']")).sendKeys("Macbook");
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("phuchien94@gmail.com");
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("a[href*=login]")).click();
		sleepInSecond(3);
	}
	
	
	@Test
	public void TC_08_Xpath() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("AutomationFC");
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//input[contains(@class,'search-box-text')]")).sendKeys("AutomationFC");
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys("phuchien94@gmail.com");
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys("phuchien94@gmail.com");
		sleepInSecond(3);
 		
		driver.findElement(By.xpath("//a[(text()='Log in')]")).click();
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//a[contains(text(),'Recently viewed')]")).click();
		sleepInSecond(3);		
	}
		
	@AfterClass
	public void afterClass() {
		driver.quit();
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
