package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Wait_I_Element_Status {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {		
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");

		driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		expliciWait = new WebDriverWait(driver, 15);
	}
	
	//@Test
	public void TC_01_Visible_Displayed() {
		driver.get("https://facebook.com/");
		
		//Wait cho 1 element hiển thị trong khoảng thời gian 15s
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
	
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());

	}
	
	//@Test
	public void TC_02_Invisible_Undisplayed() {
		driver.get("https://facebook.com/");
		
		//Wait cho button tao tai khoan co the click
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Tạo tài khoản mới']")));
		
		//action
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		
		//ko co tren UI nhung van co trong DOM
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")));
		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
		
		//ko co tren UI và khong co trong DOM
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='reg']")));

	}
	
	@Test
	public void TC_03_Presence() {
		driver.get("https://facebook.com/");
		
		//có hiên thị ở UI, có trong DOM --> Pass
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']"))); 
		
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Tạo tài khoản mới']")));
		
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();


		//ko hiển thị trên UI nhưng có hiên thị trong DOM -->Pass
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();

		//ko hiển thị trên UI và ko có trong DOM --> False
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//form[@id='reg']")));

	}
	
	@Test
	public void TC_04_Staleness() {
		driver.get("https://facebook.com/");
		
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Tạo tài khoản mới']")));
		
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='reg']")));
		WebElement registerForm = driver.findElement(By.xpath("//form[@id='reg']"));

		
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")));
		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
		
		//Wait register form staleness
		expliciWait.until(ExpectedConditions.stalenessOf(registerForm));
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
