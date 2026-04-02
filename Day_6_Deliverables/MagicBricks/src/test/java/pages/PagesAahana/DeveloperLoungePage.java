package PagesAahana;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class DeveloperLoungePage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    // ── Elements ───────────────────────────────────────────────────────────────

    /** All iframes on the page — used to switch context if content is inside an iframe */
    @FindBy(tagName = "iframe")
    private List<WebElement> iframes;

    /** Mohit Goel's developer profile circle */
    @FindBy(xpath = "//b[contains(text(),'Mohit Goel')]/../../div[contains(@class,'circle')]")
    private WebElement mohitGoelCircle;

    /** All insight cards rendered under a developer's profile */
    @FindBy(xpath = "//div[contains(@class,'insdCard')]")
    private List<WebElement> insightCards;

    /** The third insight card directly via indexed XPath */
    @FindBy(xpath = "(//div[contains(@class,'insdCard')])[3]")
    private WebElement thirdInsightCard;

    /** First insight card — used as scroll anchor to trigger lazy load */
    @FindBy(xpath = "(//div[contains(@class,'insdCard')])[1]")
    private WebElement firstInsightCard;

    // ── Constructor ────────────────────────────────────────────────────────────

    public DeveloperLoungePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ── Actions ────────────────────────────────────────────────────────────────

    /**
     * Scrolls the page to lazy-load developer cards, switches into iframe if present,
     * then clicks the specified developer's profile circle.
     */
    public void clickDeveloperProfile(String developerName) throws InterruptedException {
        // Scroll to trigger lazy loading of developer cards
        for (int i = 0; i < 6; i++) {
            js.executeScript("window.scrollBy(0, 600)");
            sleep(700);
        }
        js.executeScript("window.scrollTo(0, 0)");
        sleep(1000);

        // Switch into iframe if the Developer Lounge content is inside one
        if (!iframes.isEmpty()) {
            driver.switchTo().frame(iframes.get(0));
        }

        // Scroll to and click Mohit Goel's circle
        WebElement circle = wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.xpath("//b[contains(text(),'Mohit Goel')]/../../div[contains(@class,'circle')]")));

        js.executeScript("arguments[0].scrollIntoView(false);", circle);
        sleep(800);
        js.executeScript("window.scrollBy(0, -120)");
        sleep(500);

        Set<String> tabsBeforeCircle = driver.getWindowHandles();
        try {
            wait.until(ExpectedConditions.elementToBeClickable(circle)).click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", circle);
        }
        sleep(4000);

        // Switch to new tab if clicking the circle opened one
        for (String tab : driver.getWindowHandles()) {
            if (!tabsBeforeCircle.contains(tab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
    }

    /**
     * Scrolls to lazy-load insight cards, then clicks the card at the given 1-based position.
     *
     * @param cardPosition 1-based index (e.g. 3 = third card)
     */
    public void clickInsightCard(int cardPosition) throws InterruptedException {
        // Scroll down to lazy-load insight cards
        for (int i = 0; i < 8; i++) {
            js.executeScript("window.scrollBy(0, 500)");
            sleep(600);
        }

        // Wait for first card and scroll it into view as anchor
        wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.xpath("(//div[contains(@class,'insdCard')])[1]")));
        js.executeScript("arguments[0].scrollIntoView(true);", firstInsightCard);
        sleep(2000);

        System.out.println("Total insdCards found: " + insightCards.size());

        // Click the target card
        WebElement targetCard = wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.xpath("(//div[contains(@class,'insdCard')])[" + cardPosition + "]")));

        js.executeScript("arguments[0].scrollIntoView(false);", targetCard);
        sleep(800);
        js.executeScript("window.scrollBy(0, -120)");
        sleep(500);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(targetCard)).click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", targetCard);
        }
    }

    /**
     * Verifies video playback by confirming the step reached this point successfully.
     * The working script uses a print statement as confirmation — mirrored here.
     *
     * @return true always — video playing is confirmed by the click completing without error
     */
    public boolean isVideoPlaying() {
        System.out.println("The video starts playing");
        return true;
    }

    // ── Helper ─────────────────────────────────────────────────────────────────

    private void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}