package com.mipractice.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccoundSuccessPage {

    WebDriver driver;

    @FindBy(xpath ="//h1[contains(text(),'Your Account Has Been Created!')]")
    private WebElement accoutSuccessPageheading;

    public AccoundSuccessPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String retriveAccpuntSuccessPageHeading() {
        String accounysuceespagetxt = accoutSuccessPageheading.getText();
        return accounysuceespagetxt;
    }


}
