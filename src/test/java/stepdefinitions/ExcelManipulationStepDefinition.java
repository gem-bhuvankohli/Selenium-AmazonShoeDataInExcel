package stepdefinitions;

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

    @Then("^parse the excel data to a json file$")
    public static void fetchDataFromExcel() {
        ExcelManipulationImplementation.fetchDataFromExcelParseToJson();
    }
}
