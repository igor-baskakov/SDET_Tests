import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InboxPage {

    public WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "input[placeholder='Поиск в почте']")
    private WebElement searchStringMail;

    @FindBy(css = "div[gh='tm'] span[class='ts']:last-child")
    private WebElement countOfSearchMail;

    @FindBy(css = "div[gh='cm']")
    private WebElement newMailButton;

    @FindBy(css = "textarea[aria-label='Кому']")
    private WebElement recipientMail;

    @FindBy(css = "input[name='subjectbox']")
    private WebElement subjOfSearchMail;

    @FindBy(css = "div[aria-label='Тело письма']")
    private WebElement textOfMail;

    @FindBy(css = "span[class='bAq']")
    private WebElement mailStatus;

    public InboxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step
    public void inputSearchSubj(String subjOfMail) {
        searchStringMail.sendKeys("subject:" + subjOfMail + Keys.ENTER);
    }

    @Step
    public void clickNewMailButton() {
        newMailButton.click();
    }

    @Step
    public void sendNewMail(String recipientOfMail, String stringForSend) {
        clickNewMailButton();
        recipientMail.sendKeys(recipientOfMail);
        subjOfSearchMail.sendKeys(stringForSend);
        textOfMail.sendKeys(Integer.parseInt(countOfSearchMail.getText()) + Keys.chord(Keys.CONTROL, Keys.ENTER));
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.textToBePresentInElement(mailStatus, "Письмо отправлено."));
    }
}





