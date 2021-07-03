package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.commands.Click;

import sun.text.normalizer.CharTrie.FriendAgent;

public class Topic_06_Textbox_TextArea_guru {
	WebDriver driver;
	String emailAddress, loginPageUrl, userID, password;

	//Data for create new customer
	String name, dob, addr, city, state, pinno, telephoneno, customerID;
	//Data for edit customer
	String editAddr, editCity, editState, editPinno, editTelephoneno, editEmail;

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

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4/");

		emailAddress = "Hdemo" + generateEmail();
		
		//Init data (new customer)
		name = "Hname Lips";
		dob = "2000-05-05";
		addr = "Shinjuku PO Boxing";
		city = "Japan";
		state = "Asia";
		pinno = "854445";
		telephoneno = "9874562130";
		
		//data for new customer)
		editAddr = "659 New Yersey";
		editCity = "London";
		editState = "Manchester";
		editPinno = "659847";
		editTelephoneno = "0987888999";
		editEmail = "johnterry"+ generateEmail();

	}

	@Test
	public void TC_01_Register() {
		loginPageUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();

		driver.findElement(By.name("emailid")).sendKeys(emailAddress);
		driver.findElement(By.name("btnLogin")).click();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

	}

	@Test
	public void TC_02_Login() {
		driver.get(loginPageUrl);

		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);

		driver.findElement(By.name("btnLogin")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");

	}

	@Test
	public void TC_03_Create_New_User() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(nameTextboxBy).sendKeys(name);
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
	public void TC_04_Update_User() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();

		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		Assert.assertFalse(driver.findElement(nameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(genderTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(dobTextboxBy).isEnabled());
		
		Assert.assertEquals(driver.findElement(nameTextboxBy).getAttribute("value"),name);
		Assert.assertEquals(driver.findElement(dobTextboxBy).getAttribute("value"),dob);
		Assert.assertEquals(driver.findElement(addrTextboxBy).getText(),addr);
		Assert.assertEquals(driver.findElement(cityTextboxBy).getAttribute("value"),city);
		Assert.assertEquals(driver.findElement(stateTextboxBy).getAttribute("value"),state);
		Assert.assertEquals(driver.findElement(pinnoTextboxBy).getAttribute("value"),pinno);
		Assert.assertEquals(driver.findElement(telephonenoTextboxBy).getAttribute("value"),telephoneno);
		Assert.assertEquals(driver.findElement(emailTextboxBy).getAttribute("value"),emailAddress);
		
		driver.findElement(addrTextboxBy).clear();
		driver.findElement(addrTextboxBy).sendKeys(editAddr);
		
		driver.findElement(cityTextboxBy).clear();
		driver.findElement(cityTextboxBy).sendKeys(editCity);
		
		driver.findElement(stateTextboxBy).clear();
		driver.findElement(stateTextboxBy).sendKeys(editState);
		
		driver.findElement(pinnoTextboxBy).clear();
		driver.findElement(pinnoTextboxBy).sendKeys(editPinno);
		
		driver.findElement(telephonenoTextboxBy).clear();
		driver.findElement(telephonenoTextboxBy).sendKeys(editTelephoneno);
		
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(emailTextboxBy).sendKeys(editEmail);
		
		driver.findElement(By.name("sub")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector(".heading3")).getText(),"Customer details updated Successfully!!!");
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),editAddr);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),editPinno);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),editTelephoneno);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),editEmail);
	
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@mail.vn";

	}

}
