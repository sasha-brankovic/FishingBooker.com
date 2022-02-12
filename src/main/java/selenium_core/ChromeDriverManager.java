package selenium_core;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Locale;

public class ChromeDriverManager extends DriverManager{
    @Override
    public void createWebDriver(String browserVersion) {
        String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if(OS.contains("windows")){
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver"+browserVersion+".exe");
        }else if(OS.contains("linux") || OS.contains("ubuntu") || OS.contains("debian") || OS.contains("deepin")){
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver"+browserVersion+"-linux");
        }else{
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver"+browserVersion+"-macos");
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        this.driver = new ChromeDriver(options);
    }
}
