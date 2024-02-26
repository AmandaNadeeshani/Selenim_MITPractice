package com.mipractice.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

    WebDriver driver;
    @FindBy(linkText = "Edit your account information")
    WebElement EditYourAccountInformationOption;


    public AccountPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //add a comment
    //actioon methods
    public boolean getDosplaystatusofAccountInformationOtpion(){
        boolean displayStatus = EditYourAccountInformationOption.isDisplayed();
        return  displayStatus;
    }


}
