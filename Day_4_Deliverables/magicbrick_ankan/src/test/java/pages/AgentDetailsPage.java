package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AgentDetailsPage extends BasePage {

    @FindBy(xpath = "//div[text()='Top Localities']")
    private WebElement topLocalitiesFilter;

    @FindBy(xpath = "//label[text()='New Town']")
    private WebElement newTownLabel;

    @FindBy(xpath = "(//div[@class='filter__component__cta-done' and text()='Done'])[2]")
    private WebElement doneButton;

    @FindBy(xpath = "//a[text()='Top Agents']")
    private WebElement topAgentsTab;

    @FindBy(xpath = "(//a[contains(text(),'View Details')])[1]")
    private WebElement viewDetailsLink;

    @FindBy(css = ".prefagent__detlsec__stats__num")
    private List<WebElement> agentStats;

    @FindBy(xpath = "//div[text()='Deals in']/following-sibling::div")
    private WebElement dealsIn;

    @FindBy(xpath = "//div[text()='Operates in']/following-sibling::div")
    private WebElement operatesIn;

    @FindBy(css = ".visibleDesc")
    private WebElement aboutAgent;

    public void applyLocalityFilter() throws InterruptedException {
        topLocalitiesFilter.click();
        Thread.sleep(10000);
        newTownLabel.click();
        Thread.sleep(10000);
        doneButton.click();
        Thread.sleep(10000);
    }

    public void clickTopAgents() throws InterruptedException {
        topAgentsTab.click();
        Thread.sleep(10000);
    }

    public void clickViewDetails() throws InterruptedException {
        viewDetailsLink.click();
        Thread.sleep(10000);
    }

    public boolean areAgentDetailsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfAllElements(agentStats));
        System.out.println("For Sale: "   + agentStats.get(0).getText());
        System.out.println("Deals: "      + agentStats.get(1).getText());
        System.out.println("Team: "       + agentStats.get(2).getText());
        System.out.println("Deals In: "   + dealsIn.getText());
        System.out.println("Operates In: "+ operatesIn.getText());
        System.out.println("About: "      + aboutAgent.getText());
        return !agentStats.get(0).getText().isEmpty()
            && !dealsIn.getText().isEmpty()
            && !operatesIn.getText().isEmpty();
    }
}