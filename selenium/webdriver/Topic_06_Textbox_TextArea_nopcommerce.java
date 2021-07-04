package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.commands.Click;


public class Topic_06_Textbox_TextArea_nopcommerce {
	WebDriver driver;
	String email, password, firstName, lastName, updateEmail, updateFirstName, updateLastName;

	By gendermaleTextbox = By.id("gender-male");
	By genderfemaleTextbox = By.id("gender-female");
	By firstNameTextbox = By.name("FirstName");
	By lastNameTextbox = By.name("LastName");
	By emailTextbox = By.name("Email");
	By passwordTextbox = By.name("Password");
	By confirmPasswordTextbox = By.name("ConfirmPassword");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com");

		// data register
		email = "test" + generateEmail();
		password = "123456";
		firstName = "HienTest";
		lastName = "LastNameTest";

		// data update
		updateEmail = "testupdate" + generateEmail();
		updateFirstName = "updatehienTest";
		updateLastName = "updatelastNameTest";
	}

	@Test
	public void TC_01_Register() {
		driver.findElement(By.xpath("//a[text()='Register']")).click();

		driver.findElement(gendermaleTextbox).click();
		driver.findElement(firstNameTextbox).sendKeys(firstName);
		driver.findElement(lastNameTextbox).sendKeys(lastName);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(confirmPasswordTextbox).sendKeys(password);

		driver.findElement(By.id("register-button")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),
				"Your registration completed");

		driver.findElement(By.cssSelector(".register-continue-button")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Log out']")).isDisplayed());

		driver.findElement(By.xpath("//a[text()='Log out']")).click();
	}

	@Test
	public void TC_02_Login() {
		driver.findElement(By.cssSelector(".ico-login")).click();

		driver.findElement(By.cssSelector("#Email")).sendKeys(email);
		driver.findElement(By.cssSelector("#Password")).sendKeys(password);

		driver.findElement(By.cssSelector(".login-button")).click();

		driver.findElement(By.cssSelector(".ico-account")).click();

		Assert.assertTrue(driver.findElement(gendermaleTextbox).isSelected());

		Assert.assertEquals(driver.findElement(firstNameTextbox).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(lastNameTextbox).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(emailTextbox).getAttribute("value"), email);
	}

	@Test
	public void TC_03_Update_Information() {
		driver.findElement(genderfemaleTextbox).click();
		driver.findElement(firstNameTextbox).clear();
		driver.findElement(firstNameTextbox).sendKeys(updateFirstName);

		driver.findElement(lastNameTextbox).clear();
		driver.findElement(lastNameTextbox).sendKeys(updateLastName);

		driver.findElement(emailTextbox).clear();
		driver.findElement(emailTextbox).sendKeys(updateEmail);

		driver.findElement(By.id("save-info-button")).click();

		Assert.assertTrue(driver.findElement(genderfemaleTextbox).isSelected());

		Assert.assertEquals(driver.findElement(firstNameTextbox).getAttribute("value"), updateFirstName);
		Assert.assertEquals(driver.findElement(lastNameTextbox).getAttribute("value"), updateLastName);
		Assert.assertEquals(driver.findElement(emailTextbox).getAttribute("value"), updateEmail);
	}

	// @AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@mail.vn";

	}

}
