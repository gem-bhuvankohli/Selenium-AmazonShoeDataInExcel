package implementations;

import locators.Locators;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class AmazonSearchAutomationImplementation {
    static WebDriver driver = new ChromeDriver();
    static JavascriptExecutor executor = (JavascriptExecutor) driver;

    public static void openAmazon() {
//      Opening amazon.in url
        try {
            driver.manage().window().maximize();
            driver.get(Locators.baseUri);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void searchForShoes() {
//      Searching for shoes in amazon search
        try {
            driver.findElement(Locators.searchBox)
                    .sendKeys("Shoes" + Keys.ENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void filteringBrands() {
//      Filtering shoes by selecting adidas and puma shoes
        try {
            WebElement brandsFilterBox = driver.findElement(Locators.brandsFilterBox);
            executor.executeScript("arguments[0].scrollIntoView();", brandsFilterBox);
            WebElement adidasFilterCheckBox = driver.findElement(Locators.adidasFilterCheckBox);
            adidasFilterCheckBox.click();
            WebElement pumaFilterCheckBox = driver.findElement(Locators.pumaFilterCheckBox);
            pumaFilterCheckBox.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
 