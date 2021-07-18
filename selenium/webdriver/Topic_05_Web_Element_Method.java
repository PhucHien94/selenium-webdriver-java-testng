package webdriver;

import java.util.List;
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

public class Topic_05_Web_Element_Method {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Web_Element() {
		//Muon thao tac voi element th phai tim element
		
		//Thao tác vs 1 element
		driver.findElement(By.id(""));
		
		//Tìm nhiều element
		driver.findElements(By.id(""));
		
		// nếu như chỉ thao tác 1 lần với element thì ko cần khai báo biến
		driver.findElement(By.id("small-searchterms")).sendKeys("Apple");
		
		// Nếu cần thao tác nhiều lần với element > Nên khai báo biến
		WebElement searchTextbox = driver.findElement(By.id("small-searchterms"));
		searchTextbox.clear();
		searchTextbox.sendKeys("Apple");
		searchTextbox.getAttribute("value");
		
		//Đếm xem có bao nhiêu element thỏa mản điều kiện
		// Verify số lượng element trả về như mong đợi
		// Thao tác vs tất cả các loại element giống nhau trong 1 page (checkbox / textbox,...)
		List<WebElement> textboxes = driver.findElements(By.xpath("//div[@class='inputs']/input[not(@type='checkbox')]"));
		
		//Verify số lượng textbox có trong form đăng ký		
		Assert.assertEquals(textboxes.size(), 6);
		
		WebElement singleElement = driver.findElement(By.className(""));
		
		// Textbox / TextArea / Editable dropdown
		// Dữ liệu được toàn vẹn
		singleElement.clear();
		singleElement.sendKeys("");
		
		// Button/Link/Radio/Checkbox/ Custom Dropdown/....
		singleElement.click();
		
		//Các hàm có tiền tố bằng đầu bằng get luôn trả về dữ liệu
		// getText/ getTitle/ getCurrentUrl/ getPageSource/ getAttribute/....
		singleElement = driver.findElement(By.xpath("//input[@id='FirstName']"));
		singleElement.getAttribute("value");
		
		singleElement =  driver.findElement(By.xpath("//input[@id='small-searchterms']"));
		singleElement.getAttribute("placeholder");
		
		//Lấy ra giá trị của các thuốcj tính css- thường dùng để test GUI
		// Font/ Size/ color/ background/....
		singleElement =  driver.findElement(By.cssSelector(".search-box-button"));
		singleElement.getCssValue("background-color");
		//#4ab2f1
		singleElement.getCssValue("text-transform");
		//uppercase
		
		//Lấy ra tọa độ của element so với page hiện tại (get góc bên ngoài element)
		singleElement.getLocation();
		
		//Lấy ra kích thước của element (rộng x cao : lấy bên trong của element)
		singleElement.getSize();
		
		// Location + Size
		singleElement.getRect();
		
		// Chụp hình lỗi > đưa vào HTML report
		singleElement.getScreenshotAs(OutputType.FILE);
		
		//Id / Class/ Css/ Name/.....
		// Từ 1 element ko biết được tagname > lấy ra tagname truyền vào cho 1 locator khác
		singleElement =  driver.findElement(By.cssSelector(".search-box-button"));
		String searchButtonTagname = singleElement.getTagName();		
		searchTextbox = driver.findElement(By.xpath("//"+ searchButtonTagname + "@class='inputs']/input[not(@type='checkbox')]"));
		
		// Lấy ra text của element ( header/ Link/ message/..)
		singleElement.getText();
		
		// Các hàm có tiền tố là isXX thì trả về kiểu boolean
		// true/false
		
		// Kiểm tra 1 element là hiển thị cho người dùng thao tác hay ko
		// true: đang hiển thị
		// false: không hiên thị
		singleElement.isDisplayed();
		
		// Kiểm tra element có disable hay không
		singleElement.isEnabled();
		
		// Kiểm tra element đã được chọn hay chưa
		// Checkbox/ Radio / Dropdown (có thư viện riêng)
		singleElement.isSelected();
		
		// Enter vào textbox / click vào button
		// Chỉ dùng được trong form (Login / Search/ Register/..)
		singleElement.submit();
		
		singleElement =  driver.findElement(By.id("small-searchterms"));
		singleElement.sendKeys("Apple");
		singleElement.submit();
		
		
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
