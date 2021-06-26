package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Demo_Zalo_Ads {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Login_Empty_Email_Password() {

		driver.get("https://ads.zalo.me/client/ads/select-campaign");
		driver.findElement(By.xpath("//div[@class='tabs animated fadeIn']/ul/li[2]")).click();
		driver.findElement(By.id("input-phone")).sendKeys("0932581342");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("test123456");
		driver.findElement(By.xpath("//a[@class='btn btn--m block first']")).click();
		//driver.findElement(By.xpath("//div[@class='item-campaign']/div[@class='ins']//h3[text()='Quảng cáo website']/following-sibling::p[last()]/a[@class='btn btn-primary btn-begin']")).click();
		driver.findElement(By.xpath("//h3[contains(text(),'Quảng cáo website')]//parent::div[@class='ins']//parent::a[.=' Bắt đầu ']")).click();
		
		sleepInSecond(10);
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
