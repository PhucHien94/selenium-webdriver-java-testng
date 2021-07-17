package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.javafx.scene.web.Debugger;

import jdk.nashorn.internal.runtime.Debug;

public class Demo_Zalo_Ads2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	String[] selectValueExpect = { "25-34", "35-44", "55-64"};

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");

		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 10);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	// Login
	@Test
	public void TC_01_Login_Empty_Email_Password() {

		driver.get("https://ads.zalo.me/client/ads/select-campaign");
		sleepInSecond(2);
		driver.findElement(By.xpath("//div[@class='tabs animated fadeIn']/ul/li[2]")).click();
		sleepInSecond(1);
		driver.findElement(By.id("input-phone")).clear();
		driver.findElement(By.id("input-phone")).sendKeys("0932581342");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("test123456");
		sleepInSecond(1);

		driver.findElement(By.xpath("//a[@class='btn btn--m block first']")).click();
		sleepInSecond(2);

	}

	@Test
	public void TC_02_Ads_Create() {
		driver.get("https://ads.zalo.me/client/ads/create/15704693");
		clickToElement(selectValueExpect);
		sleepInSecond(3);
		Assert.assertTrue(checkSelectedItem(selectValueExpect));

	}

	// @AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void clickToElement(String[] expectedValueItem) {
		driver.findElement(By.xpath("//div[@class='c-btn']")).click();

		List<WebElement> allItems = driver
				.findElements(By.xpath("//div[@class='cuppa-dropdown']//div[@class='squaredFour have-label']//label"));
		// duyệt từng item trong dropdown
		for (WebElement childElement : allItems) {
			// duyệt từng giá trị mong muốn
			for (String item : expectedValueItem) {
				// lấy xpath của thằng input (thằng có thể check vào checkbox)
				By inputCheckboxXpath = By.xpath(
						"//div[@class='cuppa-dropdown']//div[@class='squaredFour have-label']//label[contains(text(),'"
								+ childElement.getText() + "')]/preceding-sibling::input");
				WebElement inputCheckboxXpathElement = driver.findElement(inputCheckboxXpath);
				// nếu label của thằng đang check bằng với giá trị mong đợi thì thực hiện check
				if (childElement.getText().equals(item)) {
					// nếu checkbox chưa check thì check vào và thoát khỏi việc kiểm tra giá trị
					// mong đợi tiếp theo
					if (!inputCheckboxXpathElement.isSelected()) {
						jsExecutor.executeScript("arguments[0].click()", inputCheckboxXpathElement);
						sleepInSecond(1);
						break;
					}
					// nếu checkbox đã check rồi thì thóat khỏi việc kiểm tra giá trị mong đợi tiếp
					// theo
					break;
				} else { // nếu text của thằng đang check không trùng với giá trị mong đợi thì thực hiện
							// uncheck
					// Nếu nó đang check thì check vào để uncheck, còn nếu uncheck rồi thì không làm
					// gì cả
					if (inputCheckboxXpathElement.isSelected()) {
						jsExecutor.executeScript("arguments[0].click()", inputCheckboxXpathElement);
						sleepInSecond(1);
					}
				}
			}
			// lay element trc input làm chuân > vòng lặp cho mỗi input > nêu input >
			// lable.gettext bằng expectted > check , còn k thì bỏ check
		}
	}

	public boolean checkSelectedItem(String[] expectedValueItem) {
		//driver.findElement(By.xpath("//div[@class='c-btn']")).click();
		List<WebElement> allSelectedItem = driver.findElements(
				By.xpath("//li[@class='pure-checkbox selected-item']//div[@class='squaredFour have-label']//label"));
		int a = allSelectedItem.size();
		int b = expectedValueItem.length;
		boolean status = false;
		if (a == b) {
			for (WebElement childSelectedElement : allSelectedItem) {
				boolean n = false;
				String childSeletedItemText = childSelectedElement.getText();
				for (int i = 0; i < b; i++) {
					if (childSeletedItemText.equals(expectedValueItem[i])) {
						n = true;
						break;
					}
					n = false;
					//continue;					
				}
				status = n;
				if(status == false) {
					break;
				}else continue;
				
			}

		}
		return status;
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
