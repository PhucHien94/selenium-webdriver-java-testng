package webdriver;


import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.webkit.dom.KeyboardEventImpl;

public class Topic_15_Upload_Senkeys {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	
	JavascriptExecutor jsExecutor;
	
	String imgUpload1 = projectPath + "\\uploadFiles\\2mb.jpg";
	String imgUpload2 = projectPath + "\\uploadFiles\\test1.jpg";
	String imgUpload3 = projectPath + "\\uploadFiles\\test2.jpg";

	String chromOneTimeAutoIT = projectPath + "\\autoIT\\chromeUploadOneTime.exe";
	String chromMultipleTimeAutoIT = projectPath + "\\autoIT\\chromeUploadMultiple.exe";

	@BeforeClass
	public void beforeClass() {		
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");

		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		expliciWait = new WebDriverWait(driver, 15);
	}
	
	//@Test
	public void TC_01_AutoIT_One_file() throws IOException {	
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//Click to button
		driver.findElement(By.cssSelector(".btn-success")).click();
		
		//Run exe file
		Runtime.getRuntime().exec(new String[] {chromOneTimeAutoIT,imgUpload2});
	
		//kiem tra file load thanh cong
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='test1.jpg']")).isDisplayed());
		
		//click start button
		driver.findElement(By.cssSelector("table .start")).click();
		
		//Kiem tra upload thanh cong
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='test1.jpg']")).isDisplayed());

	}
	
	//@Test
	public void TC_02_AutoIT_Multiple_file() throws IOException {	
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//Click to button
		driver.findElement(By.cssSelector(".btn-success")).click();
		
		//Run exe file
		Runtime.getRuntime().exec(new String[] {chromMultipleTimeAutoIT,imgUpload2,imgUpload1});
		
		//kiem tra file load thanh cong
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='test1.jpg']")).isDisplayed());	
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='2mb.jpg']")).isDisplayed());
		
		//click start button
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table .start"));
		for (WebElement startButton : startButtons) {
			startButton.click();
			sleepInSecond(1);
		}
		
		//Kiem tra upload thanh cong
		//Assert.assertTrue(driver.findElement(By.xpath("//a[text()='2mb.jpg']")).isDisplayed());

	}
	
	//@Test
	public void TC_03_Java_Robot(){	
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//Click to button
		driver.findElement(By.cssSelector(".btn-success")).click();
	
		uploadFileByRobot(imgUpload1);
		
		//kiem tra file load thanh cong
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='2mb.jpg']")).isDisplayed());
		
		//click start button
		driver.findElement(By.cssSelector("table .start")).click();
		
		//Kiem tra upload thanh cong
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='2mb.jpg']")).isDisplayed());

	}
	
	@Test
	public void TC_04_Upload_Flow(){
		driver.get("https://gofile.io/uploadFiles");
		
		String parentID = driver.getWindowHandle();
		
		//Load 3 files
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(imgUpload1 + "\n" + imgUpload2 + "\n"  + imgUpload3);
		
		//driver.findElement(By.cssSelector("button#rowUploadProgress-startUpload-uploadBtn")).click();
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rowUploadSuccess-downloadPage")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")).click();
		
		switchToWindowByID(parentID);
		
		// Verify download action displayed for each file
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='2mb.jpg']/parent::td/following-sibling::td/a[contains(@class,'download')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='test1.jpg']/parent::td/following-sibling::td/a[contains(@class,'download')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='test1.jpg']/parent::td/following-sibling::td/a[contains(@class,'download')]")).isDisplayed());

		// Verify play action displayed for each file
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='2mb.jpg']/parent::td/following-sibling::td/a[contains(@class,'play')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='test1.jpg']/parent::td/following-sibling::td/a[contains(@class,'play')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='test1.jpg']/parent::td/following-sibling::td/a[contains(@class,'play')]")).isDisplayed());
		
		// Verify info action displayed for each file
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='2mb.jpg']/parent::td/following-sibling::td/a[contains(@class,'info')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='test1.jpg']/parent::td/following-sibling::td/a[contains(@class,'info')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='test1.jpg']/parent::td/following-sibling::td/a[contains(@class,'info')]")).isDisplayed());
		
	}
	
	//@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void uploadFileByRobot(String filePath) {
		try {
			//specify the file location wwith extension
			StringSelection select = new StringSelection(filePath);
			
			//copy to clipboard
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
			
			Robot robot = new Robot();
			sleepInSecond(1);
			
			//Nhan phim enter
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			//Nhan xuong Ctrl - V
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			
			//Nha Ctrl - V
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			sleepInSecond(1);
			
			//Nhan Enter
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void switchToWindowByID(String windowID) {
		//get het id window dang co
		Set<String> allWindowIDs = driver.getWindowHandles();	
		for(String id : allWindowIDs) {
			if(!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
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
