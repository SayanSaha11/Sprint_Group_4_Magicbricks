package PagesAahana;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LocalityTrendsPage extends BasePage {

    // ─── Locators ────────────────────────────────────────────────────────────────

    private final By localitySnapshotTab = By.xpath("//div[text()='Locality Snapshot']");
    private final By localityInfoBlocks  = By.className("loc-det__blocks__tabinfoheading");

    // ─── Constructor ─────────────────────────────────────────────────────────────

    public LocalityTrendsPage(WebDriver driver) {
        super(driver);
    }

    // ─── Actions ──────────────────────────────────────────────────────────────────

    /**
     * Clicks the "Locality Snapshot" tab on the trends page.
     */
    public void clickLocalitySnapshot() {
        driver.findElement(localitySnapshotTab).click();
    }

    
    /**
     * Prints the page title and all locality detail blocks to the console.
     */
    public void printLocalityDetails() {
        System.out.println("Page Title: " + driver.getTitle());

        List<WebElement> infos = driver.findElements(localityInfoBlocks);
        System.out.println("Property Info:");
        if (infos.isEmpty()) {
            System.out.println("  (No locality info blocks found)");
        } else {
            for (WebElement info : infos) {
                System.out.println("  " + info.getText());
            }
        }
    }

    /**
     * Returns the list of locality info heading text values.
     * Used in assertions from step definitions.
     */
    public List<String> getLocalityInfoTexts() {
        List<WebElement> elements = driver.findElements(localityInfoBlocks);
        return elements.stream()
            .map(WebElement::getText)
            .filter(text -> !text.isBlank())
            .collect(java.util.stream.Collectors.toList());
    }
}