import bsh.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestMail {

    private WebDriver driver;
    private LoginPage loginPage;
    private InboxPage inboxPage;
    DesiredCapabilities capabilities;

    Properties properties = new Properties();
    InputStream is = getClass().getResourceAsStream("app.properties");
    {
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    final private String userMail = properties.getProperty("userMail");
    final private String userPassword = properties.getProperty("userPassword");
    final private String recipientOfMail = properties.getProperty("recipientOfMail");
    final private String baseURL = "https://mail.google.com/mail/u/0/#inbox";
    final private String filterOfSearch = "in:inbox subject:Simbirsoft Тестовое задание ";
    final private String stringForSend = "Simbirsoft Тестовое задание. Баскаков";

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
//        capabilities = DesiredCapabilities.chrome();
//        capabilities.setBrowserName("chrome");
//        capabilities.setPlatform(Platform.WIN10);
//        capabilities.setVersion("92.0.4515.131");
//        driver = new RemoteWebDriver(new URL("http://192.168.43.97:4444/wd/hub"), capabilities);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(baseURL);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        inboxPage = new InboxPage(driver);
    }

    @AfterTest
    public void closeResources() {
        driver.quit();
    }

    @Test
    public void mail() {
        loginPage.inputLogin(userMail);
        loginPage.inputPassword(userPassword);
        inboxPage.inputSearchSubj(filterOfSearch);
        inboxPage.sendNewMail(recipientOfMail, stringForSend);
    }
}

