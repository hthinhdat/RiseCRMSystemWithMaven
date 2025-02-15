package testRCS.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

// Contructor
public class ValidateHelper {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private Actions actions;
    final int Timeout = 5;

    public ValidateHelper(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(Timeout));
        js = (JavascriptExecutor) driver;
    }

    public void setText(By element, String value) {
        try {
            WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(element));
            webElement.clear();
            webElement.sendKeys(value);
            System.out.println("Successfully set text: " + value);
        } catch (Exception e) {
            Assert.fail("Failed to set text for element: " + element + " - " + e.getMessage());
        }
    }

    public void clickElement(By element) {
        WebElement webElement = driver.findElement(element);
        js.executeScript("arguments[0].scrollIntoView(true);", webElement);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        Assert.assertTrue(webElement.isDisplayed(), "Cannot find element");

        try {
            // Thử click thông thường
            webElement.click();
            System.out.println("Click successful using WebElement Click.");
        } catch (Exception e1) {
            System.out.println("Standard click failed. Trying JavaScript click...");
            try {
                // Thử click bằng JavaScript
                js.executeScript("arguments[0].click();", webElement);
                System.out.println("Click successful using JavaScript Executor click after WebElement Click failed.");
            } catch (Exception e2) {
                System.out.println("JavaScript click also failed. Trying Actions class...");
                actions = new Actions(driver);
                try {
                    // Di chuyển chuột và click bằng Actions class
                    actions.moveToElement(webElement).click().perform();
                    // Nếu click thành công bằng Actions, log thông tin này
                    System.out.println("Click successful using Actions class after JavaScript Executor click failed.");
                } catch (Exception e3) {
                    // Nếu tất cả đều thất bại, log lỗi cuối cùng
                    System.out.println("Actions click also failed. All click attempts have failed.");
                }
            }
        }
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public boolean verifyTitle(String expectedTitlePage){
        System.out.println("Actual page title: " + getPageTitle() + " | Expected page title: " + expectedTitlePage);
        return getPageTitle().equals(expectedTitlePage);

    }

    public String getURLPage(){
        return driver.getCurrentUrl();
    }

    public boolean verifyURLPage(String expectedURLPage){
        System.out.println("Actual page URL: " + getURLPage() + " | Expected page URL: " + expectedURLPage);
        return getURLPage().equals(expectedURLPage);
    }

    public boolean verifyElementText(String actualPageText, String expectedPageText) {
        if (actualPageText == null || expectedPageText == null) {
            System.out.println("One of the page texts is null. Actual: " + actualPageText + " | Expected: " + expectedPageText);
            return false;
        }

        System.out.println("Actual page text: " + actualPageText + " | Expected page text: " + expectedPageText);
        return actualPageText.equals(expectedPageText);
    }


    public void selectOptionByText(By element, String text){
        // Chuyen doi tuong By element sang WebElement thi su dung driver.findElement(element)
        // By element => goi la element (locator) cua doi tuong By
        // WebElement element => goi la WebElement
        Select select = new Select(driver.findElement(element));
        select.selectByVisibleText(text);
    }

    public void selectOptionByValue(By element, String value){
        // Chuyen doi tuong By element sang WebElement thi su dung driver.findElement(element)
        Select select = new Select(driver.findElement(element));
        select.selectByValue(value);
    }

}
