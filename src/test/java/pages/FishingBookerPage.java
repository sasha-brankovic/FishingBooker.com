package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FishingBookerPage extends BasePage{
    WebDriver driver;
    WebDriverWait wait;
    public FishingBookerPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "ul>div>li:nth-child(2)>div>div:nth-child(2)>div>div:nth-child(2)>button")
    WebElement buttonViewAvailabilityOfSingleFishermanSpecial;
    @FindBy(xpath = "(//td[@class='day'])[1]")
    WebElement firstAvailableDateForBooking;
    @FindBy(xpath = "(//tr/th[@class='next'])[1]")
    WebElement nextOnCalendar;
    @FindBy(css = ".search-form-persons:nth-child(3)")
    WebElement groupSize;
    @FindBy(xpath = "(//button[contains(@class, 'adults-minus')])[2]")
    WebElement buttonAdultsMinus;
    @FindBy(xpath = "(//button[contains(@class, 'children-plus')])[2]")
    WebElement buttonChildrenPlus;
    @FindBy(css = "div>button#check-availability-btn")
    WebElement checkAvailability;
    @FindBy(css = "div>div>div>#bookbtn-1")
    WebElement instantBookingSingleFishermanSpecial;
    @FindBy(xpath = "//input[@placeholder='Enter your first name']")
    WebElement firstName;
    @FindBy(xpath = "//input[@placeholder='Enter your last name']")
    WebElement lastName;
    @FindBy(xpath = "//input[@type='email']")
    WebElement email;
    @FindBy(xpath = "//input[@type='tel']")
    WebElement phone;
    @FindBy(css = "div>textarea")
    WebElement sayHelloToCaptain;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitContactForm;
    @FindBy(css = "#credit-card-number")
    WebElement inputCardNumber;
    @FindBy(css = "#expiration")
    WebElement inputCardExpiration;
    @FindBy(css = "form>#cvv")
    WebElement inputSecurityCode;
    @FindBy(css = "form>#cardholder-name")
    WebElement inputCardHolderName;
    @FindBy(css = "form>#postal-code")
    WebElement inputPostalCode;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement buttonConfirmBooking;
    @FindBy(xpath = "(//button[@type='button'])[10]")
    WebElement closeCreatePassword;
    @FindBy(xpath = "//div/div[2]/span[contains(text(), '#')]")
    WebElement selectIdText;

    public void clickToViewAvailabilityOfSingleFishermanSpecial() {
        usingJavaScriptScrollIntoView(buttonViewAvailabilityOfSingleFishermanSpecial);
        usingJavaScriptToClickOnElement(buttonViewAvailabilityOfSingleFishermanSpecial);
        while (true) {
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            List<WebElement> numberOfElementsForCheckInDate = driver.findElements(By.cssSelector("td[class='day']"));
            if (numberOfElementsForCheckInDate.size() > 0) {
                click(firstAvailableDateForBooking);
                break;
            } else {
                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                click(nextOnCalendar);
            }
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    public void enterAdditionalInfo(){
        click(groupSize);
        click(buttonAdultsMinus);
        click(buttonChildrenPlus);
        click(checkAvailability);
        click(instantBookingSingleFishermanSpecial);
    }
    public void enterContactDetails(String firstNameText, String lastNameText, String emailText, String phoneNumber, String greetingCaptainText) {
        typeText(firstName, firstNameText);
        typeText(lastName, lastNameText);
        typeText(email,emailText);
        typeText(phone, phoneNumber);
        typeText(sayHelloToCaptain, greetingCaptainText);
        click(submitContactForm);
    }
    public void enterCardInfoForPayment(String cardNumber, String cardExpiration,
                                        String cardSecurityCode, String cardHolderName, String postalCode){
        switchToIFrameWithFrameName("braintree-hosted-field-number");
        typeText(inputCardNumber, cardNumber);
        switchToParentFrame();
        switchToIFrameWithFrameName("braintree-hosted-field-expirationDate");
        typeText(inputCardExpiration, cardExpiration);
        switchToParentFrame();
        switchToIFrameWithFrameName("braintree-hosted-field-cvv");
        typeText(inputSecurityCode, cardSecurityCode);
        switchToParentFrame();
        switchToIFrameWithFrameName("braintree-hosted-field-cardholderName");
        typeText(inputCardHolderName, cardHolderName);
        switchToParentFrame();
        switchToIFrameWithFrameName("braintree-hosted-field-postalCode");
        typeText(inputPostalCode, postalCode);
        switchToParentFrame();
        click(buttonConfirmBooking);
    }
    public void copyAndWriteIntoFileBookingId() throws IOException, InterruptedException {
        String bookingId = selectIdText.getText();
        List <WebElement> createPasswordPopUp = driver.findElements(By.xpath("(//button[@type='button'])[10]"));
        if(createPasswordPopUp.size() > 0){
            click(closeCreatePassword);
        }
        FileWriter bookingIdFile = new FileWriter("bookingId/bookingId.txt");
        bookingIdFile.write(bookingId);
        bookingIdFile.close();
        Thread.sleep(1500);
    }
}
