package gsa;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class Atest440 extends BaseClass {

	/*
	 * [CCSMFSSM-440] [FE] Default page - NSN search Landing page with Add option
	 * Given User that has the URL to access the NSN Class/Group/ Routing
	 * maintenance will land on the search Landing Page.
	 */

	// public ArrayList<String> groupElementHeaderArray = new ArrayList<String>();
	// public TreeMap<String, String> groupRoutingDetailMap = new TreeMap<String,
	// String>();
	// public ArrayList<TreeMap<String, String>> groupRoutingDetailArray = new
	// ArrayList<TreeMap<String, String>>();
	// public ArrayList<TreeMap<String, String>> classRoutingDetailArray = new
	// ArrayList<TreeMap<String, String>>();
	// public ArrayList<TreeMap<String, String>> nsnRoutingDetailArray = new
	// ArrayList<TreeMap<String, String>>();

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

			if (pageTitleElemet.getText().contains("Add Record")) {
				BaseClass.test.log(LogStatus.PASS, "Navigated to the specified URL");
				// Add the screenshot to our extent report
				BaseClass.takeScreenshort(driver, BaseClass.screenShotPath + File.separator + "urlpage.png");
				BaseClass.test.log(LogStatus.PASS,
						BaseClass.test.addScreenCapture(BaseClass.screenShotPath + File.separator + "urlpage.png"));
				assertEquals(true, true);
			} else {
				BaseClass.takeScreenshort(driver, BaseClass.screenShotPath + File.separator + "urlpage.png");
				BaseClass.test.log(LogStatus.FAIL, "Test Failed");
				// Add the screenshot to our extent report
				BaseClass.test.log(LogStatus.PASS,
						BaseClass.test.addScreenCapture(BaseClass.screenShotPath + File.separator + "urlpage.png"));
				assertEquals(false, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error while launching url");
			e.printStackTrace();
			assertEquals(false, true);
		}
	}

//Given User places the mouse of the Tool tip icon, then tool tip appears.
	@Test
	@Parameters({ "Tool tip" })
	public void toolTip(String TT) {

	}
}
