package testRCS.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testRCS.base.TestBaseSetup;
import testRCS.base.ValidateHelper;
import testRCS.pages.DashboardPage;
import testRCS.pages.SignInPage;

public class SignInTest extends TestBaseSetup {

    private WebDriver driver;
    public SignInPage signInPage;
    public ValidateHelper validateHelper;
    public DashboardPage dashboardPage;

    @BeforeClass
    public void setUp(){
        driver = getDriver();
    }

    @Test
    public void signIn() throws InterruptedException {
        System.out.println(driver);
        signInPage = new SignInPage(driver);
        validateHelper = new ValidateHelper(driver);

        Assert.assertTrue(signInPage.verifySignIn(), "Can sign in page with bad parameters");
//        Assert.assertTrue(signInPage.verifySignInPageTitle(), "Page title is incorrect");
        String expectedSignInPageTitle = "Sign in | RISE CRM | Anh Tester Demo";
        Assert.assertTrue(validateHelper.verifyTitle(expectedSignInPageTitle), "Sign in page title doesn't match");


        signInPage.clickForgotPWD();
//        Assert.assertTrue(signInPage.verifyForgotPWDPageText(), "Cannot redirect to Rest Password page");
        String expectedURLResetPwdPage = "https://rise.anhtester.com/signin/request_reset_password";
        Assert.assertTrue(validateHelper.verifyURLPage(expectedURLResetPwdPage), "URL sign up page doesn't match");

        signInPage.returnSignInPage();

        signInPage.clickSignUp();
//        Assert.assertTrue(signInPage.verifySignUpPageText(), "Cannot redirect to Sign up page");
        String expectedURLSignUpPage = "https://rise.anhtester.com/signup";
        Assert.assertTrue(validateHelper.verifyURLPage(expectedURLSignUpPage), "URL sign up page doesn't match");

        signInPage.returnSignInPage();

        dashboardPage = signInPage.signIn("admin@example.com", "123456"); // liên kết Sign in page với Dashboard page
    }

    @Test(priority = 2)
    public void openEventPage() throws Exception {
//        dashboardPage = new DashboardPage(driver); // neu su dung lien ket page thi khong can khai bao doi tuong moi

        String expectedDashboardPageTitle = "Dashboard | RISE CRM | Anh Tester Demo";
        Assert.assertTrue(validateHelper.verifyTitle(expectedDashboardPageTitle), "Dashboard page title doesn't match");

        dashboardPage.openEventPage();
        String expectedEventPageTitle = "Events | RISE CRM | Anh Tester Demo";
        Assert.assertTrue(validateHelper.verifyTitle(expectedEventPageTitle), "Event page title doesn't match");

    }

    @AfterClass
    public void closeBrowser() {
        driver.close(); // Đóng trình duyệt sau khi tất cả các bài kiểm tra hoàn thành
    }

}
