import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class InboxPage {

    WebDriver driver;
    int countOfMail;

    @FindBy (css = "input[placeholder=\"Поиск в почте\"]")
    WebElement SearchStringMail;

    @FindBy (xpath = "/html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div/div[1]/div[2]/div[2]/div[2]/div/span/div[1]/span/span[1]/span[2]")
    WebElement CountOfMail;

    @FindBy (css = "div[jscontroller=\"eIu7Db\"]")
    WebElement NewMailButton;

    @FindBy (css = "textarea[aria-label=\"Кому\"]")
    WebElement RecipientMail;

    @FindBy (css = "input[name=\"subjectbox\"]")
    WebElement SubjOfMail;

    @FindBy (css = "div[aria-label=\"Тело письма\"")
    WebElement TextOfMail;

    public InboxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
    }

    public void inputSearchSubj(String subjOfMail) {
        SearchStringMail.sendKeys("subject:" + subjOfMail + Keys.ENTER);
    }

    public int countOfMail() {
        countOfMail = Integer.parseInt(CountOfMail.getText());
        return (countOfMail);
    }

    public void clickNewMailButton(){
        NewMailButton.click();
    }

    public void sendNewMail(int countOfMail, String subjOfMail, String surname, String userMailProvider) throws InterruptedException {
        clickNewMailButton();
        RecipientMail.sendKeys("18276354test" + "@" + userMailProvider);
        SubjOfMail.sendKeys(subjOfMail + "." + surname);
        TextOfMail.sendKeys(countOfMail + Keys.chord(Keys.CONTROL, Keys.ENTER));
        Thread.sleep(10000);
    }

}





