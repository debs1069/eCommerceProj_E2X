package UI;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.ie.InternetExplorerDriver;

public class UI_utils {
    public static WebDriver driver;
    public Properties prop;
    private static long TIMEOUT_S = 40;


    public WebDriver initializeDriver() throws IOException {
        String current_dir = System.getProperty("user.dir");

         prop = new Properties();
        FileInputStream fs = new FileInputStream("./src/test/resources/config/config.properties");
        prop.load(fs);
        String browserName = prop.getProperty("browser");

        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", current_dir + "\\src\\test\\resources\\webDriver\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.firefox.driver", current_dir + "/drivers/geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browserName.equals("IE")) {
            System.setProperty("webdriver.ie.driver", current_dir + "/drivers/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }

        return driver;
    }


    public void ClickElement(WebElement element) {
        //System.out.println("inside js clickelement");
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);

    }

    public void waitTillObjectAppears(WebElement locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_S);
            wait.until(ExpectedConditions.visibilityOf(locator));
        } catch (TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void waitTillObjectClickable(WebElement locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_S);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
