package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Reporter;
import pages.FishingBookerPage;
import tests.BaseTest;

import java.io.IOException;

public class FishingBookerSteps extends BaseTest {
    @Before
    public void initialise(){
        int implicitlyWait = Integer.parseInt(Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("WAIT_TIME_IMPLICIT"));
        String browserVersion = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("BROWSER_VERSION");
        String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("BROWSER");
        init(browser, browserVersion, implicitlyWait);
    }

    @After
    public void tearDown() {
        quit();
    }

    @Given("I am on page Pike Predator Tour")
    public void iAmOnPagePikePredatorTour() {
        String url = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("URL");
        String username = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("USERNAME");
        String password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("PASSWORD");
        driver.get("https://"+username+":"+password+"@"+url);
    }

    @When("I get available termin for Single Fisherman Special")
    public void iGetAvailableTerminForSingleFishermanSpecial() {
        FishingBookerPage fishingBookerPage = new FishingBookerPage(driver, wait);
        fishingBookerPage.clickToViewAvailabilityOfSingleFishermanSpecial();
        fishingBookerPage.enterAdditionalInfo();
    }

    @And("I start with enter my details necessary for booking {string} {string} {string} {string} {string}")
    public void iStartWithEnterMyDetailsNecessaryForBookingFirstNameLastnameEmailPhoneGreetingCaptain(String firstNameText, String lastNameText, String emailText, String phoneNumber,
                                                                                                      String greetingCaptainText ) {
        FishingBookerPage fishingBookerPage = new FishingBookerPage(driver, wait);
        fishingBookerPage.enterContactDetails(firstNameText, lastNameText, emailText, phoneNumber, greetingCaptainText);
    }

    @And("I enter card info for payment {string} {string} {string} {string} {string}")
    public void iEnterCardInfoForPaymentCardNumberCardExpirationCardSecurityCodeCardSecurityCodeCardHolderNamePostalCode(String cardNumber, String cardExpiration,
                                                                                                                         String cardSecurityCode, String cardHolderName, String postalCode) {
        FishingBookerPage fishingBookerPage = new FishingBookerPage(driver, wait);
        fishingBookerPage.enterCardInfoForPayment(cardNumber, cardExpiration, cardSecurityCode, cardHolderName, postalCode);
    }

    @Then("I get my booking Id")
    public void iGetMyBookingId() throws IOException, InterruptedException {
        FishingBookerPage fishingBookerPage = new FishingBookerPage(driver, wait);
        fishingBookerPage.copyAndWriteIntoFileBookingId();
    }
}
