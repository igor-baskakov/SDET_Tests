import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestMail {

    private WebDriver driver;
    private LoginPage loginPage;
    private InboxPage inboxPage;

    final private String userMail = "18276354test@gmail.com";
    final private String recipientOfMail = "18276354test@gmail.com";
    final private String userPassword = "!!CyjgSDb!#eayx7";
    final private String baseURL = "https://mail.google.com/mail/u/0/#inbox";
    final private String filterOfSearch = "in:inbox subject:Simbirsoft Тестовое задание ";
    final private String stringForSend = "Simbirsoft Тестовое задание. Баскаков";

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/chromedriver.exe");
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
