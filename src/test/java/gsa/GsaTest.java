package gsa;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;


public class GsaTest extends BaseClass {


	public ArrayList<String> groupElementHeaderArray = new ArrayList<String>();
	//public TreeMap<String, String> groupRoutingDetailMap = new TreeMap<String, String>();
	public ArrayList<TreeMap<String, String>> groupRoutingDetailArray = new ArrayList<TreeMap<String, String>>();
	public ArrayList<TreeMap<String, String>> classRoutingDetailArray = new ArrayList<TreeMap<String, String>>();
	public ArrayList<TreeMap<String, String>> nsnRoutingDetailArray = new ArrayList<TreeMap<String, String>>();


	@Parameters({ "url", "browser" })
	@Test
	public void launchURl(String url, String browser) {
		try {
			System.out.println("Launching browser : " + browser);
			BaseClass.setupBrowser(browser, url);
			Thread.sleep(5000);
			System.out.println("Web Title : " + driver.getTitle());

			WebElement pageTitleElemet = driver.findElement(By.xpath("//*[@id=\"basic-logo\"]/em/a"));
			System.out.println("Page Title : " + pageTitleElemet.getText());


			if(pageTitleElemet.getText().contains("FSS-19")) {
				BaseClass.test.log(LogStatus.PASS, "Navigated to the specified URL");
				//Add the screenshot to our extent report
				BaseClass.takeScreenshort(driver, BaseClass.screenShotPath + File.separator + "urlpage.png");
				BaseClass.test.log(LogStatus.PASS, BaseClass.test.addScreenCapture(BaseClass.screenShotPath + File.separator + "urlpage.png") );
				assertEquals(true, true);
			}
			else {
				BaseClass.takeScreenshort(driver, BaseClass.screenShotPath + File.separator + "urlpage.png");
				BaseClass.test.log(LogStatus.FAIL, "Test Failed");
				//Add the screenshot to our extent report
				BaseClass.test.log(LogStatus.PASS, BaseClass.test.addScreenCapture(BaseClass.screenShotPath + File.separator + "urlpage.png") );
				assertEquals(false, true);
			}


		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error while launching url");
			e.printStackTrace();
			assertEquals(false, true);
		}
	}


	@Test
	@Parameters({ "nsnGroupID" })
	public void searchNSNCLass(String nsnGroupID) {
		try {
			System.out.println();
			System.out.println("-----searchNSNCLass ---" );
			
			/*Actions actions = new Actions(driver);
			WebElement toolTipElement = driver.findElement(By.xpath("//*[@id=\"main-content\"]/div/div[1]/span/span[1]/sup"));
			actions.moveToElement(toolTipElement).perform();
			//actions.click();*/
			
			System.out.println("Waiting for inputbox");
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement inputElement = wait.until( ExpectedConditions.visibilityOfElementLocated(By.id("nsn-search-field")));

			System.out.println("Clearing the searchbox");
			inputElement.clear();
			inputElement.sendKeys(nsnGroupID);


			System.out.println("Clicking on search");
			WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[1]/div/form/button"));
			searchButton.click();								

			System.out.println("Validating warning message after click on search button");
			WebElement warningMessage = driver.findElement(By.xpath("//*[@id=\"main-content\"]/div/div[1]/div/p"));
			String warning = warningMessage.getText();
			System.out.println("warning: " + warning);

			if(warning.contains("NSN, Class, or Group record ["+ nsnGroupID +"] not found")) {
				System.out.println("Warning message is displayed as per the requirements");
			}else {
				System.out.println("Warning message is not matching with requirements");
			}
			inputElement.clear();
			Thread.sleep(3000);

			//Verify "Add record" text displayed on the landing page 
			
			WebElement landingPage = driver.findElement(By.cssSelector("#main-content > div > div.margin-top-205 > h3"));		 											
			
			//WebElement landingPage = driver.findElement(By.xpath("//*[@id=\"main-content\"]/div/div[3]/h3"));		 											
			System.out.println("Add Record Text from Landing page: " + landingPage.getText());

			// Verifying Add Record text with Assertion

			if(landingPage.isDisplayed()){
				assertEquals(true, true);
				System.out.println("Add Text is displayed: " + landingPage.getText() );
			}else {
				assertEquals(false, false);
				System.out.println("Add Text is  NOT displayed: " + landingPage.getText());

			}
			/*
			  System.out.println("Waiting for group caption");
			 WebElement groupCaptionElement = wait.until(						
				         ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main-content\"]/div[2]/table/caption")));


			 System.out.println("Validating the group caption");
			 if(groupCaptionElement.isDisplayed() && groupCaptionElement.getText().contains("Group Routing Details")) {
				 BaseClass.test.log(LogStatus.PASS, "Search for NSN Group ID : " + nsnGroupID + " is successful");
				 BaseClass.takeScreenshort(driver, BaseClass.screenShotPath + File.separator + "dataentry.png");

				 BaseClass.test.log(LogStatus.PASS, BaseClass.test.addScreenCapture(BaseClass.screenShotPath + File.separator + "dataentry.png") );
				 assertEquals(true, true);
			 }
			 else {
				 BaseClass.test.log(LogStatus.FAIL, "Test Failed");
				 assertEquals(false, true);
			 }
			 */ 

			System.out.println("Clicking on route");
			// click on Add a Routing Record						
			// WebElement addRouting = driver.findElement(By.xpath("//*[@id=\"main-content\"]/div/div[3]/button"));
			WebElement addRouting = driver.findElement(By.cssSelector("#main-content > div > div.margin-top-205 > button"));		  
			addRouting.click();								 
			System.out.println("Clicked on Add a Routing Record" );
			Thread.sleep(3000);

		} catch (Exception e) {
			// TODO: handle exception
			assertEquals(false, true);
		}
	}

