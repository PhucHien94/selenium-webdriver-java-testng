package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Iframe_Windows {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//
//		driver = new FirefoxDriver();	
		
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	//@Test
	public void TC_01_() {		
		
		//Switch alert
		driver.switchTo().alert();
		
		//Switch frame/iframe
		//index
		driver.switchTo().frame(0);
		// name or id
		driver.switchTo().frame("");
		//xpath css
		driver.switchTo().frame(driver.findElement(By.xpath("")));
		
		//switch ve parent
		driver.switchTo().defaultContent();
		
	}
	
	@Test
	public void TC_01_IFrame() {	
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
		driver.switchTo().frame(3);
		
		String likeNumber = driver.findElement(By.xpath("//a[text()='Automation FC']/parent::div/following-sibling::div")).getText();
		System.out.println(likeNumber);
		
		driver.switchTo().defaultContent();
		System.out.println(driver.findElement(By.xpath("//h1[@class='post-title']")).getText());
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'https://docs.google.com/')]")));
	}
	
	@Test
	public void TC_02_Frame() {	
		driver.get("https://v1.hdfcbank.com/assets/popuppages/netbanking.htm");
		
		driver.findElement(By.xpath("//div[@class='full_container']/div[@class='container'][1]//a[text()='Continue to NetBanking']")).click();
		
		//login to login frame
		driver.switchTo().frame("login_page");
		driver.findElement(By.name("fldLoginUserId")).sendKeys("automation");
		driver.findElement(By.xpath("//a[contains(@onclick,'fLogon')]/img[@alt='continue']")).click();
		Assert.assertTrue(driver.findElement(By.name("fldPassword")).isDisplayed());
		
		//switch to parent page
		driver.switchTo().defaultContent();
		
		//switch to footer frame
		driver.switchTo().frame("footer");
		
		driver.findElement(By.xpath("//a[text()='Terms and Conditions']")).click();
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
