package testRCS.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testRCS.base.TestBaseSetup;
import testRCS.base.ValidateHelper;
import testRCS.pages.AddProjectPage;
import testRCS.pages.DashboardPage;
import testRCS.pages.ProjectsPage;
import testRCS.pages.SignInPage;

public class ProjectTest extends TestBaseSetup {

    private WebDriver driver;
    private ValidateHelper validateHelper;
    private SignInPage signInPage;
    private DashboardPage dashboardPage;
    private ProjectsPage projectsPage;
    private AddProjectPage addProjectsPage;

    @BeforeClass
    public void setupBrowser() {
        driver = getDriver(); // Lấy đối tượng WebDriver từ lớp cơ sở TestBaseSetup
    }

    @Test(priority = 1)
    public void signIn() throws InterruptedException {
        signInPage = new SignInPage(driver); // Khởi tạo trang SignInPage
        validateHelper = new ValidateHelper(driver); // Khởi tạo đối tượng ValidateHelper để hỗ trợ kiểm tra
        dashboardPage = signInPage.signIn("admin@example.com", "123456"); // liên kết SignInPage với DashboardPage sau khi đăng nhập thành công
    }

    @Test(priority = 2)
    public void openProjectsPage() throws InterruptedException {
//        dashboardPage = new DashboardPage(driver); // Đã khởi tạo DashboardPage trong phương thức signIn(), không cần khởi tạo lại
        String expectedURLDashboardPage = "https://rise.anhtester.com/dashboard/index/1";
        Assert.assertTrue(validateHelper.verifyURLPage(expectedURLDashboardPage)); // Kiểm tra URL hiện tại có đúng là của DashboardPage hay không

        projectsPage = dashboardPage.openProjectPage(); // liên kết DashboardPage với ProjectsPage, chuyển hướng đến trang ProjectsPage
        String expectedURLProjectsPage = "https://rise.anhtester.com/projects/all_projects";
        Assert.assertTrue(validateHelper.verifyURLPage(expectedURLProjectsPage)); // Kiểm tra URL hiện tại có đúng là của ProjectsPage hay không
    }

    @Test(priority = 3)
    public void openAddProjectsPage() throws InterruptedException {
//        projectsPage = new ProjectsPage(driver); // Vì đang sử dụng tính năng liên kết page nên không cần khởi tạo đối tượng ProjectsPage
        addProjectsPage = projectsPage.addProject(); // Mở trang thêm dự án mới từ ProjectsPage
    }

    @Test(priority = 4)
    public void AddProjectsPage() throws InterruptedException {
//        addProjectsPage = new AddProjectsPage(driver); // Vì đang sử dụng tính năng liên kết page nên không cần khởi tạo đối tượng ProjectsPage
        addProjectsPage.saveProject();
    }

    @AfterClass
    public void closeBrowser() {
        driver.close(); // Đóng trình duyệt sau khi tất cả các bài kiểm tra hoàn thành
    }
}
