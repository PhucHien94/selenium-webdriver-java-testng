package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Web_Browser_Method {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();	
	}

	@Test
	public void TC_01_Browser() {
		// Bien driver tuong tac vơi browser
		
		//Mo 1 page ra (Url)
		driver.get("https://www.facebook.com/r.php");
		
		//Lay ra duong dan (Url) cua page hien tai
		String localPageUrl = driver.getCurrentUrl();
		
		//Lay ra title cua page hien tai
		driver.getTitle();
		
		//Lay ra HTML code cua page hien tai
		driver.getPageSource();
		
		//Xu lu tab / windows
		driver.getWindowHandle();
		driver.getWindowHandle();

		//Framework (share class state)
		//driver.manage().addCookie(cookie);
		
		//Cho cho element dc tim thay trong vong xxx thoi gian
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//setScriptTimeout
		//pageLoadTimeout
		
		// Back ve page trc do
		// Forward toi page trc do
		// Refresh page hien tai
		// Mo 1 urrl ra
		driver.navigate().back();
		// History (navigate().to("http://facebook.com/r.php"))
		
		//Browser chi co 1 tab duy nhat thi deu dong trinh duyet 
		
		// Dong all tab (dong trinh duyet)
		driver.quit();
		
		// Close tab dang active
		// cu li switch tab/windows
		driver.close();
		
		// Windows / Tab
		// Alert
		// Frame / Iframe
		driver.switchTo().alert();
		driver.switchTo().frame(1);
		driver.switchTo().window("");
		
		driver.manage().window().fullscreen();
		driver.manage().window().maximize();
		
		// Lay ra vi tri browser so voi do phan giai man hinh hien tai
		driver.manage().window().getPosition();
		//driver.manage().window().setPosition(targetPosition);
		
		driver.manage().window().getSize();
		//driver.manage().window().setSize(targetSize);
		
		
		// Bien ... tuong tac voi element (textbox,dropdown,checkbox,....)

	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
