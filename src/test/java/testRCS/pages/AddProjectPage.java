package testRCS.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import testRCS.base.ValidateHelper;

import java.time.Duration;

// Boi vi Dialog Add Project co nhieu element nen tach ra thanh 1 class rieng
// Luu y: Day khong phai la 1 page rieng vi "Add Project" nam trong page Projects
public class AddProjectPage {

    private final WebDriver driver;
    private final ValidateHelper validateHelper;
    private final Actions actions;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;
    final int Timeout = 5;

    private final By headerPageText = By.xpath("//h4[@id='ajaxModalTitle']");
    private final By titleInput = By.xpath("//input[@id='title']");
    private final By projectTypeDropdown = By.xpath("//div[@id='s2id_project-type-dropdown']");
//    private final By projectTypeInput = By.xpath("//div[@id='select2-drop']/div[@class='select2-search']/input[@class='select2-input']");
    private final By projectTypeInput = By.xpath("//div[@id='select2-drop']/div/input");

    private final By clientDropdown = By.xpath("//div[@id='clients-dropdown']//div[@class=' col-md-9']");
//    private final By clientInput = By.xpath("//div[@id='select2-drop']/div[@class='select2-search']/input[@class='select2-input']");
    private final By clientInput = By.xpath("//div[@id='select2-drop']/div/input");

    private final By descriptionInput = By.xpath("//textarea[@id='description']");
    private final By startDateInput = By.xpath("//input[@id='start_date']");
    private final By datelineInput = By.xpath("//input[@id='deadline']");
    private final By priceInput = By.xpath("//input[@id='price']");
    private final By projectLabelDropdown = By.xpath("//div[@id='s2id_project_labels']//input[@class='select2-input select2-default']");

    private final By submitBtn = By.xpath("//button[@type='submit']");

    public AddProjectPage(WebDriver driver){
        this.driver = driver;
        validateHelper = new ValidateHelper(this.driver);
        actions = new Actions(this.driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(Timeout));
        js = (JavascriptExecutor) driver;
    }

    public void saveProject() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(headerPageText)));
        String expectedHeaderPageText = "Add project";
        String actualHeaderPageText = driver.findElement(headerPageText).getText();
        Assert.assertTrue(validateHelper.verifyElementText(actualHeaderPageText, expectedHeaderPageText), "The actual text does not match the expected text.");

        driver.findElement(titleInput).sendKeys("Test Automation");

        driver.findElement(projectTypeDropdown).click();
        driver.findElement(projectTypeInput).sendKeys("Client Project");
        actions.sendKeys(Keys.ENTER).build().perform();

        driver.findElement(clientDropdown).click();
        driver.findElement(clientInput).sendKeys("AMT");
        actions.sendKeys(Keys.ENTER).build().perform();

        driver.findElement(descriptionInput).sendKeys("This is a description");

        driver.findElement(startDateInput).sendKeys("2025-01-12");

        driver.findElement(datelineInput).sendKeys("2025-01-24");

        driver.findElement(priceInput).sendKeys("123$");

        WebElement webElementProjectLabel = driver.findElement(projectLabelDropdown);
        js.executeScript("arguments[0].scrollIntoView(true);", webElementProjectLabel);
        webElementProjectLabel.sendKeys("Public");

        validateHelper.clickElement(submitBtn);
    }

}
