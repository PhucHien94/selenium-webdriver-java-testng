package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Windows {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	//@Test
	public void TC_01_Github() {		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//get ra tab / window id taij tab ddang active
		String parentTabID = driver.getWindowHandle();
		
		//Click to google
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		
		//switch to  GoogleTab
		switchToWindowByID(parentTabID);
		
		//get ra tab / window id tab dang active
		String googleTabID = driver.getWindowHandle();
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Test Selenium");
		
		//switch to parentTab
		switchToWindowByID(googleTabID);
		
		//click to facebook
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();

		switchToWindowByTabTitle("Google");
		//switchToWindowByTabTitle("SELENIUM WEBDRIVER FORM DEMO");
		//switchToWindowByTabTitle("Facebookgjhgjg");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("aaa");

	}
	
	//@Test
	public void TC_02_Kyna() {		
		driver.get("https://kyna.vn/");
		
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();
		
		switchToWindowByID(parentID);
		
		switchToWindowByTabTitle("Kyna.vn - Học online cùng chuyên gia");
		
		driver.findElement(By.xpath("//a[text()='Thông tin hữu ích']")).click();
		
		switchToWindowByTabTitle("Tổng hợp bài viết hay, thông tin hữu ích - Kyna.vn");
		
		driver.findElement(By.xpath("//img[@class='logo-image']")).click();
		
		closeTabWithouParent(parentID);
	}
	
	
	@Test
	public void TC_03_Guru() {	
		driver.get("http://live.demoguru99.com");
				
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		String parentId = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product IPhone has been added to comparison list.']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());

		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		
		switchToWindowByID(parentId);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/catalog/product_compare/index/");
	}
	
	//@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void switchToWindowByID(String windowID) {
		//get het id window dang co
		Set<String> allWindowIDs = driver.getWindowHandles();	
		for(String id : allWindowIDs) {
			if(!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}
	
	public void switchToWindowByTabTitle(String tabTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if(actualTitle.equals(tabTitle)) {
				break;
			}
		}
	}
	
	public void closeTabWithouParent(String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();	
		for(String id : allWindowIDs) {
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
		}
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
