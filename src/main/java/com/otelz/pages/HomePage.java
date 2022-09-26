package com.otelz.pages;

import com.otelz.utilities.Log;
import com.otelz.utilities.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    TestUtility testUtility;
    Actions actions;
    String config="config.properties";
//    String excelFile="Test_data/testdata.xlsx";


    @FindBy(css = ".active")
    WebElement otellerButton;

    @FindBy(xpath = "//*[@data-testid='cookieAccept']")
    WebElement acceptCookieButton;

    @FindBy(css = ".search-input")
    WebElement searchBox;

    @FindBy(xpath = "(//ul[@data-section='locationSeach']/li)[1]")
    WebElement targetLocationName;

    @FindBy(css = ".type_0.type_1.datepicker-opener")
    WebElement selectDateBox;

    @FindBy(xpath = "(//div[@aria-label=\"Choose 26 Eylül 2022 Pazartesi\"])[1]")
    WebElement entranceDate;

    @FindBy(xpath = "(//div[@aria-label=\"Choose 28 Eylül 2022 Çarşamba\"])[1]")
    WebElement checkOutDate;

    @FindBy(css=".btn.btn.btn-primary.search-btn")
    WebElement searchButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        testUtility=new TestUtility(driver);
        actions=new Actions(driver);
    }

    public void acceptCookie(){
        testUtility.waitForElementPresent(acceptCookieButton);
        acceptCookieButton.click();
    }

    String expectedTitle = TestUtility.readFromConfigProperties(config,"title");
    public boolean verifyHomePageOpened(){
        if(driver.getTitle() != null && driver.getTitle().contains(expectedTitle)){
            System.out.println("Home page is opened");
          Log.info("Home page is opened Successfully!!!");

            return true;
        }
        else{
            System.out.println("Home page could not open.");
           Log.error("Home page could not open");
            return false;
        }
    }

    public void enterLocationName(String cityName){
        testUtility.waitForElementPresent(searchBox);
        searchBox.sendKeys(cityName);
        testUtility.sleep(2);
      Log.info("Location name entered into search box.");
    }

     public boolean verifyLocationName(String cityName){
        return searchBox.getAttribute("value").equals(cityName);
     }

     public void deleteExistLocName(){
        testUtility.waitForElementPresent(searchBox);
        searchBox.clear();
        Log.info("Exist location name was cleared.");
     }

    public void enterSecondLocName(String cityName){
        testUtility.waitForElementPresent(searchBox);
        searchBox.sendKeys(cityName);
        testUtility.waitForElementPresent(targetLocationName);
        Actions actions=new Actions(driver);
        actions.click(targetLocationName).build().perform();
        testUtility.sleep(2);
       Log.info("Second location name entered into search box.");
    }

    public void selectDate(){
        testUtility.waitForElementPresent(selectDateBox);
        selectDateBox.click();
        testUtility.sleep(2);
        testUtility.waitForElementPresent(entranceDate);
        entranceDate.click();
        testUtility.sleep(2);
        testUtility.waitForElementPresent(checkOutDate);
        checkOutDate.click();
        testUtility.sleep(2);
        Log.info("Entrance and checkout date selected");
    }

    public HotelPage clickOnSearchButton(){
        testUtility.waitForElementPresent(searchButton);
        searchButton.click();
        testUtility.sleep(3);
        return new HotelPage(driver);
    }

}
