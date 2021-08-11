import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class LoginPage {

    WebDriver driver;

    @FindBy(css = "input[type=\"email\"]")
    WebElement Login;

    @FindBy(css  = "input[name=\"password\"]")
    WebElement Password;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    public void inputLogin(String userLogin) {
        Login.sendKeys(userLogin + Keys.ENTER);
    }

    public void inputPassword(String userPassword) {
        Password.sendKeys(userPassword + Keys.ENTER);
    }

}
