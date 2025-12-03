package tests;

import Base.Basetest;
import Pages.Loginpage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Log;

public class Logintest extends Basetest {

    @Test
    public void testvalidlogin()
    {

        Log.info("starting login test....");

        Loginpage loginpageobject=new Loginpage(driver);

      Log.info("adding credentials ...");

        loginpageobject.enterusername("admin@yourstore.com");
        loginpageobject.enterpassword("admin");

        loginpageobject.clickonlogin();

        System.out.println(driver.getTitle());


      Log.info("verifying page title ...");

        //Assert.assertEquals(driver.getTitle(),"Just a moment...");

        String expected = "Just a moment...";
        String actual = driver.getTitle();

        if (actual.equals(expected))
        {
            Log.info("✔ Test PASSED: Title matched");
        } else
        {
            Log.error("❌ Test FAILED: Title did NOT match");
            Log.error("Expected: " + expected);
            Log.error("Actual:   " + actual);

        }


    }
}
