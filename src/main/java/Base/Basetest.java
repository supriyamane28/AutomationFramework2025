package Base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import utils.Log;

import java.io.File;

public class Basetest {

    protected WebDriver driver;

    @BeforeSuite
    public void cleanAllureReports() {
        File allureResults = new File("allure-results");

        if (allureResults.exists()) {
            for (File file : allureResults.listFiles()) {
                file.delete();
            }
            System.out.println("🧹 Old Allure results cleaned!");
        }
    }

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser)
    {
        Log.info("Starting WebDriver ...");
        //driver=new ChromeDriver();

        switch (browser.toLowerCase()) {

            case "chrome":
                Log.info("opening chrome ...");
                ChromeOptions c1=new ChromeOptions();
                c1.addArguments("--incognito");
                driver = new ChromeDriver(c1);
                break;

            case "firefox":
                Log.info("opening firefox ...");
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("-private");
                driver = new FirefoxDriver(options);
                break;

            case "edge":
                Log.info("opening edge ...");
                EdgeOptions option = new EdgeOptions();
                option.addArguments("-inprivate");
                driver = new EdgeDriver(option);
                break;

            default:
                Log.info("opening chrome due to invalid browser selection...");
                System.out.println("⚠ Invalid browser, launching Chrome by default!");
                driver = new ChromeDriver();
        }

        driver.manage().window().maximize();

        Log.info("redirect to URL page ...");
        driver.get("https://admin-demo.nopcommerce.com/login");

    }

    @AfterMethod
    public void tearDown()
    {
        if(driver!=null)
        {
            Log.info("closing WebDriver ...");
            driver.quit();
        }


    }


    public String takeScreenshot(String testName) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + testName + ".png";
        try {
            FileUtils.copyFile(src, new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    @AfterMethod
    public void getResult(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Test Failed! Screenshot Captured!");
            takeScreenshot(result.getName());
        }

        driver.quit();
    }
}
