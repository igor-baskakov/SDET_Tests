import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class BaseClass {
    private WebDriver driver;
    private WebElement webElement;
    private Robot robot;

    final private String baseURL = "https://www.google.com/";
    final private By searchStringLocator = By.cssSelector("input[title=\"Поиск\"]");
    final private By inputCalculatorLocator = By.id("cwos");
    final private By mapStringLocator = By.cssSelector("span[jsname=\"ubtiRe\"]");
    final private String searchCalc = "калькулятор\n";

    @BeforeMethod
    private void setup() throws AWTException {
        System.setProperty("webdriver.chrome.driver", "src/test/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(baseURL);
        driver.manage().window().maximize();
        WebElement webElement = driver.findElement(searchStringLocator);
        webElement.sendKeys(searchCalc);
        webElement = driver.findElement(inputCalculatorLocator);
        webElement.click();
        robot = new Robot();
    }

    @AfterMethod
    private void closeResources(){
        driver.quit();
    }

    @Test
    private void test1(){
        input("(1 + 2) × 3 - 40 ÷ 5 =");
        webElement = driver.findElement(inputCalculatorLocator);
        Assert.assertEquals(webElement.getText(), "1");

        webElement = driver.findElement(mapStringLocator);
        Assert.assertEquals(webElement.getText(), "(1 + 2) × 3 - 40 ÷ 5 =");
    }

    @Test
    private void test2(){
        input("6 ÷ 0 =");
        webElement = driver.findElement(inputCalculatorLocator);
        Assert.assertEquals(webElement.getText(), "Infinity");

        webElement = driver.findElement(mapStringLocator);
        Assert.assertEquals(webElement.getText(), "6 ÷ 0 =");
    }

    @Test
    private void test3(){
        input("s =");
        webElement = driver.findElement(inputCalculatorLocator);
        Assert.assertEquals(webElement.getText(), "Error");

        webElement = driver.findElement(mapStringLocator);
        Assert.assertEquals(webElement.getText(), "sin() =");
    }

    private void input(String expression) {
        for(int i = 0; i < expression.length(); i++){
            switch (expression.charAt(i)){
                case '0': robot.keyPress(KeyEvent.VK_0);
                break;
                case '1': robot.keyPress(KeyEvent.VK_1);
                break;
                case '2': robot.keyPress(KeyEvent.VK_2);
                break;
                case '3': robot.keyPress(KeyEvent.VK_3);
                break;
                case '4': robot.keyPress(KeyEvent.VK_4);
                break;
                case '5': robot.keyPress(KeyEvent.VK_5);
                break;
                case '6': robot.keyPress(KeyEvent.VK_6);
                break;
                case '7': robot.keyPress(KeyEvent.VK_7);
                break;
                case '8': robot.keyPress(KeyEvent.VK_8);
                break;
                case '9': robot.keyPress(KeyEvent.VK_9);
                break;
                case 's': robot.keyPress(KeyEvent.VK_S);
                break;
                case '-': robot.keyPress(KeyEvent.VK_MINUS);
                break;
                case '+': robot.keyPress(KeyEvent.VK_ADD);
                break;
                case '*':
                case '×': robot.keyPress(KeyEvent.VK_MULTIPLY);
                break;
                case '/':
                case '÷': robot.keyPress(KeyEvent.VK_DIVIDE);
                break;
                case '=': robot.keyPress(KeyEvent.VK_EQUALS);
                break;
                case '(': {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_9);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                }
                case ')': {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_0);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                }
            }
        }
    }
}
