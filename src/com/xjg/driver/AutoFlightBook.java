package com.xjg.driver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;


public class AutoFlightBook {

	public AutoFlightBook() {
		// TODO Auto-generated constructor stub
	}
	
	public void dropDownPractice () throws InterruptedException {
		System.out.println("call dropdownpractice");
		
		System.setProperty("webdriver.chrome.driver", "/Users/xinjiegu/Downloads/chromedriver");

		WebDriver driver = new ChromeDriver();

		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		//driver.get("http://spicejet.com"); 

		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();

		driver.findElement(By.cssSelector("a[value='BLR']")).click();

		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']"))
				.click();

		driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight")).click();

		if (driver.findElement(By.id("Div1")).getAttribute("style").contains("0.5")) {
			System.out.println("its disabled");
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
		
		driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).click();

		driver.findElement(By.id("divpaxinfo")).click();

		Thread.sleep(2000);

		for (int i = 0; i < 5; i++) {
		   driver.findElement(By.id("hrefIncAdt")).click();
		}

		driver.findElement(By.id("btnclosepaxoption")).click();

		Assert.assertEquals(driver.findElement(By.id("divpaxinfo")).getText(), "6 Adult");

		System.out.println(driver.findElement(By.id("divpaxinfo")).getText());

		driver.findElement(By.cssSelector("#ctl00_mainContent_btn_FindFlights")).click();
	}
	
	public void bookFlight() throws InterruptedException {
		System.out.println("spicejet.com");
		
		System.setProperty("webdriver.chrome.driver", "/Users/xinjiegu/Downloads/chromedriver");

		ChromeOptions options = skipNotification();
		
		WebDriver driver =new ChromeDriver(options);

		driver.get("http://spicejet.com"); //URL in the browser

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		Thread.sleep(2000);
		//driver.findElement(By.cssSelector("css-1dbjc4n.r-zso239"));
		driver.findElement(By.xpath("(//div[@class='css-1dbjc4n r-zso239'])[2]")).click();
		
		//driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_0")).click();
//
//		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
//
		driver.findElement(By.xpath("//input[@value='Delhi (DEL)']")).click();
//
//		Thread.sleep(2000);
//
//		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']")).click();
//
//		driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight.ui-state-active")).click();
//
//		if(driver.findElement(By.id("Div1")).getAttribute("style").contains("0.5"))
//
//		{
//
//		System.out.println("its disabled");
//
//		Assert.assertTrue(true);
//
//		}
//
//		else
//
//		{
//
//		Assert.assertTrue(false);
//
//		}
//
//		driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).click();
//
//		driver.findElement(By.id("divpaxinfo")).click();
//
//		   Thread.sleep(2000L);
//
//		for(int i=1;i<5;i++)
//
//		{
//
//		driver.findElement(By.id("hrefIncAdt")).click();
//
//		}
//
//		driver.findElement(By.id("btnclosepaxoption")).click();
//
//		Assert.assertEquals(driver.findElement(By.id("divpaxinfo")).getText(), "5 Adult");
//
//		System.out.println(driver.findElement(By.id("divpaxinfo")).getText());
//
//		driver.findElement(By.cssSelector("input[value='Search']")).click();
	}

	public void bookFlightExpedia (WebDriver driver) throws InterruptedException {
		driver.get("https://www.expedia.com/"); //URL in the browser
		//click Flights
		driver.findElement(By.cssSelector("a.uitk-tab-anchor[aria-controls$='wizard-flight-pwa']")).click();
		//click One-way
		driver.findElement(By.cssSelector("a[aria-controls=\"wizard-flight-tab-oneway\"]")).click();
		//click leave from
		driver.findElement(By.cssSelector("button[data-stid=\"location-field-leg1-origin-menu-trigger\"]")).click();
		//type leaving city
		//Thread.sleep(2000);
		//WebElement.sendKeys(Keys.RETURN);
		WebElement fromCity = driver.findElement(By.cssSelector("input[data-stid=\"location-field-leg1-origin-menu-input\"]"));
		fromCity.sendKeys("Dallas");
		fromCity.sendKeys(Keys.RETURN);
		
		//click destination
		driver.findElement(By.cssSelector("button[data-stid='location-field-leg1-destination-menu-trigger']")).click();
		//type destination city
		WebElement toCity = driver.findElement(By.cssSelector("input[data-stid='location-field-leg1-destination-menu-input']"));
		toCity.sendKeys("Seattle");
		toCity.sendKeys(Keys.RETURN);
		
		//add 3 traveler number
		driver.findElement(By.cssSelector("button[data-testid=\"travelers-field\"]")).click();
		for (int i = 0; i < 2; i++) {
			driver.findElement(By.cssSelector("svg[aria-label=\"Increase adults\"]")).click();
		}
		driver.findElement(By.cssSelector("button[data-testid=\"guests-done-button\"]")).click();
		
		//chose business class
		driver.findElement(By.cssSelector("[data-testid=\"preferred-class-input-trigger\"]")).click();
		driver.findElement(By.xpath("//span[normalize-space()='Business class']")).click();
		
		//click search  sleep 3sec easy to visualize the choose result
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("[data-testid='submit-button']")).click();
	}
	
	public ChromeOptions skipNotification() {
		//Create a map to store  preferences 
		Map<String, Object> prefs = new HashMap<String, Object>();
		    
		//add key and value to map as follow to switch off browser notification
		//Pass the argument 1 to allow and 2 to block
		prefs.put("profile.default_content_setting_values.notifications", 2);
		    
		//Create an instance of ChromeOptions 
		ChromeOptions options = new ChromeOptions();
		    
		// set ExperimentalOption - prefs 
		options.setExperimentalOption("prefs", prefs);
		    
		//Now Pass ChromeOptions instance to ChromeDriver Constructor to initialize chrome driver which will switch off this browser notification on the chrome browser
		return options;
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		AutoFlightBook autoFlightBook = new AutoFlightBook();
		//autoFlightBook.bookFlight();
		
		System.setProperty("webdriver.chrome.driver", "/Users/xinjiegu/Downloads/chromedriver");

		ChromeOptions options = autoFlightBook.skipNotification();
		
		WebDriver driver =new ChromeDriver(options);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		autoFlightBook.bookFlightExpedia(driver);
		
	}

}
