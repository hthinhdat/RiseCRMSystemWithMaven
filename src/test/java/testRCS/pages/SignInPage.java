package testRCS.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import testRCS.base.ValidateHelper;

import java.time.Duration;

public class SignInPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    public ValidateHelper validateHelper;

    private final By emailInput = By.xpath("//input[@id='email']");
    private final By passwordInput = By.xpath("//input[@id='password']");
    private final By signinBtn = By.xpath("//button[@type='submit']");
    private final By errorMsgText = By.className("alert-danger");
//    private By forgotPWDPageText = By.xpath("//label[@for='email']");
//    private By signUpPageText = By.xpath("//h2[normalize-space()='Sign up']");
    private final By hrefForgotPWD = By.xpath("//a[@href='https://rise.anhtester.com/signin/request_reset_password']");
    private final By hrefSignUp = By.xpath("//a[@href='https://rise.anhtester.com/signup']");
    private final By hrefSignIn = By.xpath("//a[@href='https://rise.anhtester.com/signin']");

    // **Constructor**
    // Khởi tạo class khi được gọi và truyền driver vào để các thành phần trong class này này đọc
    // this.driver ám chỉ biến toàn cục driver của class này. Dòng này gán giá trị của tham số driver (từ bên ngoài truyền vào) cho biến toàn cục this.driver.
    public SignInPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        js = (JavascriptExecutor) this.driver;
        validateHelper = new ValidateHelper(this.driver);
    }

//    public String getSignInPageTitle(){
//        return driver.getTitle();
//    }
//
//    public boolean verifySignInPageTitle(){
//        String expectedTitle = "Sign in | RISE CRM | Anh Tester Demo";
//        System.out.println("Actual page title: " + getSignInPageTitle() + " | Expected page title: " + expectedTitle);
//        return getSignInPageTitle().equals(expectedTitle);
//    }

    public void enterEmail(String email){
//        WebElement emailTxtBox = driver.findElement(emailInput);
//        if(emailTxtBox.isDisplayed()){
//            emailTxtBox.sendKeys(email);
//        }
//        Assert.assertTrue(emailTxtBox.isDisplayed(), "Cannot find email text box");
//        emailTxtBox.sendKeys(email);

        validateHelper.setText(emailInput, email);
    }

    public void enterPassword(String password){
//        WebElement passwordTxtBox = driver.findElement(passwordInput);
//        if(passwordTxtBox.isDisplayed()){
//            passwordTxtBox.sendKeys(password);
//        }
//        Assert.assertTrue(passwordTxtBox.isDisplayed(), "Cannot find password text box");
//        passwordTxtBox.sendKeys(password);

        validateHelper.setText(passwordInput, password);
    }

    public void clickSignIn(){
//        WebElement signInBtn = driver.findElement(signinBtn);
//        Assert.assertTrue(signInBtn.isDisplayed(), "Cannot find password sign in button");
//        signInBtn.click();
        validateHelper.clickElement(signinBtn);
    }

    public DashboardPage signIn(String username, String password) throws InterruptedException {
        enterEmail(username);
        enterPassword(password);
        clickSignIn();
        Thread.sleep(2000);

        return new DashboardPage(driver); // liên kết Sign in page với Dashboard page
    }

    public String getErrorMessage(){
        WebElement errorMsg = driver.findElement(errorMsgText);
        Assert.assertTrue(errorMsg.isDisplayed(), "Cannot find error message");
        return errorMsg.getText();
    }

    public boolean verifySignIn(){
        String expectedErrorMsg = "Authentication failed!";
        enterEmail("email_invaild@gmail.com");
        enterPassword("pass_invalid");
        clickSignIn();
        System.out.println("Actual error message: " + getErrorMessage() + " | Expected error message: " + expectedErrorMsg);
        return getErrorMessage().equals("Authentication failed!");
    }

    public void clickForgotPWD(){
//        WebElement ForgotPWD = driver.findElement(hrefForgotPWD);
//        Assert.assertTrue(ForgotPWD.isDisplayed(), "Cannot find forgot password");
//        ForgotPWD.click();
        validateHelper.clickElement(hrefForgotPWD);
    }

//    public String getURLPage(){
//        return driver.getCurrentUrl();
//    }
//
//    public boolean verifySignUpPageText(){
//        String expectedURLPage = "https://rise.anhtester.com/signup";
//        return getURLPage().contains(expectedURLPage);
//    }

    public void clickSignUp(){
//        WebElement signUp = driver.findElement(hrefSignUp);
//        Assert.assertTrue(signUp.isDisplayed(), "Cannot find forgot password");
//        signUp.click();
        validateHelper.clickElement(hrefSignUp);
    }

//    public boolean verifyForgotPWDPageText(){
//        WebElement element = driver.findElement(forgotPWDPageText);
//        String expectedURLPage = "https://rise.anhtester.com/signin/request_reset_password";
//        return getURLPage().equals(expectedURLPage);
//    }

    public void returnSignInPage(){
//        WebElement signIn = driver.findElement(hrefSignIn);
//        js.executeScript("arguments[0].scrollIntoView(true);", signIn);
//        Assert.assertTrue(signIn.isDisplayed(), "Cannot find sign in");
//        signIn.click();
        validateHelper.clickElement(hrefSignIn);
    }

    public void waitForPageLoaded() {
        try {
            // Đợi một giây để đảm bảo tài liệu đã tải được một phần
            Thread.sleep(1000);

            // Chờ đợi trang web tải xong (trạng thái 'complete')
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            // Chuyển đối tượng driver thành một đối tượng JavascriptExecutor, cho phép bạn thực thi mã JavaScript trong trình duyệt.
            wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete"));
        } catch (Exception e) {
            // Nếu quá thời gian chờ mà trang không tải xong, báo lỗi
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }


}
