package webdriver;


import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Upload_AutoIT_Robot {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	JavascriptExecutor jsExecutor;
	
	String imgUpload1 = projectPath + "\\uploadFiles\\2mb.jpg";
	String imgUpload2 = projectPath + "\\uploadFiles\\test1.jpg";


	@BeforeClass
	public void beforeClass() {		
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");

		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	//@Test
	public void TC_01_Sendkey_One_file() {	
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//Load file khong can bat Open Filr Dialog
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(imgUpload1);
		
		//kiem tra file load thanh cong
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='2mb.jpg']")).isDisplayed());
		
		//click start button
		driver.findElement(By.cssSelector("table .start")).click();
		
		//Kiem tra upload thanh cong
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='2mb.jpg']")).isDisplayed());

	}
	
	@Test
	public void TC_01_Sendkey_Multiple_file() {	
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//Load file khong can bat Open Filr Dialog
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(imgUpload1 + "\n" + imgUpload2);
		
		//kiem tra file load thanh cong
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='2mb.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='test1.jpg']")).isDisplayed());	
		
		//click start button
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table .start"));
		for (WebElement startButton : startButtons) {
			startButton.click();
			sleepInSecond(1);
		}
		
		//Kiem tra upload thanh cong
		//Assert.assertTrue(driver.findElement(By.xpath("//a[text()='2mb.jpg']")).isDisplayed());

	}
		
	//@AfterClass
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
