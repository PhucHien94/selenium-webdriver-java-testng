package webdriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown_Excercise {
	WebDriver driver;
	String email, password, firstName, lastName, day, month, year;
	Select select;
	List<String> expectedItemText;
	JavascriptExecutor jsExecutor;

	By gendermaleTextbox = By.id("gender-male");
	By firstNameTextbox = By.name("FirstName");
	By lastNameTextbox = By.name("LastName");
	By emailTextbox = By.name("Email");
	By passwordTextbox = By.name("Password");
	By confirmPasswordTextbox = By.name("ConfirmPassword");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		expectedItemText = new ArrayList<String>(Arrays.asList("Automation", "Mobile", "Desktop"));
		// data register
		email = "test" + generateEmail();
		password = "123456";
		firstName = "HienTest";
		lastName = "LastNameTest";
		day = "1";
		month = "May";
		year = "1980";

	}

	@Test
	public void TC_01_TC02() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		select = new Select(driver.findElement(By.id("job1")));
		Assert.assertFalse(select.isMultiple());

		select.selectByVisibleText("Mobile Testing");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");

		select.selectByValue("manual");
		Assert.assertEquals(select.getFirstSelectedOption().getAttribute("value"), "manual");

		select.selectByIndex(9);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");

		Assert.assertEquals(select.getOptions().size(), 10);

		select = new Select(driver.findElement(By.id("job2")));
		Assert.assertTrue(select.isMultiple());

		select.selectByVisibleText("Automation");
		select.selectByVisibleText("Mobile");
		select.selectByVisibleText("Desktop");

		List<WebElement> multipleItem = select.getAllSelectedOptions();
		List<String> actualItemText = new ArrayList<>();
		for (WebElement item : multipleItem) {
			actualItemText.add(item.getText());
		}
		Assert.assertEquals(expectedItemText, actualItemText);

		select.deselectAll();
		Assert.assertEquals(select.getAllSelectedOptions().size(), 0);

	}

	@Test
	public void TC_02_TC03() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//a[text()='Register']")).click();

		driver.findElement(gendermaleTextbox).click();
		driver.findElement(firstNameTextbox).sendKeys(firstName);
		driver.findElement(lastNameTextbox).sendKeys(lastName);

		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(day);
		Assert.assertEquals(select.getOptions().size(), 32);

		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getOptions().size(), 13);

		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getOptions().size(), 112);

		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(confirmPasswordTextbox).sendKeys(password);

		// driver.findElement(By.id("register-button")).click();
		ClickByJS(By.id("register-button"));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),
				"Your registration completed");

		driver.findElement(By.cssSelector(".ico-account")).click();
		sleepInSecond(3);

		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);

		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);

		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@mail.vn";
	}

	public void ClickByJS(By by) {
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
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
