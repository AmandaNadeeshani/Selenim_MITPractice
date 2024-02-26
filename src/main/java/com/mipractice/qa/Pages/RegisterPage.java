package com.mipractice.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

    WebDriver driver;
    @FindBy(id = "input-firstname")
    private WebElement firstNameInput;

    @FindBy(id = "input-lastname")
    private WebElement lastNameInput;

    @FindBy(id = "input-email")
    private WebElement emailInput;

    @FindBy(id = "input-telephone")
    private WebElement telephoneInput;

    @FindBy(id = "input-password")
    private WebElement passwordInput;

    @FindBy(id = "input-confirm")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement termsAndConditionsCheckbox;

    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//h1[contains(text(),'Your Account Has Been Created!')]")
    private WebElement successMessage;

    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void EnterFirstName(String fname){
        firstNameInput.sendKeys(fname);
    }

    public void enterLastName(String lname) {
        lastNameInput.sendKeys(lname);
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void enterTelephone(String telephone) {
        telephoneInput.sendKeys(telephone);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        confirmPasswordInput.sendKeys(password);
    }

    public void clickTermsAndConditionsCheckbox() {
        termsAndConditionsCheckbox.click();
    }

    public AccoundSuccessPage clickContinueButton() {
        continueButton.click();
        return new AccoundSuccessPage(driver);
    }

    public String getSuccessMessage() {
        return successMessage.getText();
    }


    public AccoundSuccessPage register(String fname, String lname, String email, String telephone, String password, String validpassword){
        firstNameInput.sendKeys(fname);
        lastNameInput.sendKeys(lname);
        emailInput.sendKeys(email);
        telephoneInput.sendKeys(telephone);
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(password);
        termsAndConditionsCheckbox.click();
        continueButton.click();
        return new AccoundSuccessPage(driver);
    }

}
