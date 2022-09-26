import com.otelz.pages.*;
import com.otelz.utilities.TestBase;
import com.otelz.utilities.TestUtility;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNGTestRunner extends TestBase {
    final static String config = "config.properties";
    HomePage homePage;
    HotelPage hotelPage;
    HotelInfoPage hotelInfoPage;
    ReservationFirstPage reservationFirstPage;
    ReservationSecondPage reservationSecondPage;
    TestUtility testUtility;
    String locName1 = TestUtility.readFromConfigProperties(config, "cityName1");
    String locName2 = TestUtility.readFromConfigProperties(config, "cityName2");

    @BeforeClass
    public void setUp() {
        String url = TestUtility.readFromConfigProperties(config, "url");
        browserSetUp(url);
        homePage = new HomePage(driver);
        testUtility = new TestUtility(driver);
    }

    @Test(dataProvider = "customer_info")
    public void testOtelzWeb(String name, String surname, String email, String phoneNumber) {
        homePage.acceptCookie();
        Assert.assertTrue(homePage.verifyHomePageOpened());
        homePage.enterLocationName(locName1);
        Assert.assertTrue(homePage.verifyLocationName(locName1));
        homePage.deleteExistLocName();
        homePage.enterSecondLocName(locName2);
        homePage.selectDate();

        hotelPage = homePage.clickOnSearchButton();
        String priceFromHotelPage = hotelPage.getHotelPrice();

        hotelInfoPage = hotelPage.clickOnFirstHotel();
        Assert.assertEquals(priceFromHotelPage, hotelInfoPage.getNetPrice());
        hotelInfoPage.selectRoom();

        reservationFirstPage = hotelInfoPage.clickOnReservationButton();
        reservationFirstPage.inputCustomerInfo(name, surname, email, phoneNumber);

        reservationSecondPage = reservationFirstPage.clickOnSubmitButton();
        Assert.assertTrue(reservationSecondPage.verifyOnlinePayment());

    }

    @DataProvider
    public Object[][] customer_info() {
        Object[][] userData = new Object[][]{
                {testUtility.readFromExcelCell("test_data/customer_info.xls",
                        "sheet1", 1, 0),
                        testUtility.readFromExcelCell("test_data/customer_info.xls",
                                "sheet1", 1, 1),
                        testUtility.readFromExcelCell("test_data/customer_info.xls",
                                "sheet1", 1, 2),
                        testUtility.readFromExcelCell("test_data/customer_info.xls",
                                "sheet1", 1, 3)}
        };
        return userData;
    }

    @AfterClass
    public void tearDown() {
        closeBrowser();
    }
}
