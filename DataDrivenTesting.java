package dataDrivenTesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DataDrivenTesting {

	public static void main(String[] args) {
		WebDriver driver = null;
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		XSSFCell cell = null;

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\sasirekha.govindaraj\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://qcoaainternal.americantower.com/OLA/faces/login.jspx");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		File src = new File("M:\\HexawareQA\\Sasi\\DataDrivenProject\\TestDataFolder\\TestData.xls");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(src);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = workbook.getSheetAt(0);
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {

			cell = sheet.getRow(i).getCell(0);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.id("pt1:it1::content")).clear();
			driver.findElement(By.id("pt1:it1::content")).sendKeys(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.id("pt1:it2::content")).clear();
			driver.findElement(By.id("pt1:it2::content")).sendKeys(cell.getStringCellValue());
			driver.findElement(By.xpath("//input[@type='submit'][@id='u_0_5']")).click();
			driver.findElement(By.xpath("//div[text()='Account Settings']")).click();
			driver.findElement(By.xpath("//text()[.='Log Out']/ancestor::span[1]")).click();
		}
	}
}