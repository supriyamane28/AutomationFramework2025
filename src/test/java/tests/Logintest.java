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

        //rowcount-1 becoz in first row giving label like username and password.so these are not consider as data. [2] becoz we have 2 coloumns data
        Object[][] data=new Object[rowcount-1][2]; //check explanation above mentioned

        for(int i=1;i<rowcount;i++)
        {
            data[i-1][0]=Excelutils.getCellData(i,0);   //username
            data[i-1][1]=Excelutils.getCellData(i,1);    //password
        }
        Excelutils.closeExcel();
        return data;

    }


    @DataProvider(name="LoginData1")     //this is 2nd method to get data from Dataprovider method
    public Object[][] getData()
    {
        return new Object[][]
                {
                        {"username1","pass1"},
                        {"username2","pass2"},
                        {"user3name","pass3"}
                };

    }
    // now you can use .... @Test(dataProvider = "LoginData1") rest of code will be same

    //this is 3nd method to get data from testng1.xml file
    //if get data from testng1.xml file. so added parameter in that file
    //here how to use .check below commented code
    /*
     @Test
     @parameters({"username","password"})
     rest code will same for method => public void testvalidlogin(String username,String password)
     run the testng1.xml
     */

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


    @Test(dataProvider = "LoginData1")          //this test is created becoz to check screenshot if TC failed
    public void testinvalidlogin(String username,String password)
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
        }
        /*  commented code due to check test case failed ...and checking screenshot
        else
        {
            Log.error("❌ Test FAILED: Title did NOT match");
            Log.error("Expected: " + expected);
            Log.error("Actual:   " + actual);

        }

         */
    }
        @Test
        public void testFail()
    {
        driver.get("https://googleee.co...");
        Assert.fail("Failing intentionally");


    }




}
