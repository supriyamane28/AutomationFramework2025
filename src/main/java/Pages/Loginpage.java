package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Log;

public class Loginpage {

    private WebDriver driver;

    private By usernametextbox=By.id("Email");
    private By passwordtextbox=By.id("Password");
    private By loginbutton=By.xpath("//button[text()='Log in']");

    public Loginpage(WebDriver driver)
    {
        this.driver=driver;

    }

    public void enterusername(String username)
    {
        Log.info("entering username.."+username);
        driver.findElement(usernametextbox).clear();
        driver.findElement(usernametextbox).sendKeys(username);
    }

    public void enterpassword(String password)
    {
        Log.info("entering password.."+password);
        driver.findElement(passwordtextbox).clear();
        driver.findElement(passwordtextbox).sendKeys(password);
    }

    public void clickonlogin()
    {
        Log.info("Clicking login button..");
        driver.findElement(loginbutton).click();
    }


}
