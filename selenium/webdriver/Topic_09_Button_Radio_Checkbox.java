package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver;
	boolean status;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	// @Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");

		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();

		// Button disabled
		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("Status of button : " + status);
		Assert.assertFalse(status);

		driver.findElement(By.cssSelector("#login_username")).sendKeys("0987555888");
		driver.findElement(By.cssSelector("#login_password")).sendKeys("123456");

		// Button Enable
		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("Status of button : " + status);
		Assert.assertTrue(status);

		driver.navigate().refresh();

		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();

		// remove disable attribute of login button
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')",
				driver.findElement(By.cssSelector(".fhs-btn-login")));
		sleepInSecond(5);

		// Button Enable
		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("Status of button : " + status);
		Assert.assertTrue(status);

		driver.findElement(By.cssSelector(".fhs-btn-login")).click();

		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");

	}

	// @Test
	public void TC_02_Checkbox_Radio_Default() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

		// click vao checkbox de chon no
		checkToCheckboxOrRadio(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input"));
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input"))
				.isSelected());

		// click vao checkbox de bo chon no
		uncheckToCheckbox(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input"));
		sleepInSecond(2);

		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input"))
				.isSelected());

		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");

		// click vao radio de chon
		checkToCheckboxOrRadio(By.xpath("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::input"));
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::input"))
				.isSelected());
	}

	// @Test
	public void TC_03_Checkbox_Select_All() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

		// select all checkbox
		for (WebElement checkbox : checkboxes) {
			if (!checkbox.isSelected()) {
				checkbox.click();
				sleepInSecond(1);
			}
		}

		for (WebElement checkbox : checkboxes) {
			Assert.assertTrue(checkbox.isSelected());

		}
	}

	// @Test
	public void TC_04_Checkbox_Radio_Custom() {

		// Radio
		driver.get("https://material.angular.io/components/radio/examples");

		// The input bi an ko click duoc, chi verify dc
		// dung the span de click (hien thi) (nhung the nay chi chon ma ko the verify
		// duoc)
		/*
		 * By winterSpan = By.xpath("//span[contains(text(),' Winter')]");
		 * driver.findElement(winterSpan).click(); sleepInSecond(2);
		 */

		// dung span de click, dung input de verify
		By winterRadio = By.xpath("//input[@value='Winter']");

		// span de click, dung input de verify >>>> Phai co 2 locator cho 1 element
		// Maintain phai bao tri nhieu cho > Click bang javascript
		// Dung JS de lick va verify
		clickToElementByJS(winterRadio);

		Assert.assertTrue(driver.findElement(winterRadio).isSelected());

		// Checkbox
		driver.get("https://material.angular.io/components/checkbox/examples");

		By checkedRadio = By.xpath("//input[@id='mat-checkbox-1-input']");
		By indeterminateRadio = By.xpath("//input[@id='mat-checkbox-2-input']");

		clickToElementByJS(checkedRadio);
		clickToElementByJS(indeterminateRadio);

		// kiem tra da chon
		Assert.assertTrue(driver.findElement(checkedRadio).isSelected());
		Assert.assertTrue(driver.findElement(indeterminateRadio).isSelected());

		clickToElementByJS(checkedRadio);
		clickToElementByJS(indeterminateRadio);

		// kiem tra da bo chon
		Assert.assertFalse(driver.findElement(checkedRadio).isSelected());
		Assert.assertFalse(driver.findElement(indeterminateRadio).isSelected());
	}

	@Test
	public void TC_05_Checkbox_Radio_Custom() {
		driver.get(
				"https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		//kiem tra checkbox chua duoc chon
		Assert.assertTrue(driver.findElement(By.xpath("//div[@data-answer-value='Quảng Nam' and @aria-checked='false']/div[contains(@class,'exportInnerBox')]")).isDisplayed());
		
		driver.findElement(By.xpath("//div[@data-answer-value='Quảng Nam']/div[contains(@class,'exportInnerBox')]")).click();
		
		//kiem tra checkbox duoc chon
		Assert.assertTrue(driver.findElement(By.xpath("//div[@data-answer-value='Quảng Nam' and @aria-checked='true']/div[contains(@class,'exportInnerBox')]")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void clickToElementByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].click()", element);
	}

	public void checkToCheckboxOrRadio(By by) {
		WebElement checkbox = driver.findElement(by);
		if (!checkbox.isSelected()) {
			checkbox.click();
		}
	}

	public void uncheckToCheckbox(By by) {
		WebElement checkbox = driver.findElement(by);
		if (checkbox.isSelected()) {
			checkbox.click();
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
