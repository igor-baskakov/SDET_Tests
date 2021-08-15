import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    @FindBy(css = "input[type='email']")
    private WebElement login;

    @FindBy(css = "input[name='password']")
    private WebElement password;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void inputLogin(String userLogin) {
        login.sendKeys(userLogin + Keys.ENTER);
    }

    public void inputPassword(String userPassword) {
        password.sendKeys(userPassword + Keys.ENTER);
    }

}
