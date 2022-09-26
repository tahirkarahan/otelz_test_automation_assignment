package com.otelz.pages;

import com.otelz.utilities.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HotelInfoPage {
    WebDriver driver;
    TestUtility testUtility;

    @FindBy(css = ".net-price")
    WebElement netPrice;

    @FindBy(xpath = "(//*[@class=\"roomSelectBox\"])[1]")
    WebElement roomCheckBox;

    @FindBy(xpath = "//*[@id=\"totalPrice\"]/button")
    WebElement reservationButton;

    public HotelInfoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        testUtility=new TestUtility(driver);
    }

    public String getNetPrice(){
        testUtility.waitForElementPresent(netPrice);
        System.out.println("the net price of room is:"+netPrice.getText().trim());
        return netPrice.getText().trim();
    }

    public void selectRoom(){
        Select select=new Select(roomCheckBox);
        select.selectByValue("1");
        testUtility.sleep(2);
    }

    public ReservationFirstPage clickOnReservationButton(){
        testUtility.waitForElementPresent(reservationButton);
        reservationButton.click();
        testUtility.sleep(2);
        return new ReservationFirstPage(driver);
    }

}
