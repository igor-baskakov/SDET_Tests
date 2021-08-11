import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestMail {

    private WebDriver driver;
    LoginPage loginPage;
    InboxPage inboxPage;
    int countOfMail;

    final private String userMailProvider = "gmail.com";
    final private String userLogin = "18276354test";
    final private String userPassword = "!!CyjgSDb!#eayx7";
    final private String baseURL = "https://mail.google.com/mail/u/0/#inbox";
    final private String subjOfMail = "Simbirsoft Тестовое задание";
    final private String surname = "Баскаков";


    @BeforeTest
    public void setup() {

        driver = new ChromeDriver();

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
    public void mail() throws InterruptedException {

        loginPage.inputLogin(userLogin);

        loginPage.inputPassword(userPassword);

        inboxPage.inputSearchSubj(subjOfMail);

        countOfMail = inboxPage.countOfMail();

        inboxPage.sendNewMail(countOfMail, subjOfMail, surname, userMailProvider);

    }

}
