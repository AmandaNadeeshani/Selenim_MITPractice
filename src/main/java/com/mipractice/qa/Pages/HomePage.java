package com.mipractice.qa.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    //objecs
    public WebDriver driver;
    @FindBy(xpath = "//span[text()='My Account']")
    private WebElement myAccountDropMenu;

    @FindBy(linkText = "Login")
    private WebElement LoginOption;

    @FindBy(linkText = "Register")
    private WebElement registerOption;

    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchboxoption;

    @FindBy(xpath = "//span/button[@type='button']")
    private WebElement searchbtn;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Actions
    public void ClickonMyAccount() {
        myAccountDropMenu.click();
    }

    public LoginPage SelectLoginOption() {
        LoginOption.click();
        return new LoginPage(driver);


    }

    public  LoginPage navigateToLoginpage(){
        myAccountDropMenu.click();
        LoginOption.click();
        return new LoginPage(driver);
    }
    public RegisterPage selectRegisterOption() {

        registerOption.click();
        return new RegisterPage(driver);
    }

    public void entersearchboxtoproduct(String productname){
        searchboxoption.sendKeys(productname);
    }

    public void searchbtn(){
        searchbtn.click();
    }
}


