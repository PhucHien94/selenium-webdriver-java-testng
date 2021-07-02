package webdriver;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assume.assumeTrue;
import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.sound.midi.Soundbank;

import org.eclipse.jetty.util.component.Graceful;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Element_Excercise {
	WebDriver driver;
	String firstName, lastName, emailAddress, password, fullName;
	By emailTextbox = By.id("mail");
	By educationTextArea = By.id("edu");
	By under18Radio = By.id("under_18");
	By javaCheckbox = By.id("java");
	
	By passwordTextbox = By.id("password");
	By disableCheckbox = By.id("check-disbaled");
	By disableButton = By.id("button-disabled");
	
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Khoi tao data test
		firstName = "first";
		lastName = "name";
		emailAddress = "firstnametest" + generateEmail();
		password = "123456";
		fullName = firstName + " " + lastName;
	}
	
	//@Test
	public void TC_01_Create_New_Account() {		
		driver.get("http://live.demoguru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();	
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "Thank you for registering with Main Website Store.");
		
		//Dung ham isDisplayed de kiem tra
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(string(),'" + fullName + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(string(),'" + emailAddress + "')]")).isDisplayed());

		//Dung ham getText va verify contains
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(emailAddress));
		
		//Logout
		driver.findElement(By.cssSelector(".skip-account")).click();
		driver.findElement(By.cssSelector("a[title='Log Out']")).click();
	}
	
	//@Test
	public void TC_02_Login() {		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.cssSelector("#email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("#pass")).sendKeys(password);
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(), "Hello, " + fullName + "!");
	}

	//@Test
	public void TC_03_Displayed_Newbie() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if(driver.findElement(By.id("mail")).isDisplayed()) {
			driver.findElement(By.id("mail")).sendKeys("AutomationFC");
			System.out.println("Mail textbox is displayed");
		}else {
			System.out.println("Mail textbox is not displayed");
		}
				
		if(driver.findElement(By.id("edu")).isDisplayed()) {
			driver.findElement(By.id("edu")).sendKeys("AutomationFC");
			System.out.println("Education textarea is displayed");
		}else {
			System.out.println("Education textarea is not displayed");
		}
		
		if (driver.findElement(By.id("under_18")).isDisplayed()) {
			driver.findElement(By.id("under_18")).click();
			System.out.println("Radio button under 18 is displayed");
		} else {
			System.out.println("Radio button under 18 is not displayed");
		}
		
	}
	
	//@Test
	public void TC_03_Displayed_Function() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		By emailTextbox = By.id("mail");
		By educationTextArea = By.id("edu");
		By under18Radio = By.id("under_18");
		
		if(isElementDisplayed(emailTextbox)) {
			sendkeyToElement(emailTextbox,"AutomationFC");
		}
		
		if(isElementDisplayed(educationTextArea)) {
			sendkeyToElement(educationTextArea,"AutomationFC");
		}
		
		if(isElementDisplayed(under18Radio)) {
			clickToElement(under18Radio);
		}
		
	}
	
	//@Test
	public void TC_04_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		clickToElement(under18Radio);
		clickToElement(javaCheckbox);

		Assert.assertTrue(isElementSelecteded(javaCheckbox));
		Assert.assertTrue(isElementSelecteded(under18Radio));
		
		clickToElement(under18Radio);
		clickToElement(javaCheckbox);
		
		Assert.assertFalse(isElementSelecteded(javaCheckbox));
		Assert.assertTrue(isElementSelecteded(under18Radio));

		
	}
	
	//@Test
	public void TC_05_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		Assert.assertTrue(isElementDisplayed(emailTextbox));
		Assert.assertTrue(isElementDisplayed(educationTextArea));
		Assert.assertTrue(isElementDisplayed(under18Radio));
		Assert.assertTrue(isElementDisplayed(javaCheckbox));
		
		Assert.assertFalse(isElementDisplayed(passwordTextbox));
		Assert.assertFalse(isElementDisplayed(disableCheckbox));
		Assert.assertFalse(isElementDisplayed(disableButton));

	}
	
	
	@Test
	public void TC_06_Register_Validate() {
		driver.get("https://login.mailchimp.com/signup/");
		
		By passwordTextbox = By.cssSelector("#new_password");
		By signupButton = By.cssSelector("#create-account");
		By newletterCheckbox = By.id("marketing_newsletter");
		By upperCaseCompleted  = By.cssSelector(".uppercase-char.completed");
		By lowerCaseCompleted = By.cssSelector(".lowercase-char.completed");
		By numberCompleted = By.cssSelector(".number-char.completed");
		By specialCharCompleted = By.cssSelector(".special-char.completed");
		By geaterThan8CharCompleted = By.cssSelector("li[class='8-char completed']");
		
		
		driver.findElement(By.id("email")).sendKeys("htest@mail.com");
		driver.findElement(By.id("new_username")).sendKeys("htest123");
		
		//Uppercase
		driver.findElement(passwordTextbox).sendKeys("AUTOMATION");
		sleepInSecond(2);

		Assert.assertTrue(isElementDisplayed(upperCaseCompleted));
		Assert.assertFalse(isElementEnabled(signupButton));
		sleepInSecond(2);

		//Lowercase
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("automation");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(lowerCaseCompleted));
		Assert.assertFalse(isElementEnabled(signupButton));
		sleepInSecond(2);
		
		//Number
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("123456");
		Assert.assertTrue(isElementDisplayed(numberCompleted));
		Assert.assertFalse(isElementEnabled(signupButton));
		
		//Special
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("!@#$%");
		sleepInSecond(2);

		Assert.assertTrue(isElementDisplayed(specialCharCompleted));
		Assert.assertFalse(isElementEnabled(signupButton));
		
		//geater than 8 char
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("automationtesting");
		Assert.assertTrue(isElementEnabled(geaterThan8CharCompleted));
		
		//all criteria
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("Automation123***");
		
		Assert.assertFalse(isElementDisplayed(upperCaseCompleted));
		Assert.assertFalse(isElementDisplayed(lowerCaseCompleted));
		Assert.assertFalse(isElementDisplayed(numberCompleted));
		Assert.assertFalse(isElementDisplayed(specialCharCompleted));
		Assert.assertFalse(isElementDisplayed(geaterThan8CharCompleted));
		
		Assert.assertTrue(isElementEnabled(signupButton));
		
		clickToElement(newletterCheckbox);
		Assert.assertTrue(isElementSelecteded(newletterCheckbox));

	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public boolean isElementDisplayed(By by) {
		if(driver.findElement(by).isDisplayed()) {
			System.out.println(by + "is displayed");
			return true;
		} else {
			System.out.println(by + "is not displayed");
			return false;

		}
	}
	
	public boolean isElementSelecteded(By by) {
		if(driver.findElement(by).isSelected()) {
			System.out.println(by + "is selected");
			return true;
		} else {
			System.out.println(by + "is not selected");
			return false;

		}
	}
	
	public boolean isElementEnabled(By by) {
		if(driver.findElement(by).isEnabled()) {
			System.out.println(by + "is enable");
			return true;
		} else {
			System.out.println(by + "is not enable");
			return false;

		}
	}
	
	public void sendkeyToElement(By by,String value) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
		
	}
	
	public void clickToElement(By by) {
		driver.findElement(by).click();
	}
	
	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@mail.vn";
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
