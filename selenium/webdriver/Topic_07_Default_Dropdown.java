package webdriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown {
	WebDriver driver;
	String email, password, firstName, lastName, updateEmail, updateFirstName, updateLastName, day, month, year;
	Select select;
	JavascriptExecutor jsExecutor;
	List<String> expectedItemText;

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
		driver.get("https://demo.nopcommerce.com");

		// data register
		email = "test" + generateEmail();
		password = "123456";
		firstName = "HienTest";
		lastName = "LastNameTest";
		day = "10";
		month = "September";
		year = "2000";
		expectedItemText = new ArrayList<String>(Arrays.asList("Month", "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November", "December"));
	}

	//@Test
	public void TC_01_Register() {
		driver.findElement(By.xpath("//a[text()='Register']")).click();

		driver.findElement(firstNameTextbox).sendKeys(firstName);
		driver.findElement(lastNameTextbox).sendKeys(lastName);

		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		// cac ham cua select
		// chon item trong dropdown
		select.selectByVisibleText(day);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);

		// Kiem tra da chon dung item hay chua
		// Assert.assertEquals(select.getFirstSelectedOption().getText(), "10");

		// verify so luong item trong dropdown
		// Assert.assertEquals(select.getOptions().size(), 32);

		// Verify dropdown chi chon 1 item 1 luc
		// Assert.assertFalse(select.isMultiple());

		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);

		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);


		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(confirmPasswordTextbox).sendKeys(password);
	
		/*
		 * sleepInSecond(3);
		 * 
		 * driver.findElement(By.id("register-button")).click(); sleepInSecond(3);
		 */
		ClickByJS(By.id("register-button"));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),"Your registration completed");
		
		driver.findElement(By.cssSelector(".ico-account")).click();
		sleepInSecond(3);

		
		Assert.assertEquals(driver.findElement(firstNameTextbox).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(lastNameTextbox).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(emailTextbox).getAttribute("value"), email);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);

		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
	}

	@Test
	public void TC_02_() {
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		List<WebElement> allItems = select.getOptions();
		List<String> actualItemText = new ArrayList<>();
		
		//Duyet qua tat ca item co trong list element
		for (WebElement item : allItems) {
			actualItemText.add(item.getText());
		}
		Assert.assertEquals(expectedItemText, actualItemText);

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
