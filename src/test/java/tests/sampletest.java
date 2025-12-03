package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class sampletest {

    @Test
    public void url()
    {
        WebDriver driver=new FirefoxDriver();
        driver.get("https://admin-demo.nopcommerce.com/login");
    }
}
