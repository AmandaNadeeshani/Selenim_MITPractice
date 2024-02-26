package com.mipractice.qa.testcase;

import com.mipractice.qa.Pages.HomePage;
import com.mipractice.qa.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class Search extends Base {
    public WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException {
        loadPropertiesFile();
        driver = initializeBrwoser(prop.getProperty("browser"));

        // driver.findElement(By.linkText("HP LP3065")).click();


    }

    @Test(priority = 1)
    public void verifySearchWithExistingProduct() {

        HomePage homePage = new HomePage(driver);
        homePage.entersearchboxtoproduct(dataprop.getProperty("validProduct"));
        homePage.searchbtn();
        //driver.findElement(By.xpath("//span/button[@type='button']")).click();

        Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
    }


    @Test(priority = 2)
    public void verifySearchWithInvalidProduct() {
        driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(dataprop.getProperty("invalidProduct"));
        driver.findElement(By.xpath("//span/button[@type='button']")).click();
        String actualSearchMsg = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
        Assert.assertEquals(actualSearchMsg, dataprop.getProperty("NoProductWarningMsg"), "no such msg poduct in search result");
    }

    @Test(priority = 3)
    public void verifyserachwithoutAnyProduct(){
        //driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys();
        driver.findElement(By.xpath("//span/button[@type='button']")).click();
        String actualSearchMsg = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
        Assert.assertEquals(actualSearchMsg, dataprop.getProperty("NoProductWarningMsg"), "no such msg poduct in search result");

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }



}
