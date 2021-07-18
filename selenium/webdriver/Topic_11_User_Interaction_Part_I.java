package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_User_Interaction_Part_I {
	WebDriver driver;
	Actions action;

	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();	
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	//@Test
	public void TC_01_Hover_Mouse_I() {	
		driver.get("https://tiki.vn/");
		
		action.moveToElement(driver.findElement(By.cssSelector(".profile-icon"))).perform();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='tel']")).isDisplayed());
	}
	
	//@Test
	public void TC_01_Hover_Mouse_II() {	
		driver.get("https://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");
	}
	
	@Test
	public void TC_02_ClickAndHold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> rectangle = driver.findElements(By.cssSelector("#selectable>li"));
		
		//Click and hold element đầu tiên > Hover chuột đến element đích > Nhả chuột trái ra
		action.clickAndHold(rectangle.get(0)).moveToElement(rectangle.get(3)).release().perform();
		Assert.assertEquals(driver.findElements(By.cssSelector("#selectable>li.ui-selected")).size(), 4);
		
	}
	
	@Test
	public void TC_02_ClickAndHold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> rectangle = driver.findElements(By.cssSelector("#selectable>li"));
		
		//Nhấn Ctrl xuống
		action.keyDown(Keys.CONTROL).perform();
		
		//Chọn element
		action.click(rectangle.get(0)).click(rectangle.get(2)).click(rectangle.get(5)).click(rectangle.get(10)).perform();
		//Nhả phím Ctrl
		action.keyUp(Keys.CONTROL).perform();

		
		Assert.assertEquals(driver.findElements(By.cssSelector("#selectable>li.ui-selected")).size(), 4);

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
