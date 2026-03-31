package pages.pages_sayan;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class NewProjectsPage extends BasePage {

    private static final String NEW_PROJECTS_URL =
            "https://www.magicbricks.com/new-projects-Mumbai";

    private final By projectCards =
            By.xpath("//div[contains(@class,'mghome__prjblk__txtsec')]");

    private final By contactSeller =
            By.xpath("(//span[text()='Contact Seller'])[1]");

    private final By nameField =
            By.xpath("(//input[@id='userName'])[1]");

    private final By emailField =
            By.xpath("(//input[@id='userEmail'])[1]");

    private final By mobileField =
            By.xpath("(//input[@id='userMobile'])[1]");

    private final By getContactBtn =
            By.xpath("(//button[contains(@class,'contact-form__btn')])[1]");

//    private final By topMatchContent =
//            By.xpath("//button[contains(text(),'Verify')]");

    private final By firstTopMatch =
            By.xpath("//*[@id='topmatches']/div/div[1]");

    public NewProjectsPage() {
        super();
    }

    public void openNewProjectsPage() {
        driver.get(NEW_PROJECTS_URL);

        waitPageReady();

        // wait until cards are loaded (dynamic content)
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(projectCards, 2));
    }

    public void printFirst3ProjectDetails() {

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(projectCards, 2));

        for (int i = 0; i < 3; i++) {

            for (int retry = 0; retry < 3; retry++) {
                try {
                    List<WebElement> cards = driver.findElements(projectCards);

                    System.out.println("\nPROPERTY " + (i + 1) + " DETAILS :--\n");
                    System.out.println(cards.get(i).getText());

                    break;

                } catch (org.openqa.selenium.StaleElementReferenceException e) {
                    sleep(1000);
                }
            }
        }
    }

    public void clickFirstProject() {

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(projectCards, 2));

        for (int i = 0; i < 3; i++) {
            try {
                List<WebElement> cards = driver.findElements(projectCards);

                WebElement first = cards.get(0);

                jsScrollIntoView(first);
                wait.until(ExpectedConditions.elementToBeClickable(first));

                first.click();
                return;

            } catch (Exception e) {
                sleep(1000);
            }
        }

        throw new RuntimeException("Unable to click first project (stale issue)");
    }

    public void clickContactSeller() {

        waitPageReady();

        WebElement btn = wait.until(
                ExpectedConditions.presenceOfElementLocated(contactSeller)
        );

        jsScrollIntoView(btn);
        jsClick(btn); // safer for modern UI

        waitVisible(nameField);
    }

    public void fillContactForm(String name, String email, String mobile) {

        WebElement nameEl = waitClickable(nameField);
        nameEl.clear();
        nameEl.sendKeys(name);

        WebElement emailEl = waitClickable(emailField);
        emailEl.clear();
        emailEl.sendKeys(email);

        WebElement mobileEl = waitClickable(mobileField);
        mobileEl.clear();
        mobileEl.sendKeys(mobile);
    }

    public void clickGetContactDetails() {

        waitPageReady();

        //  trigger validation 
        driver.findElement(mobileField).sendKeys(Keys.TAB);

        sleep(1500); 

        WebElement btn = wait.until(
                ExpectedConditions.presenceOfElementLocated(getContactBtn)
        );

        jsScrollIntoView(btn);

        for (int i = 0; i < 4; i++) {
            try {
                jsClick(btn);
                break;
            } catch (Exception e) {
                sleep(1000);
                btn = driver.findElement(getContactBtn);
            }
        }

//        wait.until(ExpectedConditions.visibilityOfElementLocated(topMatchContent));
    }

    public void clickContactSubmitButton() throws InterruptedException {
    	Thread.sleep(50000);
        longWaitClickable(getContactBtn).click();
    }

    public void printContactedConfirmation() {
        System.out.println(waitVisible(getContactBtn).getText());
    }

    public String getContactButtonText() {
        return waitVisible(getContactBtn).getText();
    }

    protected void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}