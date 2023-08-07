package stepdefinitions;

import implementations.AmazonSearchAutomationImplementation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class AmazonSearchAutomationStepDefinition {
    @Given("^Open amazon.in$")
    public static void openWebsite() {
        AmazonSearchAutomationImplementation.openAmazon();
    }

    @And("^Search for shoes$")
    public static void searchOnWebsite() {
        AmazonSearchAutomationImplementation.searchForShoes();
    }

    @Given("^Filter brand shoes$")
    public static void filteringProducts() {
        AmazonSearchAutomationImplementation.filteringBrands();
    }
}
