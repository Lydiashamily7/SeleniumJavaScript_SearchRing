package siterraSiteCreation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchRing {

	public static WebDriver driver;
	public static String state = "Washington";
	public static String SearchRingName = null;
	public static String city = "Cleveland";
	public static String ZIPCode = "01010";
	public static String county;

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\sasirekha.govindaraj\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		// Login
		driver.get("https://americantower-uat4.siterra.com/atc");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys("sasirekha.govindaraj@americantower.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// Navigate to Search Ring via top navigation tab
		driver.findElement(By.xpath("//map/area[@title='Search']")).click();
		driver.switchTo().frame("MainFrame");
		Select searchOption = new Select(driver.findElement(By.id("searchClassList")));
		searchOption.selectByVisibleText("Search Ring");
		driver.findElement(By.id("searchWords")).sendKeys(state);
		driver.findElement(By.xpath("//a[@class='Button']")).click();
		driver.findElement(By.xpath("//tr[1]/td[4]/a/g_value")).click();
		Thread.sleep(3000);
		driver.switchTo().window(driver.getWindowHandle());
		if (driver.findElement(By.xpath("//*[@id='NavCrumbs']/a[6]")).isDisplayed()) {
			System.out.println("Element is displayed");
		} else {
			System.out.println("Element is not displayed");
		}
		driver.findElement(By.xpath("//*[@id='NavCrumbs']/a[6]")).click();

		// Create Search Ring
		driver.switchTo().frame("MainFrame");
		Actions action = new Actions(driver);
		Thread.sleep(1000);
		action.moveToElement(driver.findElement(By.xpath("//div/a[3]//span[2]/img"))).build().perform();
		driver.findElement(By.xpath("//*[@id='tblDynamicMenu0']/tbody/tr[2]/td[2]")).click();
		String parentWindow = driver.getWindowHandle();
		driver.findElement(By.xpath("//img[@src='skins/common/images/icons/gif_buttons_solid/add.gif']")).click();
		for (String s : driver.getWindowHandles()) {
			driver.switchTo().window(s);
		}
		Select searchRingType = new Select(driver.findElement(By.id("CMB_TYPE_ID")));
		searchRingType.selectByVisibleText("Site");
		driver.findElement(By.xpath("//span[@id='INSERTED_FRAME']//tr/td[1]/a//img")).click();

		// Fill out the Create Search Ring Form
		SimpleDateFormat date = new SimpleDateFormat("dd-MMM_HH-mm_ss", Locale.US);
		SearchRingName = state + date.format(new Date());
		Thread.sleep(1000);
		driver.manage().window().maximize();
		Thread.sleep(1000);
		driver.findElement(By.id("1190206")).sendKeys(SearchRingName);
		Select SearchRingStatus = new Select(driver.findElement(By.id("1190204")));
		SearchRingStatus.selectByVisibleText("Accepted");
		String LatitudeDegree = "24", LatitudeMinute = "17", LatitudeSecond = "39.1", LongitudeDegree = "73",
				LongitudeMinute = "09", LongitudeSecond = "59.8", radius = "3";
		driver.findElement(By.id("LatDeg1190202")).sendKeys(LatitudeDegree);
		driver.findElement(By.id("LatMin1190202")).sendKeys(LatitudeMinute);
		driver.findElement(By.id("LatSec1190202")).sendKeys(LatitudeSecond);
		driver.findElement(By.id("LongDeg1190205")).sendKeys(LongitudeDegree);
		driver.findElement(By.id("LongMin1190205")).sendKeys(LongitudeMinute);
		driver.findElement(By.id("LongSec1190205")).sendKeys(LongitudeSecond);
		driver.findElement(By.id("1295397")).sendKeys(radius);
		Select SearchRingRadius = new Select(driver.findElement(By.id("1295398")));
		SearchRingRadius.selectByVisibleText("Mile");
		String GeneratedLatitude = driver.findElement(By.xpath("//input[@id='1190202']")).getText();
		System.out.println("GeneratedLatitude: " + GeneratedLatitude);
		String GeneratedLongitude = driver.findElement(By.xpath("//input[@id='1190205']")).getText();
		System.out.println("GeneratedLongitude: " + GeneratedLongitude);
		driver.findElement(By.xpath("//td[1]/a//img[@src[contains(text(),tick.gif)]]")).click();
		Thread.sleep(3000);
		System.out.println("Parent Window: " + parentWindow);
		driver.switchTo().window(parentWindow);
		// driver.switchTo().window(driver.getWindowHandle());
		Thread.sleep(3000);
		// wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("MainFrame")));
		// driver.switchTo().frame("MainFrame");
		// Search icon
		// driver.findElement(By.xpath("//img[@id='IMG_SEARCH_702040100']")).click();

		if (driver
				.findElements(By.xpath(
						"//img[@src='skins/common/images/icons/gif_grey/magnifier_cancel.gif?v=4.2.5-release-293']"))
				.size() != 0) {
			driver.findElement(By
					.xpath("//img[@src='skins/common/images/icons/gif_grey/magnifier_cancel.gif?v=4.2.5-release-293']"))
					.click();
		}
		if (driver.findElements(By.xpath("//table[@class='BackgroundSearch']//input[@id='TXT_SEARCH_FOR_702040100']"))
				.size() != 0) {
			driver.findElement(By.xpath("//div/span/div[2]/table//input[1]")).clear();
		} else {
			driver.findElement(By.xpath("//img[@id='IMG_SEARCH_702040100']")).click();
		}
		driver.findElement(By.xpath("//div/span/div[2]/table//input[1]")).sendKeys(SearchRingName);
		driver.findElement(By.xpath("//table[@class='BackgroundSearch']//a[@class='ShortButton'][1]/img")).click();
		driver.findElement(By.xpath("//table[@id='GRID_DATA_702040100']//tr[@id='GRID_ROW']/td[4]/a/g_value")).click();
		// driver.switchTo().window(parentWindow);

		wait.until(ExpectedConditions
				.visibilityOfElementLocated((By.xpath("//div[@id='DataDivContainerLeftNavSites']/div/span[1]//nobr"))));
		String searchRingParentWindow = driver.getWindowHandle();
		driver.findElement(By.xpath("//div[@id='DataDivContainerLeftNavSites']/div/span[1]//nobr")).click();
		for (String a : driver.getWindowHandles()) {
			driver.switchTo().window(a);
		}
		driver.findElement(By.xpath("//*[@id='INSERTED_FRAME']/form/table/tbody/tr/td/table/tbody/tr/td[1]/a/nobr"))
				.click();
		driver.findElement(By.xpath("//a[@class='parent inherit Button']")).click();
		driver.findElement(By.id("1190164")).clear();
		driver.findElement(By.xpath("//input[@id='1190170']")).sendKeys(state + "1");
		// driver.findElement(By.xpath("//input[@id='1190167']")).sendKeys(GeneratedLatitude);
		// driver.findElement(By.xpath("//input[@id='1190168']")).sendKeys(GeneratedLongitude);
		String Address = "10 Presidential Way";
		driver.findElement(By.xpath("//textarea[@id='1283867']")).sendKeys(Address);
		driver.findElement(By.xpath("//input[@id='1303327']")).sendKeys(state);
		Select SiteState = new Select(driver.findElement(By.id("1285277")));
		SiteState.selectByIndex(2);
		Select SiteCounty = new Select(driver.findElement(By.xpath("//select[@id='1283999']")));
		SiteCounty.selectByIndex(1);
		county = SiteCounty.getFirstSelectedOption().getText();
		Select OperationalArea = new Select(driver.findElement(By.id("1283884")));
		OperationalArea.selectByVisibleText("East");
		driver.findElement(By.xpath("//input[@id='1285276']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@id='1285278']")).sendKeys(ZIPCode);
		driver.findElement(By.xpath("//img[@src='skins/common/images/icons/gif_buttons_solid/tick.gif']")).click();
		Thread.sleep(1000);
		driver.switchTo().window(searchRingParentWindow);
	}
}

// verify if element exists
// verify if it is enabled
