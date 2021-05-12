package gsa;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;

public class BaseClass {

	public static WebDriver driver;
	public String url;
	public static ExtentReports reports;
	public static ExtentTest test;
	public static String screenShotPath;

	@BeforeSuite
	public void beforeSuite() {

		String currentPath = System.getProperty("user.dir");
		System.out.println(currentPath);
		reports = new ExtentReports(System.getProperty("user.dir") + File.separator + "target" + File.separator
				+ "HTMLReports" + File.separator + "ExtentReport.html");
		test = reports.startTest("GSA Test");
		
		screenShotPath = System.getProperty("user.dir") + File.separator + "target" + File.separator
				+ "HTMLReports" + File.separator + "screenshots";

	}

	public static void setupBrowser(String browserName, String url) {

		if (browserName.equalsIgnoreCase("chrome")) {
			chromeSetup();
			driver.get(url);
		}
	}

	public static void chromeSetup() {
		try {

			if (System.getProperty("os.name").toLowerCase().contains("mac")) {
				System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver");
				driver = new ChromeDriver();
			} else if (System.getProperty("os.name").toLowerCase().contains("win")) {
				System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
				driver = new ChromeDriver();
			} else if (System.getProperty("os.name").toLowerCase().contains("nux")) {
				System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriverlinux");
				driver = new ChromeDriver();
			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("\nError while setting up chrome browser");
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void afterSuite() {
		try {
			Thread.sleep(2000);
			driver.quit();
			reports.endTest(test);
			reports.flush();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error while closing browser");
			e.printStackTrace();

		}
	}

	
	public static void takeScreenshort (WebDriver driver, String fileName) throws IOException {
		TakesScreenshot screenShot = (TakesScreenshot) driver;
		File file = screenShot.getScreenshotAs(OutputType.FILE);
		File storeFile = new File(fileName);
		FileUtils.copyFile(file, storeFile);
		
	}
	
	
	public static void moveToElement(WebDriver driver, String xpath) {
		
		try {
			Actions actions = new Actions(driver);
			WebElement mainMenu = driver.findElement(By.xpath(xpath));
			actions.moveToElement(mainMenu).perform();
			actions.click();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", mainMenu);

			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