	@Test
	@Parameters({ "singleNsnGroupID" })
	public void singleSearchNSNCLass(String singleNsnGroupID) {
		try {
			System.out.println();
			System.out.println("-----singleSearchNSNCLass ---" );
			System.out.println("Waiting for inputbox");
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement inputElement = wait.until( ExpectedConditions.visibilityOfElementLocated(By.id("nsn-search-field")));

			System.out.println("Clearing the searchbox");
			inputElement.clear();
			inputElement.sendKeys(singleNsnGroupID);


			System.out.println("Clicking on search");
			WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[1]/div/form/button"));
			searchButton.click();								

			System.out.println("Validating Error message after click on search button");
			WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"main-content\"]/div/div[1]/div/p"));
			String error = errorMessage.getText();
			System.out.println("Error message on NSN Landing Page: " + error);

			if(error.contains("NSN, Class, or Group field must have a minimum of 2 digits")) {
				System.out.println("Error message is displayed as per the requirements:  " + error );
			}else {
				System.out.println("Error message is NOT matching with requirements");
			}

			inputElement.clear();
		} catch (Exception e) {
			// TODO: handle exception
			assertEquals(false, true);
		}
	}



	@Test
	public void validateGroupRouting() {
		try {
			System.out.println("\nInside validateGroupRouting method");
			WebDriverWait wait = new WebDriverWait(driver, 10);

			System.out.println("Waiting for group caption");
			WebElement groupCaptionElement = wait.until( ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main-content\"]/div[2]/table/caption")));


			//WebElement groupCaptionTable = driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[2]/table"));
			System.out.println("\n\nReading Group Routing Details Table\n");


			groupRoutingDetailArray = iterateTable("//*[@id=\"main-content\"]/div[2]/table");
			System.out.println("\nDisplaying groupRoutingDetailArray map items");
			System.out.println(groupRoutingDetailArray);
			BaseClass.moveToElement(driver, "//*[@id=\"main-content\"]/div[2]/table/caption");  //*[@id="main-content"]/div[2]/table/caption
			BaseClass.test.log(LogStatus.INFO, "Reading Group Routing Table");
			BaseClass.takeScreenshort(driver, BaseClass.screenShotPath + File.separator + "groupRoutingTable.png");

			BaseClass.test.log(LogStatus.PASS, BaseClass.test.addScreenCapture(BaseClass.screenShotPath + File.separator + "groupRoutingTable.png") );





			System.out.println("\n\nReading Class Routing Details Table\n");
			classRoutingDetailArray = iterateTable("//*[@id=\"main-content\"]/div[3]/table");
			System.out.println("\nDisplaying classRoutingDetailArray map items");
			System.out.println(classRoutingDetailArray);
			BaseClass.test.log(LogStatus.INFO, "Class Group Routing Table");
			BaseClass.takeScreenshort(driver, BaseClass.screenShotPath + File.separator + "classRoutingTable.png");
			BaseClass.test.log(LogStatus.PASS, BaseClass.test.addScreenCapture(BaseClass.screenShotPath + File.separator + "classRoutingTable.png") );




			System.out.println("\n\nReading NSN Routing Details Table\n");
			nsnRoutingDetailArray = iterateTable("//*[@id=\"main-content\"]/div[4]/table");
			System.out.println("\nDisplaying nsnRoutingDetailArray map items");
			System.out.println(nsnRoutingDetailArray);
			BaseClass.test.log(LogStatus.INFO, "NSN Group Routing Table");
			BaseClass.takeScreenshort(driver, BaseClass.screenShotPath + File.separator + "nsnRoutingTable.png");
			BaseClass.test.log(LogStatus.PASS, BaseClass.test.addScreenCapture(BaseClass.screenShotPath + File.separator + "nsnRoutingTable.png") );





			System.out.println("Validating the group caption");
			if(groupCaptionElement.isDisplayed() && groupCaptionElement.getText().contains("Group Routing Details")) {
				BaseClass.test.log(LogStatus.PASS, "Reading all tables are successful");
				assertEquals(true, true);
			}
			else {
				BaseClass.test.log(LogStatus.FAIL, "Test Failed");
				assertEquals(false, true);
			}


		} catch (Exception e) {
			// TODO: handle exception
			assertEquals(false, true);
		}
	}



	public ArrayList<TreeMap<String, String>> iterateTable(String xpath) {
		ArrayList<String> tableHeaderArray = new ArrayList<String>();
		ArrayList<TreeMap<String, String>> tableDetailArray = new ArrayList<TreeMap<String, String>>();

		try {
			int rowCount = 1;


			//Finding the table element";
			WebElement groupCaptionTable = driver.findElement(By.xpath(xpath));
			WebElement groupCaptionTableHead = groupCaptionTable.findElement(By.tagName("thead"));

			List<WebElement> groupCaptionHeaderList = groupCaptionTableHead.findElements(By.tagName("th"));

			//Iterating the table element;
			//System.out.println("No of elements in the table : " + groupCaptionHeaderList.size());

			Iterator<WebElement> groupCaptionHeaderListIter = groupCaptionHeaderList.iterator();
			while(groupCaptionHeaderListIter.hasNext()) {
				WebElement thElem = groupCaptionHeaderListIter.next();
				System.out.print("\t" + thElem.getText());
				tableHeaderArray.add(thElem.getText());
			}


			//Finding the table body element
			WebElement groupCaptionTableBody = groupCaptionTable.findElement(By.tagName("tbody"));

			//Finding all the table row inside the table body
			List<WebElement> groupCaptionTableRowList = groupCaptionTableBody.findElements(By.tagName("tr"));
			//System.out.println("No of Rows : " + groupCaptionTableRowList.size());

			//Iterating the table row element
			Iterator<WebElement> groupCaptionTableRowListIter = groupCaptionTableRowList.iterator();
			while(groupCaptionTableRowListIter.hasNext()) {

				TreeMap<String, String> rowDataMap = new TreeMap<String, String>();
				WebElement trElem = groupCaptionTableRowListIter.next();

				List<WebElement> tdElem = trElem.findElements(By.xpath(".//*"));

				//Iterating the table row element
				Iterator<WebElement> tdElemIter = tdElem.iterator();
				System.out.print("\nRow " + rowCount++);
				int colCount = 0;

				for(WebElement e : tdElem) {
					if(e.getTagName().contains("td") || e.getTagName().contains("th")) {
						System.out.print("\t" + e.getText());
						rowDataMap.put(tableHeaderArray.get(colCount++), e.getText());
					}
				}

				tableDetailArray.add(rowDataMap);
				System.out.println();


			}

			return tableDetailArray;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return tableDetailArray;

	}


	@Parameters({ "ticker" })
	@Test
	public void searchForTicker(String ticker) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement inputElement = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.id("yfin-usr-qry")));

			inputElement.sendKeys(ticker);

			WebElement searchButton = driver.findElement(By.id("header-desktop-search-button"));
			searchButton.click();


			WebElement ticketElement = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"quote-header-info\"]/div[3]/div[1]/div/span[1]")));


			if(ticketElement.isDisplayed() && driver.getTitle().contains(ticker)) {
				BaseClass.test.log(LogStatus.PASS, "Navigated to the specified URL");
				assertEquals(true, true);
			}
			else {
				BaseClass.test.log(LogStatus.FAIL, "Test Failed");
				assertEquals(false, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			assertEquals(false, true);
		}
	}

	//*[@id="quote-header-info"]/div[3]/div[1]/div/span[1]

	@Parameters({ "expectedTickerPrice" })
	@Test
	public void validateTickerPrice(String expectedTickerPrice) {
		try {

			WebElement tickerPrice = driver.findElement(By.xpath("//*[@id=\"quote-header-info\"]/div[3]/div[1]/div/span[1]"));
			System.out.println("Ticket Price : " + tickerPrice.getText());
			double dblTickerPrice = Double.parseDouble(tickerPrice.getText());
			double expTickerPrice = Double.parseDouble(expectedTickerPrice);

			BaseClass.test.log(LogStatus.INFO, "Expected Ticker Price : " + expectedTickerPrice);
			BaseClass.test.log(LogStatus.INFO, "Current  Ticker Price : " + dblTickerPrice);


			if(dblTickerPrice >= expTickerPrice ) {
				BaseClass.test.log(LogStatus.PASS, "Navigated to the specified URL");
				assertEquals(true, true);
			} else {
				BaseClass.test.log(LogStatus.FAIL, "Ticker Price not matching with expected Price");
				assertEquals(false, true);
			}

		} catch(NumberFormatException e) {
			System.err.println("\n\nNot able to convert to number.");
			e.printStackTrace();
			assertEquals(false, true);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("\n\nNot able to get ticker price");
			e.printStackTrace();
			assertEquals(false, true);
		}
	}

	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
