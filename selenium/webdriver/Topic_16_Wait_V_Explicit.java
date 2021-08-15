package webdriver;


import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Wait_V_Explicit {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	By startButton = By.cssSelector("#start>button");
	By loadingIcon = By.id("loading");
	By helloworldText = By.id("finish");

	String imgUpload1 = projectPath + "\\uploadFiles\\test1.jpg";
	String imgUpload2 = projectPath + "\\uploadFiles\\test2.jpg";
	
	@BeforeClass
	public void beforeClass() {		
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver,30);
			
	}
	
	//@Test
	public void TC_01_Visible() {
		driver.get("http://the-internet.herokuapp.app/dynamic_loading/2");
		
		driver.findElement(startButton).click();
		
		//cho cho hello word text duoc hien thi
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloworldText));

		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());
	}
	
	//@Test
	public void TC_02_Invisible() {	
		driver.get("http://the-internet.herokuapp.app/dynamic_loading/2");
		
		driver.findElement(startButton).click();
		
		// cho loading icon bien mat di
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));

		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());
	}

	//@Test
	public void TC_03_Ajax_Loading() {	
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		// 	Wait cho den khi datepicker hien thi
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));
		
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
		
		// Click vao ngay hien tai
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[text()='27']")));
		driver.findElement(By.xpath("//td/a[text()='27']")).click();
		
		// Wait cho ajax loading bien maf;
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
	
		// Verify ngay duoc chon
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//td[@class='rcSelected']/a[text()='27']")));
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='27']")).isDisplayed());
		
		// Verify ngay da chon duoc hien thi o 'Selected Dates'
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "Sunday, June 27, 2021");
		
	}
	
	@Test
	public void TC_04_Upload_File() {	
		driver.get("https://gofile.io/uploadFiles");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'uploadButton')]")));
				
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(imgUpload1 + "\n" + imgUpload2);
		
		//cho cho spinner invisible
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#rowUploadProgress-selectServer")));
		
		// Wait progress bar icon invisible
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//div[@role='progressbar']"))));
	
		// Wait success message displayed
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());
		
		// wait button show file clickable and click button show file
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#rowUploadSuccess-showFiles")));
		driver.findElement(By.cssSelector("button#rowUploadSuccess-showFiles")).click();
		
		// Verify image uploaded success
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='test1.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='test2.jpg']")).isDisplayed());
		
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
