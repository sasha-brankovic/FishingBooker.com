package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class BasePage {
    WebDriverWait wait;
    WebDriver driver;
    String waitTimeExplicit = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("WAIT_TIME_EXPLICIT");
    int waitExplicit = Integer.parseInt(waitTimeExplicit);

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    private void waitUntil(WebElement element){
        wait = new WebDriverWait(driver, waitExplicit);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void typeText(WebElement element, String text){
        waitUntil(element);
        try {
            if(text != null) {
                element.clear();
                element.sendKeys(text);
            } else {
                System.out.println("Parameter was null!");
            }
        }catch (StaleElementReferenceException e){
            if(text != null) {
                element.clear();
                element.sendKeys(text);
            } else {
                System.out.println("Parameter was null!");
            }
        }
    }

    public void click(WebElement element) {
        waitUntil(element);
        try{
            Actions actions = new Actions(driver);
            actions.moveToElement(element).build().perform();
            element.click();
        }catch(StaleElementReferenceException e){
            Actions actions = new Actions(driver);
            actions.moveToElement(element).build().perform();
            element.click();
        }
    }

    public void usingJavaScriptToClickOnElement(WebElement element){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        try{
            jse.executeScript("arguments[0].click()", element);
        }catch(StaleElementReferenceException e){
            jse.executeScript("arguments[0].click()", element);
        }
    }

    public void usingJavaScriptScrollIntoView(WebElement element){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        try{
            jse.executeScript("arguments[0].scrollIntoView(true)", element);
        }catch(StaleElementReferenceException e){
            jse.executeScript("arguments[0].scrollIntoView(true)", element);
        }
    }

    public void switchToIFrameWithFrameName(String iFrameName){
        driver.switchTo().frame(iFrameName);
    }

    public void switchToParentFrame(){
        driver.switchTo().parentFrame();
    }
}
