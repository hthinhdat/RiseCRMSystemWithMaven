/**
 Đảm bảo IntelliJ IDEA tự động tải xuống source code và tài liệu của thư viện Maven
 Giúp dễ dàng xem code thư viện và đọc tài liệu API ngay trong IDE mà không cần tìm kiếm bên ngoài

 Cách thiết lập trong IntelliJ IDEA:
 1. Mở IntelliJ IDEA, vào File > Settings
 2. Điều hướng đến Build, Execution, Deployment > Build Tools > Maven > Importing
 3. Tích chọn "Automatically download sources and documentation"
 4. Nhấn Apply và OK để lưu cài đặt
 **/

package testRCS.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class TestBaseSetup {

    private WebDriver driver;
    static String driverPath = "resources\\driver\\chromedriver-win64\\";

    public WebDriver getDriver(){
    /**
     * Returns the WebDriver instance.
     * @return WebDriver instance
     */
        return driver;
    }

    public static WebDriver initChromeDriver(String appURL){
    /**
     * Initializes and returns a Chrome WebDriver instance.
     * @param appURL The URL to navigate to
     * @return Chrome WebDriver instance
     */
        System.out.println("Launching Chrome browser...");
        System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(appURL);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        return driver;
    }

    private static WebDriver initFirefoxDriver(String appURL) {
    /**
     * Initializes and returns a Firefox WebDriver instance.
     * @param appURL The URL to navigate to
     * @return Firefox WebDriver instance
     */
        System.out.println("Launching Firefox browser...");
        System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to(appURL);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        return driver;
    }

    private void setDriver(String browserType, String appURL){
    /**
     * Sets the WebDriver instance based on the browser type.
     * @param browserType The type of browser ("chrome" or "firefox")
     * @param appURL The URL to navigate to
     */
        switch(browserType){
            case "chrome":
                driver = initChromeDriver(appURL);
                break;
            case "firefox":
                driver = initFirefoxDriver(appURL);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
    }

    @Parameters({"browserType", "appURL"})
    @BeforeClass
    public void initializeTestBaseSetup(String browserType, String appURL){
    /**
     * Initializes the test setup before the test class execution.
     * @param browserType The type of browser to use
     * @param appURL The application URL to navigate to
     */
        try{
            System.out.println("Initializing Test Base Setup ...");
            setDriver(browserType, appURL);
        } catch (Exception e){
            System.out.println("Error initializing test base setup: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
    /**
     * Tears down the WebDriver instance after test execution.
     * @throws InterruptedException if thread sleep is interrupted
     */
        System.out.println("Closing browser...");
        Thread.sleep(2000);
        if(driver != null){
            driver.quit();
        }
    }
}
