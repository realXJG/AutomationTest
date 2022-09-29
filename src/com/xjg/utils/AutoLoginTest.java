package com.xjg.utils;

import java.time.Duration;
//import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutoLoginTest {
    private String username;
    private String pwd;
    private WebDriver driver;
    private String fieldUsername;
    private String fieldPwd;
    private String fieldPhonenumber;
    String loginRes;
    
	public AutoLoginTest(String username, String pwd, WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.pwd = pwd;
		this.driver = driver;
	}
	
	public boolean login() {
		//driver.get("https://rahulshettyacademy.com/locatorspractice/");
		
		driver.findElement(By.id("inputUsername")).sendKeys(username);
		
		driver.findElement(By.name("inputPassword")).sendKeys(pwd);
		
		driver.findElement(By.cssSelector("button.submit.signInBtn")).click();
		
		//<p> will always present on the web page after first failure attempt; 
		if (loginRes != null) return true;
		
		try {
			loginRes = driver.findElement(By.cssSelector("p.error")).getText();
		} catch (NoSuchElementException e) {
			System.out.println("username, password correct");
			return true;
		}
		System.out.println(loginRes);

		return loginRes.length() == 0;
	}
	
	public void resetPwd(String fieldUsername, String fieldPwd, String fieldPhonenumber) throws InterruptedException {
		driver.findElement(By.linkText("Forgot your password?")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys(fieldUsername);
		driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys(fieldPwd);
		driver.findElement(By.xpath("//form/input[3]")).sendKeys(fieldPhonenumber);
		driver.findElement(By.cssSelector("button.reset-pwd-btn")).click();
		driver.findElement(By.xpath("//div[@class='forgot-pwd-btn-conainer']/button[1]")).click();
		
		this.username = fieldUsername;
		this.pwd = fieldPwd; 
		
		System.out.println("reset password completed");
	}
	
	public void logout() throws InterruptedException {
		driver.findElement(By.xpath("//*[text()='Log Out']")).click();
		for (int i = 0; i < 60; i++) {
			Thread.sleep(1000);
			System.out.println("Automation Test will end in" + (60 - i) + "sec");
		}
		driver.close();
	}
	
    //Original hard code logic;
	public void AutoLoginTest1() throws InterruptedException {
		// TODO Auto-generated method stub

		// First log in attempt
		System.setProperty("webdriver.chrome.driver", "/Users/xinjiegu/Downloads/chromedriver");
		WebDriver driver = new ChromeDriver();

		// driver.get("https://rahulshettyacademy.com/locatorspractice/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		driver.findElement(By.id("inputUsername")).sendKeys("rahul");
		driver.findElement(By.name("inputPassword")).sendKeys("123");
		// driver.findElement(By.className("signInBtn")).click();
		driver.findElement(By.cssSelector("button.submit.signInBtn")).click();
		System.out.println(driver.findElement(By.cssSelector("p.error")).getText());

		// reset password
		driver.findElement(By.linkText("Forgot your password?")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("XJG");
		driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("xjg@test.com");
		driver.findElement(By.xpath("//form/input[3]")).sendKeys("6822346494");
		driver.findElement(By.cssSelector("button.reset-pwd-btn")).click();
		driver.findElement(By.xpath("//div[@class='forgot-pwd-btn-conainer']/button[1]")).click();
		Thread.sleep(1000);

		// log in with new account
		driver.findElement(By.cssSelector("#inputUsername")).sendKeys("XJG");
		driver.findElement(By.cssSelector("input[type*='pass']")).sendKeys("rahulshettyacademy");
		driver.findElement(By.id("chkboxOne")).click();
		driver.findElement(By.cssSelector("#chkboxTwo")).click();
		driver.findElement(By.cssSelector("button.submit.signInBtn")).click();

		// login welcome text
		String getUser = driver.findElement(By.cssSelector("div.login-container > h2")).getText();
		String getRes = driver.findElement(By.cssSelector("div.login-container > h1")).getText();
		System.out.println(getUser + getRes);

		// log out
		driver.findElement(By.xpath("//*[text()='Log Out']")).click();

		// close driver
		Thread.sleep(60000);
		driver.close();
	}

}
