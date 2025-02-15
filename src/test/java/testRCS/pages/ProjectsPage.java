package testRCS.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import testRCS.base.ValidateHelper;

public class ProjectsPage {

    private WebDriver driver;
    public ValidateHelper validateHelper;
    private By headerPageText = By.tagName("h1");
    private By addProjectBtn = By.xpath("//a[@title='Add project']");

    public ProjectsPage(WebDriver driver){
        this.driver = driver;
        validateHelper = new ValidateHelper(driver);
    }

    public AddProjectPage addProject(){
        String pageText = "Projects";
        Assert.assertTrue(validateHelper.verifyElementText(pageText, driver.findElement(headerPageText).getText()), "The actual text does not match the expected text.");
        validateHelper.clickElement(addProjectBtn);

        return new AddProjectPage(driver);
    }

}
