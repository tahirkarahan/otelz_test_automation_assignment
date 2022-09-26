package com.otelz.pages;

import com.otelz.utilities.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReservationFirstPage {
    WebDriver driver;
    TestUtility testUtility;

    @FindBy(xpath = "//input[@name='customerInfo.name']")
    WebElement nameField;

    @FindBy(xpath = "//input[@name='customerInfo.surname']")
    WebElement surnameField;

    @FindBy(xpath = "//input[@name='customerInfo.email']")
    WebElement emailField;

    @FindBy(xpath = "//div[@class='dropdown']")
    WebElement regionDropdown;

    @FindBy(xpath = "//span[@class='flag_f_p tr']")
    WebElement targetRegion;

    @FindBy(id = "phoneInput")
    WebElement phoneNumberField;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitButton;

    public ReservationFirstPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        testUtility=new TestUtility(driver);
    }

    public void enterCustomerName(String name){
        testUtility.waitForElementPresent(nameField);
        nameField.sendKeys(name);
    }

    public void enterCustomerSurname(String surname){
        testUtility.waitForElementPresent(surnameField);
        surnameField.sendKeys(surname);
    }

    public void enterCustomerEmail(String email){
        testUtility.waitForElementPresent(emailField);
        emailField.sendKeys(email);
    }

    public void setCustomerPhoneNumber(String phoneNumber){
        testUtility.waitForElementPresent(regionDropdown);
        regionDropdown.click();
        testUtility.sleep(2);
        testUtility.waitForElementPresent(targetRegion);
        targetRegion.click();
        testUtility.sleep(2);
        testUtility.waitForElementPresent(phoneNumberField);
        phoneNumberField.sendKeys(phoneNumber);
    }

    public void inputCustomerInfo(String name, String surname, String email, String phoneNumber){
        enterCustomerName(name);
        enterCustomerSurname(surname);
        enterCustomerEmail(email);
        setCustomerPhoneNumber(phoneNumber);
    }

    public ReservationSecondPage clickOnSubmitButton(){
        testUtility.waitForElementPresent(submitButton);
        submitButton.click();
        testUtility.sleep(3);
        return new ReservationSecondPage(driver);
    }
}
