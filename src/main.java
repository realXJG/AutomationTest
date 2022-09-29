import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
//		System.out.println("Hello World!");
//
//		// ChromeDriver chromeDriver = new ChromeDriver();
//		System.setProperty("webdriver.chrome.driver", "/Users/xinjiegu/Downloads/chromedriver");
//		WebDriver chromeDriver = new ChromeDriver();
//		chromeDriver.get("https://www.google.com/");
//		String currTitle = chromeDriver.getTitle();
//		System.out.println(currTitle);
//		System.out.println(chromeDriver.getCurrentUrl());
//		//chromeDriver.quit();
//		chromeDriver.close();
		
		//First log in attempt
		System.setProperty("webdriver.chrome.driver", "/Users/xinjiegu/Downloads/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		//driver.get("https://rahulshettyacademy.com/locatorspractice/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		driver.findElement(By.id("inputUsername")).sendKeys("rahul");
		driver.findElement(By.name("inputPassword")).sendKeys("123");
		//driver.findElement(By.className("signInBtn")).click();
		driver.findElement(By.cssSelector("button.submit.signInBtn")).click();
		System.out.println(driver.findElement(By.cssSelector("p.error")).getText());
		
		//reset password
		driver.findElement(By.linkText("Forgot your password?")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("XJG");
		driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("xjg@test.com");
		driver.findElement(By.xpath("//form/input[3]")).sendKeys("6822346494");
		driver.findElement(By.cssSelector("button.reset-pwd-btn")).click();
		driver.findElement(By.xpath("//div[@class='forgot-pwd-btn-conainer']/button[1]")).click();
		Thread.sleep(1000);
		
		//log in with new account
		driver.findElement(By.cssSelector("#inputUsername")).sendKeys("XJG");
		driver.findElement(By.cssSelector("input[type*='pass']")).sendKeys("rahulshettyacademy");
		driver.findElement(By.id("chkboxOne")).click();
		driver.findElement(By.cssSelector("#chkboxTwo")).click();
		driver.findElement(By.cssSelector("button.submit.signInBtn")).click();
		
		//login welcome text
		String getUser = driver.findElement(By.cssSelector("div.login-container > h2")).getText();
		String getRes = driver.findElement(By.cssSelector("div.login-container > h1")).getText();
		System.out.println(getUser + getRes);
		
		//log out
		driver.findElement(By.xpath("//*[text()='Log Out']")).click();
		
		//close driver
		Thread.sleep(60000);
		driver.close();
	}

}
