package com.mipractice.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    @FindBy(id = "input-email")
    private WebElement emailAddress;
    @FindBy(id = "input-password")
    private WebElement passwordField;
    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginbtn;


    @FindBy(xpath = "//div[contains(@class,'alert alert-danger alert-dismissible')]")
    private WebElement warningFornotmatchcredentiasl;

    //creating constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //actions methods
    public void enterEmail(String emailText) {
        emailAddress.sendKeys(emailText);

    }

    public void passeordEnetr(String password) {
        passwordField.sendKeys(password);
    }

    public AccountPage loginclickbtn() {
        loginbtn.click();
        return new AccountPage(driver);
    }

    public AccountPage login(String email, String password){
        emailAddress.sendKeys(email);
        passwordField.sendKeys(password);
        loginbtn.click();
        return new AccountPage(driver);
    }


    public String RetreiveEmailoasswordNotMatchingWaring() {
        String warningText = warningFornotmatchcredentiasl.getText();
        return warningText;
    }


}
