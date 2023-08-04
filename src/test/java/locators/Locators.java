package locators;

import org.openqa.selenium.By;

public class Locators {
    public static final String baseUri = "https://www.amazon.in/";
    public static final By searchBox = By.id("twotabsearchtextbox");
    public static final By brandsFilterBox = By.id("brandsRefinements");

    public static final By adidasFilterCheckBox = By.xpath("//li[@id='p_89/Adidas']//child::i[@class='a-icon a-icon-checkbox']");

    public static final By pumaFilterCheckBox = By.xpath("//li[@id='p_89/Puma']//child::i[@class='a-icon a-icon-checkbox']");
}

