package implementations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import logger.Log;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


import static implementations.AmazonSearchAutomationImplementation.driver;

public class ExcelManipulationImplementation {
    static ArrayList<String> adidasList = new ArrayList<>();
    static ArrayList<String> pumaList = new ArrayList<>();
    static int count = 2;

    /*
     * Storing shoes brand data.
     * @return ArrayList of puma and adidas shoes.
     */
    public static void storingBrandsData() {
//      Storing brand data from amazon.in
        for (int i = 0; i < 60; i++) {
            WebElement brand = driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal']//preceding::div[@class='a-row a-size-base a-color-secondary']//descendant::span)[" + (i + 1) + "]"));
            String brandName = brand.getText();
            WebElement shoeName = driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal'])[" + (i + 1) + "]"));
            String shoeFullName = shoeName.getText();
            if (brandName.equals("Adidas")) {
                adidasList.add(shoeFullName);
            } else if (brandName.equals("Puma")) {
                pumaList.add(shoeFullName);
            }
        }
    }

    /*
     * Writing and saving shoes data to excel.
     * @return excel(.xlsx) file in data folder.
     */
    public static void writeAndSaveDataToExcel() {
        try {
//        Try block to handle exceptions
//          Creating xlsx workbook
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet(" Shoes Brand Data ");

//          Creating a row object
            Row row;
//          Adding shoes data to list
            Map<String, Object[]> shoeData = new LinkedHashMap<>();
            shoeData.put("1", new Object[]{"Adidas", "Puma"});

            for (int i = 0; i < pumaList.size(); i++) {

                shoeData.put("" + count, new Object[]{(adidasList.get(i)), (pumaList.get(i))});
                count++;
            }
            for (int j = count - 2; j < adidasList.size(); j++) {

                shoeData.put("" + count, new Object[]{(adidasList.get(j)), ""});
                count++;
            }

            // Writing in workbook
            int rowId = 0;
            for (Map.Entry<String, Object[]> shoes : shoeData.entrySet()) {

                row = spreadsheet.createRow(rowId++);
                Object[] objectArr = shoeData.get(shoes.getKey());
                int cellId = 0;

                for (Object obj : objectArr) {
                    Cell cell = row.createCell(cellId++);
                    cell.setCellValue(obj.toString());
                }
            }
            // Saving the data in resources data folder
            FileOutputStream out = new FileOutputStream(("src/test/resources/data/shoeBrandData.xlsx"));
            workbook.write(out);
            out.close();
            driver.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Fetching data from shoeBrandData.xlsx file and parsing to json.
     * @return output.json file in data folder.
     */
    public static void fetchDataFromExcelParseToJson() {
//        Try block to handle exceptions
        try {
//          Fetching the shoeBrandData.xlsx file
            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/data/shoeBrandData.xlsx");
            Workbook wb = new XSSFWorkbook(file);
            Sheet sh = wb.getSheet(" Shoes Brand Data ");

            List<Map<String, String>> dataList = new ArrayList<>();

            // The first row contains headers
            Row headerRow = sh.getRow(0);
            int numColumns = headerRow.getLastCellNum();

            for (int i = 1; i <= sh.getLastRowNum(); i++) {
                Row currentRow = sh.getRow(i);
                Map<String, String> dataMap = new HashMap<>();

                for (int j = 0; j < numColumns; j++) {
                    Cell cell = currentRow.getCell(j);
                    String header = headerRow.getCell(j).getStringCellValue();
                    String value = (cell != null) ? cell.getStringCellValue() : "";
                    dataMap.put(header, value);
                }

                dataList.add(dataMap);
            }
            // Convert dataList to JSON using Gson library
            Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
            String jsonData = gson.toJson(dataList);

            // Writing the JSON data to a file
            try (FileOutputStream output = new FileOutputStream("src/test/resources/data/output.json")) {
                output.write(jsonData.getBytes());
            }
            Log.info("JSON data:\n" + jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}