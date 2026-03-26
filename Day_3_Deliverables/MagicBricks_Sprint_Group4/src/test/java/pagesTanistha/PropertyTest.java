package pagesTanistha;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class PropertyTest {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.magicbricks.com/property-for-sale-rent-in-Kolkata/residential-real-estate-Kolkata");

        Thread.sleep(3000);

        // 🔹 HomePage actions
        HomePage home = new HomePage(driver);

        home.getBudget().click();
        home.selectMinBudget();
        home.selectMaxBudgetOption();
        home.getSearchButton().click();

        Thread.sleep(3000);

        // 🔹 Click first property
        List<WebElement> properties = driver.findElements(By.cssSelector(".mb-srp__card--title"));
        properties.get(0).click();

        // 🔹 Switch to property tab
        switchToNewTab(driver);

        System.out.println("Property Page Title: " + driver.getTitle());

        Thread.sleep(3000);

        // 🔹 Initialize Property Page AFTER switching tab
        PropertyDetailPage property = new PropertyDetailPage(driver);

        // Step 1: Click Buy
        property.getBuyTag().click();

        // Step 2: Click Budget dropdown
        property.getBudgetTag();

        Thread.sleep(2000); // wait for dropdown

        // 🔥 Capture tab count BEFORE clicking category
        int beforeTabs = driver.getWindowHandles().size();

        // Step 3: Click budget category
        property.getCategory();

        Thread.sleep(3000); // wait for new tab to open

        // 🔥 Switch to new tab AGAIN
        switchToNewTab(driver);

        int afterTabs = driver.getWindowHandles().size();

        System.out.println("New Page Title: " + driver.getTitle());

        if (afterTabs > beforeTabs) {
            System.out.println("✅ New tab opened");
        } else {
            System.out.println("❌ No new tab opened");
        }

        driver.quit();
    }

    // 🔥 Reusable tab switch method
    public static void switchToNewTab(WebDriver driver) {
        String currentTab = driver.getWindowHandle();

        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(currentTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
    }
}