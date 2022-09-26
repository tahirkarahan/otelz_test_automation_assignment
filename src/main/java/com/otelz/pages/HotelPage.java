package com.otelz.pages;

import com.otelz.utilities.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelPage {
    WebDriver driver;
    TestUtility testUtility;

    @FindBy(xpath = "//div[@data-testid='otel-1']//div[@class=\"price\"]")
    WebElement hotelPrice;

    @FindBy(xpath = "//div[@data-testid='otel-1']//span[@class=\"total\"]")
    WebElement totalPrice;

    public HotelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        testUtility=new TestUtility(driver);
    }

    public String getHotelPrice(){

        String total = "";
        try {
            testUtility.waitForElementPresent(totalPrice);
            total = totalPrice.getText();
        } catch (Exception e) {
        }
        testUtility.waitForElementPresent(hotelPrice);
        String targetPrice=hotelPrice.getText().replace(total, "").trim();
        System.out.println("the first hotel price is:"+targetPrice);
        return targetPrice;
    }

    public HotelInfoPage clickOnFirstHotel(){
        testUtility.waitForElementPresent(hotelPrice);
        hotelPrice.click();
        return new HotelInfoPage(driver);
    }
}
