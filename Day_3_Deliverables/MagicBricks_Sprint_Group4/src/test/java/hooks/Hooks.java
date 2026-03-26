package hooks;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	public static WebDriver driver;
	
	@Before
	public void setup() {
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//driver.get("https://www.magicbricks.com/property-for-sale-rent-in-Kolkata/residential-real-estate-Kolkata");
	}
	
	@After
	public void closeDown() {
		if(driver!=null) driver.quit();
	}
}
