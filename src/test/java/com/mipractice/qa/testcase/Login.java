package com.mipractice.qa.testcase;

import com.mipractice.qa.Pages.AccountPage;
import com.mipractice.qa.Pages.HomePage;
import com.mipractice.qa.Pages.LoginPage;
import com.mipractice.qa.base.Base;
import com.mipractice.qa.utils.utilities;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;
import java.util.Date;

public class Login extends Base {
    public WebDriver driver;
    LoginPage loginpage;
    HomePage homepage;

    @BeforeTest
    public void setup() throws IOException {


        loadPropertiesFile();
        driver = initializeBrwoser(prop.getProperty("browser")); // Call initializeBrowser method
        homepage = new HomePage(driver);
        loginpage = new LoginPage(driver);
        loginpage= homepage.navigateToLoginpage();
       // homepage.ClickonMyAccount();
        //homepage.SelectLoginOption();
    }
    @BeforeMethod
    public void navigate() throws IOException {
        loginpage= homepage.navigateToLoginpage();
        // homepage.ClickonMyAccount();
        //homepage.SelectLoginOption();
    }

    @Test(priority = 1,dataProvider ="validCredentiaslSupplier")
    public void Verifywithlogincredentials(String email, String password) {
        LOG.info("starting Verifywithlogincredentials()");

        loginpage.enterEmail(email);
        loginpage.passeordEnetr(password);
        //loginpage.loginclickbtn();
      //  AccountPage accountpage = new AccountPage(driver);
        //Assert.assertTrue(accountpage.getDosplaystatusofAccountInformationOtpion());
       // driver.findElement(By.xpath("//input[@value='Login']")).click();
// Check for warning message if the credentials are incorrect

     //   Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());


    }


    @Test(priority = 2)
    public void VerifywithInvalidlogincredentials() {

        loginpage.login(prop.getProperty("validEmail"),dataprop.getProperty("invalidpassword"));


        String actualwarning = loginpage.RetreiveEmailoasswordNotMatchingWaring();
        String expectedwarning=dataprop.getProperty("warningNotMatch");
        Assert.assertTrue(actualwarning.contains(expectedwarning));

        // Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());

    }


    @Test(priority = 3)
    public void VerifywithEmptycredentials() {


        driver.findElement(By.xpath("//input[@value='Login']")).click();

        String actualWarning = driver.findElement(By.xpath("//div[contains(@class,'alert alert-danger alert-dismissible')]")).getText();
        String expectedwarning=dataprop.getProperty("warningNotMatch");
        Assert.assertTrue(actualWarning.contains(expectedwarning));



    }

    @AfterTest
    public void tearDown() {

        driver.quit();
    }

    @DataProvider(name="validCredentiaslSupplier")
    public Object[][] supplyTestData() throws IOException, InvalidFormatException {
        Object[][] data = utilities.getTestDataFromExcel("Login");
        return  data;

    }

    public  String generateEmailwithtimeStamp(){
        Date date = new Date();
       String timestamp=  date.toString().replace("","_").replace(":","_");
      return "amanda"+timestamp+"@gmail.com";
    }
}
