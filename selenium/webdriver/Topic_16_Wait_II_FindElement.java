package webdriver;


import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.sound.midi.Soundbank;

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

public class Topic_16_Wait_II_FindElement {
	WebDriver driver;
	//WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {		
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void TC_01_FindElement() {
		driver.get("https://facebook.com/");
		
		// 1- có duy nhất 1 element
		// không cần chờ hết timeout của implicit
		// tương tác lên element luôn
		driver.findElement(By.id("email")).sendKeys("automationfc.vn@gmail.com");
		
		// 2- không có element nào hết
		// chờ hết timeout của implicit
		// trong khoảng thời gian chờ cứ mỗi nửa giây sẽ tìm lại 1 lần
		// khi nào hết timeout của implicit thì sẽ đánh fail testcase và throw exception: NoSuchElementException
		driver.findElement(By.id("address")).sendKeys("VietNam");

		// 3- nhiều hơn 1 element
		// không cần chờ hết timeout của implicit
		// nó sẽ lấy element đầu tiên để tương tác
		// ko quan tâm tìm dc bao nhiêu matching note

	}
	
	@Test
	public void TC_02_FindElements() {
		driver.navigate().refresh();
		
		// 1- có duy nhất 1 element
		// không cần chờ hết timeout của implicit
		// tương tác lên element luôn
		driver.findElements(By.id("email")).get(0).sendKeys("automationfc.vn@gmail.com");
		System.out.println(driver.findElements(By.id("email")).size());

		// 2- không có element nào hết
		// chờ hết timeout của implicit
		// trong khoảng thời gian chờ cứ mỗi nửa giây sẽ tìm lại 1 lần
		// khi nào hết timeout của implicit thì ko đánh fail testcase
		// trả về 1 list empty [rỗng, không có phần từ (web element) nào hết]
		// chuyển qua step tiếp theo
		System.out.println(driver.findElements(By.id("address")).size()); 
		
		// 3- Nhiều hơn 1 element
		// không cần chờ hết timeout của implicit
		// lưu tất cả các element vào trong list
		List<WebElement> footer = driver.findElements(By.cssSelector("ul.pageFooterLinkList a"));
		for (WebElement link : footer) {
			System.out.println(link.getText());
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
}
