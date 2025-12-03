package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Log;

public class Basetest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp()
    {
        Log.info("Starting WebDriver ...");
        driver=new ChromeDriver();

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
}
