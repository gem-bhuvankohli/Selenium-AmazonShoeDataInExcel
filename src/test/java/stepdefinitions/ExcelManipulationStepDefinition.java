package stepdefinitions;

import implementations.AmazonSearchAutomationImplementation;
import implementations.ExcelManipulationImplementation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class ExcelManipulationStepDefinition {
    @Given("^Store brand data$")
    public static void storeData() {
        ExcelManipulationImplementation.storingBrandsData();
    }

    @Then("^write data to excel and save it$")
    public static void writeAndSaveData() {
        ExcelManipulationImplementation.writeAndSaveDataToExcel();
    }
}
