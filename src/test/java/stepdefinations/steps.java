package stepdefinations;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;

//import io.cucumber.java.PendingException;
@RunWith(Cucumber.class)
public class steps {
	 WebDriver dr;
	 JavascriptExecutor executor; 
	// System.setProperty("webdriver.chrome.driver", "C:\\Users\\chromedriver.exe");
	// WebDriver dr = new ChromeDriver();
	@Given("user is on homepage")
	public void user_is_on_homepage() throws Throwable {
	 System.out.println("bbb");
	 System.setProperty("webdriver.chrome.driver", "C:\\Users\\chromedriver.exe");
	 dr = new ChromeDriver();
	 dr.get("https://coinmarketcap.com/");
	 dr.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
	 dr.findElement(By.xpath("//div[@class='cmc-cookie-policy-banner__close']")).click();
	 
	 System.out.println("test11");
	 
	}

	@When("user click on show record dropdown")
	public void user_click_on_show_record_dropdown() {
		dr.findElement(By.xpath("//div[@class='sc-16r8icm-0 tu1guj-0 cSTqvK']")).click();
		//System.out.println("test12");
	}

	@When("select show100 records")
	public void select_show100_records() {
		 dr.findElement(By.xpath("//button[contains (text(),'100')]")).click();
		// System.out.println("test13");
	}
	@Then("selected records should visible")
	public void selected_records_should_visible() {
		WebElement records= dr.findElement(By.xpath("//p[contains (text(),'Showing 1 - 100 ')]"));
		if(records.isDisplayed())
			System.out.println("passed");
		dr.close();
	}
	
	@When("user click on filter button")
	public void user_click_on_filter_button() throws Throwable {
		dr.manage().window().maximize();
		//dr.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
		dr.findElement(By.xpath("//div[@class='sc-16r8icm-0 tu1guj-0 cSTqvK']")).click();
		dr.findElement(By.xpath("//button[contains (text(),'20')]")).click();
		dr.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);

		//JavascriptExecutor js = (JavascriptExecutor) dr;
		//executor.executeScript("window.scrollBy(0,250)", "");
		WebElement element = dr
				.findElement(By.xpath("/html/body/div/div/div[1]/div[2]/div/div[1]/div[4]/div[1]/span[2]/button[1]"));

				executor = (JavascriptExecutor)dr;
				executor.executeScript("arguments[0].click();", element);
			
				
				dr.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);

				Thread.sleep(3000);

	}

	@When("Filter records by {string} and {string}")
	public void filter_records_by_and(String string, String string2) throws Throwable {
	   
		WebElement element2 = dr.findElement(By.xpath("//html/body/div/div/div[1]/div[2]/div/div[1]/ul/li[5]/button"));

		executor.executeScript("arguments[0].click();", element2);

		dr.findElement(By.xpath("//button[contains (text(),'Market Cap')]")).click();
		Thread.sleep(2000);
		dr.findElement(By.xpath("//button[contains (text(),'$1B - $10B')]")).click();
		dr.findElement(By.xpath("//button[contains (text(),'Apply Filter')]")).click();
		dr.findElement(By.xpath("//button[contains (text(),'Price')]")).click();
		dr.findElement(By.xpath("//button[contains (text(),'$101 - $1,000')]")).click();
		dr.findElement(By.xpath("//button[contains (text(),'Apply Filter')]")).click();
		dr.findElement(By.xpath("//button[contains (text(),'Show results')]")).click();
		

	}

	@Then("records displayed on page are correct as per the filter applied")
	public void records_displayed_on_page_are_correct_as_per_the_filter_applied() throws Throwable {
		List<WebElement> rows = dr.findElements(By.xpath("//table[@class='h7vnx2-2 czTsgW cmc-table  ']//tr"));

		System.out.println(rows.size());
		for (int i = 1; i < 10; i++) {
			Thread.sleep(2000);
			String strprice = dr
					.findElement(By.xpath("//table[@class='h7vnx2-2 czTsgW cmc-table  ']//tr["+i+"]//td[4]"))
					.getText();
			System.out.println(strprice);
			String value = strprice.substring(1).replace("," ,"");;

			// System.out.println(value.length);
			System.out.println(value);
			float floatprice = Float.parseFloat(value);

			if (floatprice <= 1000.00 || floatprice >= 101.00) {
				System.out.println("float price is in the filtered range -true");
			}
			Thread.sleep(2000);
			String strmarketcap = dr
					.findElement(By.xpath("//table[@class='h7vnx2-2 czTsgW cmc-table  ']//tr["+i+"]//td[7]"))
					.getText();
			System.out.println(strmarketcap);
			Thread.sleep(2000);
			String valuemarket = strmarketcap.substring(1).replace(",", "");

			System.out.println(valuemarket);
			long longmarket = Long.parseLong(valuemarket);
			// Long floatmarket=(long) Integer.parseInt(valuemarket);

			if (longmarket >= 1000000000 ) {
				System.out.println("long market cap is in the filtered range -true");
			}

		}
	}

}
