package com.xjg.driver;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutoShoppingCart {

	public AutoShoppingCart() {
		// TODO Auto-generated constructor stub
	}

	public void addProducts(WebDriver driver) {
		driver.get("https://rahulshettyacademy.com/seleniumPractise/");
		String[] itemsNeeded = { "Cucumber", "Brocolli", "Beetroot" };

		int count = 0;

		List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));

		for (int i = 0; i < products.size(); i++) {
			String[] name = products.get(i).getText().split("-");
			String formattedName = name[0].trim();
			List<String> itemsNeededList = Arrays.asList(itemsNeeded);
			if (itemsNeededList.contains(formattedName)) {
				count++;
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				if (count == itemsNeeded.length) {
					break;
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "/Users/xinjiegu/Downloads/chromedriver");

		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		
		prefs.put("profile.managed_default_content_settings.javascript", 2);
		
		options.setExperimentalOption("prefs", prefs);
		
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		AutoShoppingCart autoShoppingCart = new AutoShoppingCart();

		autoShoppingCart.addProducts(driver);

		// click shopping cart
		driver.findElement(By.cssSelector("img[alt='Cart']")).click();
		// click checkout
		driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();

		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.promoCode")));

		driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");

		driver.findElement(By.cssSelector("button.promoBtn")).click();

		// explicit wait
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo")));

		System.out.println(driver.findElement(By.cssSelector("span.promoInfo")).getText());

		// click place order
		driver.findElement(By.xpath("//button[normalize-space()='Place Order']")).click();

		// second page - chose address, check agree terms, click place order
		Select selectCountry = new Select(driver.findElement(By.xpath("//select")));
		selectCountry.selectByVisibleText("Australia");
		// driver.findElement(By.xpath("//option[value='Australia']")).click();
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Proceed']")).click();
		
		System.out.println(driver.findElement(By.cssSelector("div.wrapperTwo > span")).getText());
	}

}
