package testRCS.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import testRCS.base.ValidateHelper;

public class DashboardPage {
    private WebDriver driver;
    public ValidateHelper validateHelper;
    private By hrefEventPage = By.xpath("//a[@href='https://rise.anhtester.com/events']");
    private By hrefProjectpage = By.xpath("//a[@href='https://rise.anhtester.com/projects/all_projects']");


    // **Constructor**
    // Khởi tạo class khi được gọi và truyền driver vào để các thành phần trong class này này đọc
    // this.driver ám chỉ biến toàn cục driver của class này. Dòng này gán giá trị của tham số driver (từ bên ngoài truyền vào) cho biến toàn cục this.driver.
    public DashboardPage(WebDriver driver){
        this.driver = driver;
        validateHelper = new ValidateHelper(driver);
    }

    public void openEventPage(){
        String expectedURLEventsPage = "https://rise.anhtester.com/events";
        Assert.assertTrue(validateHelper.verifyURLPage(expectedURLEventsPage));
        validateHelper.clickElement(hrefEventPage);

    }

    public ProjectsPage openProjectPage(){
        validateHelper.clickElement(hrefProjectpage);

        return new ProjectsPage(driver); // liên kết Dashboard page với Projects page
    }




}
