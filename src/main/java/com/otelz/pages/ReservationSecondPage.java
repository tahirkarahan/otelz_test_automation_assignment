package com.otelz.pages;

import com.otelz.utilities.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReservationSecondPage {
    WebDriver driver;
    TestUtility testUtility;

    @FindBy(xpath = "//span[contains(text(),'Online öde')]")
    WebElement onlinePaymentOption;

    public ReservationSecondPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        testUtility=new TestUtility(driver);
    }

    public boolean verifyOnlinePayment(){
        return onlinePaymentOption.isDisplayed();
    }
}
