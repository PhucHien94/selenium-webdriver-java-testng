package webdriver;


import java.util.Random;
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

public class Topic_14_JavascriptExecutor {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {		
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");

		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	//@Test
	public void TC_01_Live_Guru() {		
		navigateToUrlByJS("http://live.demoguru99.com/");
		
		String liveGuruDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(liveGuruDomain, "live.demoguru99.com");
		
		String liveGuruUrl = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(liveGuruUrl, "live.demoguru99.com");
		
		clickToElementByJS("//a[text()='Mobile']");
		
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		
		Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		
		clickToElementByJS("//a[text()='Customer Service']");
		
		scrollToElement("//input[@id='newsletter']");
		
		sendkeyToElementByJS("//input[@id='newsletter']", generateEmail());
		
		clickToElementByJS("//button[@title='Subscribe']");
		
		Assert.assertTrue(isExpectedTextInInnerText("Thank you for your subscription."));

		navigateToUrlByJS("http://demo.guru99.com/v4/");
		
		Assert.assertEquals((String) executeForBrowser("return document.domain;"), "demo.guru99.com");
	}
		
	//@Test
	public void TC_02_GitHub() {		
		navigateToUrlByJS("https://automationfc.github.io/html5/index.html");

		clickToElementByJS("//input[@name='submit-btn']");
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage("//input[@id='fname']"),"Please fill out this field.");
		sendkeyToElementByJS("//input[@id='fname']", "Automation FC");
		sleepInSecond(2);

		clickToElementByJS("//input[@name='submit-btn']");
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage("//input[@id='pass']"),"Please fill out this field.");

		sendkeyToElementByJS("//input[@id='pass']", "123456");
		sleepInSecond(2);

		clickToElementByJS("//input[@name='submit-btn']");
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please fill out this field.");

		sendkeyToElementByJS("//input[@id='em']", "123");
		sleepInSecond(2);

		//Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please enter an email address.");

		sendkeyToElementByJS("//input[@id='em']", "123@234");
		sleepInSecond(2);
		
		//Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please match the requested format.");

		sendkeyToElementByJS("//input[@id='em']", "123@#$%");
		sleepInSecond(2);
		
		//Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please enter an email address.");

		sendkeyToElementByJS("//input[@id='em']", "123@234.567");
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage("//select"), "Please select an item in the list.");
	}
	
	//@Test
	public void TC_03_New_Customer() {	
		driver.get("http://demo.guru99.com/v4/");
		
		String name, dob, addr, city, state, pinno, telephoneno, customerID;
		String emailAddress, loginPageUrl, userID, password;

		By nameTextboxBy = By.name("name");
		By dobTextboxBy = By.name("dob");
		By genderTextboxBy = By.name("gender");
		By addrTextboxBy = By.name("addr");
		By cityTextboxBy = By.name("city");
		By stateTextboxBy = By.name("state");
		By pinnoTextboxBy = By.name("pinno");
		By telephonenoTextboxBy = By.name("telephoneno");
		By emailTextboxBy = By.name("emailid");
		By passwordTextboxBy = By.name("password");
		
		emailAddress = "Hdemo" + generateEmail();
		name = "Hname Lips";
		dob = "2000-05-05";
		addr = "Shinjuku PO Boxing";
		city = "Japan";
		state = "Asia";
		pinno = "854445";
		telephoneno = "9874562130";
		
		loginPageUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();

		driver.findElement(By.name("emailid")).sendKeys(emailAddress);
		driver.findElement(By.name("btnLogin")).click();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

		driver.get(loginPageUrl);

		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);

		driver.findElement(By.name("btnLogin")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(nameTextboxBy).sendKeys(name);
		
		//removeAttributeInDOM("//input[@name='dob']", "type");
		jsExecutor.executeScript("document.getElementById('dob').removeAttribute('type')");
		sleepInSecond(3);
		driver.findElement(dobTextboxBy).sendKeys(dob);
		
		driver.findElement(addrTextboxBy).sendKeys(addr);
		driver.findElement(cityTextboxBy).sendKeys(city);
		driver.findElement(stateTextboxBy).sendKeys(state);
		driver.findElement(pinnoTextboxBy).sendKeys(pinno);
		driver.findElement(telephonenoTextboxBy).sendKeys(telephoneno);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(By.name("sub")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector(".heading3")).getText(),"Customer Registered Successfully!!!");
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),addr);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),pinno);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),telephoneno);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),emailAddress);
		
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	}
	
	
	@Test
	public void TC_04_Register() {	
		driver.get("http://live.demoguru99.com/");
		clickToElementByJS("//div[@id='header-account']//a[text()='My Account']");
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
	
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean isExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElement(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@mail.vn";

	}
}
