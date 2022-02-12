package selenium_core;

import org.openqa.selenium.edge.EdgeDriver;

import java.util.Locale;

public class EdgeDriverManager extends DriverManager{
    @Override
    public void createWebDriver(String browserVersion) {

        String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if(OS.contains("windows")){
            System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver"+browserVersion+".exe");
        }else if(OS.contains("linux") || OS.contains("ubuntu") || OS.contains("debian") || OS.contains("deepin")){
            System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver"+browserVersion+"-linux");
        }else{
            System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver"+browserVersion+"-macos");
        }
        this.driver = new EdgeDriver();
        this.driver.manage().window().maximize();
    }
}