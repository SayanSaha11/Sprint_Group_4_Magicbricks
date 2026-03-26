package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import Pages.HomePage;

public class HomeTest {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.magicbricks.com/property-for-sale-rent-in-Kolkata/residential-real-estate-Kolkata");

        Thread.sleep(3000); // wait for page to load

        HomePage homePage = new HomePage(driver);

        homePage.getBuy().click();
        System.out.println("Clicked Buy tab");

        // Click Budget
        homePage.getBudget().click();
        System.out.println("Clicked Budget option");

        // Select Min Budget (₹10 Lac)
        homePage.selectMinBudget();
        System.out.println("Selected Min Budget");

        // Select Max BHK (8 BHK)
        homePage.selectMaxBudgetOption();
        System.out.println("Selected Max BHK");

        // Click Search
        homePage.getSearchButton().click();
        System.out.println("Clicked Search button");

        Thread.sleep(5000); // wait to observe results

        driver.quit();
    }
}