package usecase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UseCaseTest {
	private static WebDriver driver;
	
	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "../chromedriver");
		driver = new ChromeDriver();
		testCreateRoom();
		testAddSong();
		testViewUserAndDetailsUpdated();	
		
	}

	private static void testViewUserAndDetailsUpdated() {
		System.out.println("Testing View User Account");
		driver.get("http://localhost:4567/user/1");	
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("user")));
		driver.findElement(By.id("recentRooms")).click();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("New Room")));
		System.out.println("Account View correctly and contains recently added room");
	}

	private static void testAddSong() {
		System.out.println("Testing Add Song");
		driver.findElement(By.id("songLink")).sendKeys("New Song");
		driver.findElement(By.id("btn_submitSong")).click();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("New Song")));
		System.out.println("Song added Successfully");
	}

	private static void testCreateRoom() {
		driver.get("http://localhost:4567/");
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("btn-create-room")));
		
		System.out.println("Testing Create Room");
		driver.findElement(By.id("btn-create-room")).click();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("txt_roomname")));
		driver.findElement(By.name("txt_roomname")).sendKeys("New Room");
		driver.findElement(By.name("btn_createroom")).click();
		WebElement wb = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("room")));
		System.out.println("Room Created Successfully");		
	}
}
