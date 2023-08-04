package implementations;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static implementations.AmazonSearchAutomationImplementation.driver;

public class ExcelManipulationImplementation {
    static ArrayList<String> adidasList = new ArrayList<>();
    static ArrayList<String> pumaList = new ArrayList<>();

    public static void storingBrandsData() {
//      Storing brand data from amazon.in
        for (int i = 0; i < 50; i++) {
            String brandName = driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal']//preceding::div[@class='a-row a-size-base a-color-secondary']//descendant::span)[" + (i + 1) + "]")).getText();
            String shoeFullName = driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal'])[" + (i + 1) + "]")).getText();
            if (brandName.equals("Adidas")) {
                adidasList.add(shoeFullName);
            } else if (brandName.equals("Puma")) {
                pumaList.add(shoeFullName);
            }
        }
    }

    public static void writeAndSaveDataToExcel() {
        try {
//        Try block to handle exceptions
//          Creating xlsx workbook
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet(" Shoes Brand Data ");

//          Creating a row object
            Row row;
//          Adding shoes data to list
            Map<String, Object[]> shoeData = new TreeMap<>();
            shoeData.put("1", new Object[]{"Adidas", "Puma"});
            int count = 2;
            for (int i = 0; i < pumaList.size(); i++) {
                shoeData.put("" + count, new Object[]{(adidasList.get(i)), (pumaList.get(i))});
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
}
