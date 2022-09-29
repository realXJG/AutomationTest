import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.xjg.utils.*;

public class AutoLoginImpl {

	public AutoLoginImpl() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "/Users/xinjiegu/Downloads/chromedriver");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		
		String username = "testUser01";
		String password = "Temp1234!";
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		AutoLoginTest autoLoginTest = new AutoLoginTest(username, password, driver);
		
		boolean isLogin = autoLoginTest.login();
		
		
		password = "rahulshettyacademy";
		String phonenumber = "6822346494";
		
		if (!isLogin) {
			autoLoginTest.resetPwd(username, password, phonenumber);
		} else {
			return;
		}
		
		//Log in with correct password;
		Thread.sleep(1000);
		isLogin = autoLoginTest.login();
		
		if (isLogin) {
			System.out.println("Logged In...");
		} else {
			System.out.println("Failed, please correct the password!");
		}
		
		int Ten_Sec = 10000;
		Thread.sleep(Ten_Sec);
		autoLoginTest.logout();
	}

}
