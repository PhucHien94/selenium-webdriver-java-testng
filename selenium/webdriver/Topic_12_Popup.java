package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	// @Test
	public void TC_01_ZingPoll() {
		driver.get("https://www.zingpoll.com/");

		By signInPopup = By.cssSelector(".modal_dialog_custom");

		driver.findElement(By.xpath("//a[@id='Loginform']")).click();

		Assert.assertTrue(driver.findElement(signInPopup).isDisplayed());

		driver.findElement(By.cssSelector(".modal_dialog_custom .close")).click();
		sleepInSecond(2);

		Assert.assertFalse(driver.findElement(signInPopup).isDisplayed());

	}

	//@Test
	public void TC_02_Shopee() {
		driver.get("https://shopee.vn/");

		By homePopup = By.xpath("//img[@alt='home_popup_banner']");

		Assert.assertTrue(isElementDisplayed_2(homePopup));

		driver.findElement(By.cssSelector(".shopee-popup__close-btn")).click();
		sleepInSecond(2);

		Assert.assertFalse(isElementDisplayed_2(homePopup));

	}

	@Test
	public void TC_03_Random_Popup_In_DOM() {
		driver.get("https://blog.testproject.io/");
		
		if(isElementDisplayed_2(By.cssSelector(".mailch-wrap"))) {
			driver.findElement(By.cssSelector("close-mailch")).click();
			sleepInSecond(2);
		}
	
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

	public boolean isElementDisplayed_1(By by) {
		try {
			// Nó sẽ tìm element trong vòng 10s
			// Khi nào hết 10s mới throw exception
			return driver.findElement(by).isDisplayed();
		} catch (Exception e) {
			// catch bắt exception lại
			return false;
		}

	}

	public boolean isElementDisplayed_2(By by) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		List<WebElement> elements = driver.findElements(by);
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		if (elements.size() == 0) {
			return false;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return false;
		} else {
			return true;
		}

	}
}
