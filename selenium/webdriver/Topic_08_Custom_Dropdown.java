package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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


public class Topic_08_Custom_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	// Wait
	WebDriverWait explicitWait;

	// Inject 1 javascript code
	JavascriptExecutor jsExecutor;

	String[] firstMonth = { "January", "May", "October" };
	String[] secondMonth = { "January", "May", "October", "December" };

	@BeforeClass
	public void beforeClass() {
		// driver = new FirefoxDriver();

		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();

		explicitWait = new WebDriverWait(driver, 15);

		// Ép kiểu tường minh
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	// @Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemInDropdown("//span[@id='number-button']/span[contains(@class,'ui-selectmenu-icon')]",
				"//ul[@id='number-menu']//div", "5");
		sleepInSecond(2);
		Assert.assertTrue(driver
				.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']"))
				.isDisplayed());

		selectItemInDropdown("//span[@id='number-button']/span[contains(@class,'ui-selectmenu-icon')]",
				"//ul[@id='number-menu']//div", "15");
		sleepInSecond(2);
		Assert.assertTrue(driver
				.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='15']"))
				.isDisplayed());

		selectItemInDropdown("//span[@id='number-button']/span[contains(@class,'ui-selectmenu-icon')]",
				"//ul[@id='number-menu']//div", "3");
		sleepInSecond(2);
		Assert.assertTrue(driver
				.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='3']"))
				.isDisplayed());

	}

	// @Test
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectItemInDropdown("//i[@class='dropdown icon']", "//div[@role='option']/span", "Stevie Feliciano");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Stevie Feliciano']"))
				.isDisplayed());

	}

	// @Test
	public void TC_03_VueJS_01() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		selectItemInDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Second Option");
		sleepInSecond(3);
		Assert.assertTrue(
				driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]"))
						.isDisplayed());

	}

	// @Test
	public void TC_04_Angular_1() {
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

		selectItemInDropdown("//span[@aria-owns='games_options']", "//li[@class='e-list-item ']", "Hockey");
		sleepInSecond(2);
		// Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text'
		// and text()='5']")).isDisplayed());

	}

	// @Test
	public void TC_04_Angular_2() {
		driver.get("https://valor-software.com/ng2-select");

		selectItemInDropdown("//tab[@heading='Single']//i[@class='caret pull-right']",
				"//tab[@heading='Single']//a[@class='dropdown-item']/div", "Stockholm");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath(
				"//h3[text()='Select a single city']/following-sibling::ng-select//span[contains(@class,'ui-select-allow-clear')]"))
				.getText(), "Stockholm");

	}

	// @Test
	public void TC_05_Editable_01() {
		driver.get("https://valor-software.com/ng2-select");

		enterAndSelectItemInCustomDropdown("//tab[@heading='Single']//i[@class='caret pull-right']",
				"//tab[@heading='Single']//input", "//tab[@heading='Single']//a[@class='dropdown-item']/div",
				"Stockholm");
		sleepInSecond(3);

	}

	// @Test
	public void TC_05_Editable_02() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		enterAndTabToCustomDropdown("//input[@class='search']", "Algeria");
		sleepInSecond(3);

	}

	@Test
	public void TC_06_Multiple() {
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");

		selectMultiItemInDropdown("(//button[@class='ms-choice'])[1]",
				"//div[@class='form-group row'][2]//div[@class='ms-drop bottom']//li//span", firstMonth);
		sleepInSecond(3);
		Assert.assertTrue(areItemSelected(firstMonth));

		driver.navigate().refresh();

		selectMultiItemInDropdown("(//button[@class='ms-choice'])[1]",
				"//div[@class='form-group row'][2]//div[@class='ms-drop bottom']//li//span", secondMonth);
		sleepInSecond(3);
		Assert.assertTrue(areItemSelected(secondMonth));	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void selectItemInDropdown(String parentXpath, String childXpath, String expectedText) {
		// xổ hết tất cả item trong dropdown ra > parent element
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(5);

		// chờ tất cả item được load ra thành công
		// Lấy tất cả element lưu vào list element
		List<WebElement> allChildElement = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

		// Duyệt qua từng item
		// Get text của item đó ra và kiểm tra xem nó có bằng vs item text mình mong
		// muốn hay không
		// Item cần chọn nó hiên thị > Click vào item đó luôn
		// Item cần chọn ko hiển thị > Scroll đến item đó > Click vào item
		for (WebElement item : allChildElement) {
			if (item.getText().trim().equals(expectedText)) {
				// if(!item.isDisplayed()) {
				jsExecutor.executeScript("arguments[0].scrollIntoview(true);", item);
				sleepInSecond(1);
				// }
				item.click();
				break;
			}
		}

	}

	public void enterAndSelectItemInCustomDropdown(String parentXpath, String textboxXpath, String childXpath,
			String expectedText) {
		// xổ hết tất cả item trong dropdown ra > parent element

		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(1);

		driver.findElement(By.xpath(textboxXpath)).sendKeys(expectedText);
		sleepInSecond(1);

		// chờ tất cả item được load ra thành công
		// Lấy tất cả element lưu vào list element
		List<WebElement> allChildElement = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

		// Duyệt qua từng item
		// Get text của item đó ra và kiểm tra xem nó có bằng vs item text mình mong
		// muốn hay không
		// Item cần chọn nó hiên thị > Click vào item đó luôn
		// Item cần chọn ko hiển thị > Scroll đến item đó > Click vào item
		for (WebElement item : allChildElement) {
			if (item.getText().trim().equals(expectedText)) {
				// if(!item.isDisplayed()) {
				jsExecutor.executeScript("arguments[0].scrollIntoview(true);", item);
				sleepInSecond(1);
				// }
				item.click();
				break;
			}
		}

	}

	public void enterAndTabToCustomDropdown(String textboxXpath, String expectedText) {

		driver.findElement(By.xpath(textboxXpath)).sendKeys(expectedText);
		sleepInSecond(1);

		driver.findElement(By.xpath(textboxXpath)).sendKeys(Keys.TAB);

	}

	public void selectMultiItemInDropdown(String parentXpath, String childXpath, String[] expectedValueItem) {
		// 1: click vào cái dropdown cho nó xổ hết tất cả các giá trị ra
		driver.findElement(By.xpath(parentXpath)).click();

		// 2: chờ cho tất cả các giá trị trong dropdown được load ra thành công
		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

		// Duyệt qa hết tất cả các phần tử cho đến khi thỏa mãn điều kiện
		for (WebElement childElement : allItems) {
			// "January", "April", "July"
			for (String item : expectedValueItem) {
				if (childElement.getText().equals(item)) {
					// 3: scroll đến item cần chọn (nếu như item cần chọn có thể nhìn thấy thì ko
					// cần scroll)
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
					sleepInSecond(1);

					// 4: click vào item cần chọn
					childElement.click();
					sleepInSecond(1);

					List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
					System.out.println("Item selected = " + itemSelected.size());
					if (expectedValueItem.length == itemSelected.size()) {
						break;
					}
				}
			}
		}
	}

	public boolean areItemSelected(String[] months) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberItemSelected = itemSelected.size();

		String allItemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
		System.out.println("Text da chon = " + allItemSelectedText);
		// "January", "April", "July"

		// month: "January", "April", "July"
		if (numberItemSelected <= 3 && numberItemSelected > 0) {
			System.out.println(">0 & <3  =>"+numberItemSelected);
			boolean status = true;
			for (String item : months) {
				if (!allItemSelectedText.contains(item)) {
					status = false;
					return status;
				}
			}
			return status;
		} else if (numberItemSelected == 12) {
			System.out.println("=12  =>"+numberItemSelected);

			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']"))
					.isDisplayed();
		} else if (numberItemSelected > 3 && numberItemSelected < 12) {
			System.out.println(">3 & <12  =>"+numberItemSelected);
			return driver
					.findElement(By.xpath(
							"//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']"))
					.isDisplayed();
		} else {
			return false;
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
