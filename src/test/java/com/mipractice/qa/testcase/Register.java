package com.mipractice.qa.testcase;

import com.mipractice.qa.Pages.AccoundSuccessPage;
import com.mipractice.qa.Pages.HomePage;
import com.mipractice.qa.Pages.RegisterPage;
import com.mipractice.qa.base.Base;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.text.Utilities;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import static com.mipractice.qa.utils.utilities.generateEmailwithtimeStamp;

public class Register extends Base {

    RegisterPage registerpage;
    public WebDriver driver;

    @BeforeMethod
    public void setUp() throws IOException {
        loadPropertiesFile();
        driver = initializeBrwoser(prop.getProperty("browser"));
        HomePage homepage = new HomePage(driver);
        homepage.ClickonMyAccount();
        registerpage = homepage.selectRegisterOption();
    }

    //https://youtu.be/5OBXZMiuYnY
    @Test(priority = 1)
    public void verifyRegisteraccountwithMandatoryFields() {

        AccoundSuccessPage accoundSuccessPage = registerpage.register(dataprop.getProperty("firstname"), dataprop.getProperty("lastname"), generateEmailwithtimeStamp(), dataprop.getProperty("telephoneNumber"), prop.getProperty("validpassword"), prop.getProperty("validpassword"));

        //AccoundSuccessPage accoundSuccessPage = new AccoundSuccessPage(driver);

        String actualText = accoundSuccessPage.retriveAccpuntSuccessPageHeading();
        Assert.assertEquals(actualText, "Your Account Has Been Created!");

    }

    @Test(priority = 2)
    public void verifyRegisteraccountwithAllFields() {

        RegisterPage registerpage = new RegisterPage(driver);
        registerpage.EnterFirstName(dataprop.getProperty("firstname"));
        registerpage.enterLastName(dataprop.getProperty("lastname"));
        registerpage.enterEmail(generateEmailwithtimeStamp());
        registerpage.enterTelephone(dataprop.getProperty("telephoneNumber"));
        registerpage.enterPassword(prop.getProperty("validpassword"));
        registerpage.enterConfirmPassword(prop.getProperty("validpassword"));
        driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
        registerpage.clickTermsAndConditionsCheckbox();
        registerpage.clickContinueButton();
        AccoundSuccessPage accoundSuccessPage = new AccoundSuccessPage(driver);

        String actualText = accoundSuccessPage.retriveAccpuntSuccessPageHeading();
        Assert.assertEquals(actualText, "Your Account Has Been Created!");

    }


    @Test(priority = 3)
    public void verifyRegisteraccountwithexsistingEmail() {
        RegisterPage registerpage = new RegisterPage(driver);
        registerpage.EnterFirstName(dataprop.getProperty("firstname"));
        registerpage.enterLastName(dataprop.getProperty("lastname"));
        driver.findElement(By.xpath(" //input[@id='input-email']")).sendKeys(prop.getProperty("validEmail"));
        registerpage.enterTelephone(dataprop.getProperty("telephoneNumber"));
        registerpage.enterPassword(prop.getProperty("validpassword"));
        registerpage.enterConfirmPassword(prop.getProperty("validpassword"));
        registerpage.clickTermsAndConditionsCheckbox();
        registerpage.clickContinueButton();

        String actualwarning = driver.findElement(By.xpath("//div[contains(@class,'alert alert-danger alert-dismissible')]")).getText();
        Assert.assertTrue(actualwarning.contains(dataprop.getProperty("duplicateEmailWarning")));
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}
