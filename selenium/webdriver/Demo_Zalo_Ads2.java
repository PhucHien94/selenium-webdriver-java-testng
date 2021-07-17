package webdriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Demo_Zalo_Ads2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	String[] selectValueExpect = { "25-34", "35-44", "55-64" };
	List<String> selectedValueSave = new ArrayList<String>();

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
		sleepInSecond(1);
		Assert.assertTrue(checkSelectedItem(selectValueExpect));

	}

	@Test
	public void TC_03_Save() {
		//Luu tuoi da chon
		List<WebElement> allSelectedItem = driver.findElements(
				By.xpath("//li[@class='pure-checkbox selected-item']//div[@class='squaredFour have-label']//label"));
		for (WebElement childSelectedElement : allSelectedItem) {
			String childSeletedItemText = childSelectedElement.getText();
			selectedValueSave.add(childSeletedItemText);	
		}
		//Nhan luu
		clickToElementByJS(By.xpath("//div[@class='c-btn']"));
		clickToElementByJS(By.xpath("//a[@class='btn btn-default mg-right-10'][contains(text(),'Lưu')]"));
		sleepInSecond(2);
		clickToElementByJS(By.xpath("//button[contains(text(),'Đồng ý')]"));

		sleepInSecond(1);
		Assert.assertEquals("https://ads.zalo.me/client/ads/detail/15704693", driver.getCurrentUrl());
		
		//Kiểm tra xem tuổi đã chọn có trùng với những thằng nhấn trước khi lưu hay không
		By ageString = By.xpath("//span[contains(text(),'Độ tuổi:')]/following-sibling::div//span");
		Assert.assertTrue(splitString(ageString,selectedValueSave));
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
		// driver.findElement(By.xpath("//div[@class='c-btn']")).click();
		List<WebElement> allSelectedItem = driver.findElements(
				By.xpath("//li[@class='pure-checkbox selected-item']//div[@class='squaredFour have-label']//label"));
		int sizeOfSelectedItem = allSelectedItem.size();
		int sizeOfExpectedItem = expectedValueItem.length;
		boolean status = false;
		//Nếu item dã select bằng số lượng item mong muón thì check tiếp
		if (sizeOfSelectedItem == sizeOfExpectedItem) {
			for (WebElement childSelectedElement : allSelectedItem) {
				boolean n = false;
				String childSeletedItemText = childSelectedElement.getText();
				for (int i = 0; i < sizeOfExpectedItem; i++) {
					//nêu thăng đang select trùng với giá trị mong đợi thì thoát khỏi vòng lặp, check thằng đang select tiếp theo
					if (childSeletedItemText.equals(expectedValueItem[i])) {
						n = true;
						break;
					}
					//nêu ko trùng thì check đến list expect xem thằng select có trùng với bất kỳ item nào trong expect hay k
					n = false;					
				}
				//cứ chạy hết 1 item thì kiểm tra status 1 lần, nếu là false tức là thằng đang chọn ko trùng với expected >> N/G
				status = n;
				if (status == false) {
					break;
				} else
					//nếu đã map được 1 item thì compare ỉtem tiếp theo trong list select
					continue;

			}

		}
		return status;
	}

	public void clickToElementByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].click()", element);
	}
	
	public boolean splitString(By age,List<String> expected) {
		String selectedAge  = driver.findElement(age).getText();
		
		String[] splitAgeText = selectedAge.split(",");
		List<String> displayedItem = Arrays.asList(splitAgeText);
		
		int sizeOfAdsDetailItems = displayedItem.size();
		int sizeOfExpectedValue = expected.size();
		
		boolean status = false;
		//Nếu item dã select bằng số lượng item mong muón thì check tiếp
		if (sizeOfAdsDetailItems == sizeOfExpectedValue) {
			for (String childSelectedElement : displayedItem) {				
				boolean n = false;				
				for (int i = 0; i < sizeOfExpectedValue; i++) {
					if (childSelectedElement.trim().equals(expected.get(i))) {
						n = true;
						break;
					}
					n = false;					
				}
				status = n;
				if (status == false) {
					break;
				} else
					continue;
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
