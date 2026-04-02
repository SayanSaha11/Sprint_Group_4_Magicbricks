package PagesAahana;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class MagicBricksHomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final String KOLKATA_URL =
            "https://www.magicbricks.com/property-for-sale-rent-in-Kolkata/residential-real-estate-Kolkata";

    // ── Elements ───────────────────────────────────────────────────────────────

    @FindBy(xpath = "(//a[@class='mb-header__sub__tabs__link js-menu-link'])[3]")
    private WebElement toolsAndAdviceMenu;

    @FindBy(xpath = "//a[text()='Developer Lounge']")
    private WebElement developerLoungeLink;

    // ── Constructor ────────────────────────────────────────────────────────────

    public MagicBricksHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    // ── Actions ────────────────────────────────────────────────────────────────

    public void navigateToKolkataResidentialPage() {
        driver.get(KOLKATA_URL);
    }

    public void navigateToSection(String sectionName) {
        wait.until(ExpectedConditions.elementToBeClickable(toolsAndAdviceMenu));
        toolsAndAdviceMenu.click();
        sleep(2000);
    }

    public void clickLink(String linkText) throws InterruptedException {
        Set<String> tabsBefore = driver.getWindowHandles();

        wait.until(ExpectedConditions.elementToBeClickable(developerLoungeLink));
        developerLoungeLink.click();
        sleep(3000);

        // Switch to new tab if Developer Lounge opens in a new tab
        for (String tab : driver.getWindowHandles()) {
            if (!tabsBefore.contains(tab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
    }

    // ── Helper ─────────────────────────────────────────────────────────────────

    private void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}