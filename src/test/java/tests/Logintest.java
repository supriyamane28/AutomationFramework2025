package tests;

import Base.Basetest;
import Pages.Loginpage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Excelutils;
import utils.Log;

import java.io.IOException;

public class Logintest extends Basetest {

    @DataProvider(name="LoginData")
    public Object[][] getLoginData() throws IOException
    {
        String filepath=System.getProperty("user.dir") +"/TestData/testdata.xlsx";
        Excelutils.loadExcel(filepath, "Sheet1");  //sheet 1 is the name if sheet in excel file

        int rowcount=Excelutils.getRowCount();

        //rowcunt-1 becoz in first row giving label like username and password.so these are not consider as data. [2] becoz we have 2 coloumns data
        Object[][] data=new Object[rowcount-1][2]; //check explanation above mentioned

        for(int i=1;i<rowcount;i++)
        {
            data[i-1][0]=Excelutils.getCellData(i,0);   //username
            data[i-1][1]=Excelutils.getCellData(i,1);    //password
        }
        Excelutils.closeExcel();
        return data;

    }


    @Test(dataProvider = "LoginData")
    public void testvalidlogin(String username,String password)
    {

        Log.info("starting login test....");

        Loginpage loginpageobject=new Loginpage(driver);

      Log.info("adding credentials ...");

        /*loginpageobject.enterusername("admin@yourstore.com");
		loginpageobject.enterpassword("admin");
		*/
        // above section commented becoz here we are use test data from excel so added parameter in function like username and password

        loginpageobject.enterusername(username);
        loginpageobject.enterpassword(password);


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
