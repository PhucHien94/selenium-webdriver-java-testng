package webdriver;


import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.webkit.ContextMenu.ShowContext;


public class Topic_16_Wait_VI_Mixing {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	
	@BeforeClass
	public void beforeClass() {		
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();	
	}
	
	@Test
	public void TC_01_Element_Found_Implicit_Explicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver,15);	
		
		driver.get("https://www.facebook.com/");
		
		showDateTimeNow("Start explicit");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		showDateTimeNow("End explicit - start implicit");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("autotionfc.vn@gmail.com");
		showDateTimeNow("End implicit");
	}

	@Test
	public void TC_02_Element_Not_Found_Implicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// chờ hết timeout của implicit
		// trong khoảng thời gian chờ cứ mỗi nửa giây sẽ tìm lại 1 lần
		// khi nào hết timeout của implicit thì sẽ đánh fail testcase và throw exception: NoSuchElementExcep
		
		driver.get("https://www.facebook.com/");
		
		showDateTimeNow("Start implicit");
		try {
			driver.findElement(By.xpath("//input[@id='emailllll']")).sendKeys("autotionfc.vn@gmail.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
		showDateTimeNow("End implicit");
	}
	
	@Test
	public void TC_03_Element_Not_Found_Implicit_Explicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver,15);
		
		driver.get("https://www.facebook.com/");
		
		//findElement truoc
		//apply dieu kien
		//implicit se anh huong cac step co dung explicite
		showDateTimeNow("Start explicit");
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='emailllll']")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		showDateTimeNow("End explicit");
		
		showDateTimeNow("Start implicit");
		try {
			driver.findElement(By.xpath("//input[@id='emailllll']")).sendKeys("autotionfc.vn@gmail.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
		showDateTimeNow("End implicit");
	}
	
	@Test
	public void TC_04_Element_Not_Found_Explicit_Param_By() {
		explicitWait = new WebDriverWait(driver,15);
		
		driver.get("https://www.facebook.com/");
		
		showDateTimeNow("Start explicit (By)");
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='emailllll']")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		showDateTimeNow("End explicit (By)");
	}
	
	@Test
	public void TC_05_Element_Not_Found_Explicit_Param_WebElement() {
		explicitWait = new WebDriverWait(driver,15);
		
		driver.get("https://www.facebook.com/");
		
		showDateTimeNow("Start explicit (Element)");
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='emailllll']"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		showDateTimeNow("End explicit (Element)");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void showDateTimeNow(String status) {
		Date date = new Date();
		System.out.println("-------------" + status + " : " + date.toString() + "-----------------");
	}
}
