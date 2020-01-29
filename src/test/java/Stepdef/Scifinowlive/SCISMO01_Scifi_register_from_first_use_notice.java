package Stepdef.Scifinowlive;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Elements.Finish_Notice_elements;
import Elements.Cricketer_first_use_notice_Elements;
import Elements.Wallet_Elements;
import Elements.Register_Page_Elements1;
import Elements.Scifi_First_Use_Notice;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SCISMO01_Scifi_register_from_first_use_notice {
	//Initiate driver
	WebDriver driver;	
	
	//--------------------POPSMO01-----------------------
	//Given I am a user of Axate and I am on the registration page through Cricketer FUN 
	@Given("^I am a user of Axate and I am on the registration page through Cricketer FUN \"([^\"]*)\"$")
	@Test(priority=1)
	@Parameters("browser")
	public void i_am_a_user_of_Axate_and_I_am_on_the_registration_page_through_Cricketer_FUN(String browser) throws Throwable {
		System.out.println("Executing CRISMO01_Cricketer_register_from_first_use_notice");
		
				//chrome
		if (browser.equalsIgnoreCase("chrome")) { 
			System.setProperty("webdriver.chrome.driver","C:/Users/Administrator/Desktop/chromedriver.exe");				
			driver= new ChromeDriver();				
			driver.get("https://www.scifinow.co.uk/interviews/scary-stories-to-tell-in-the-dark-director-andre-ovredal-on-horror-anticipation-and-smuggling-illegal-vhs-tapes/");
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,1200)");
			Scifi_First_Use_Notice popbitch_first_use_elements= new Scifi_First_Use_Notice(driver);
			popbitch_first_use_elements.Click_On_scifi_First_Use_CreateWallet();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));	
			String reg_Page_url= driver.getCurrentUrl();
			if(reg_Page_url.contains("https://account.agate.io/my-agate/sign-up?"))
			{
				System.out.println("Clicking on create wallet opened registration page");
			}
	} 
	
				
				
	}

	
	
	@When("^I enter all the required details on step one and click on continue$")
	@Test(priority=2)
	public void i_enter_all_the_required_details_on_step_one_and_click_on_continue() throws Throwable {
	
		Register_Page_Elements1 Reg_page_elements = new Register_Page_Elements1(driver);
		Reg_page_elements.Registration_Step1();
		System.out.println("2 of 6");
		
	}

	
	@When("^top up with a valid card in steptwo with one pound and click on continue$")
	@Test(priority=3)
	public void top_up_with_a_valid_card_in_steptwo_with_one_pound_and_click_on_continue() throws Throwable {
		Register_Page_Elements1 Reg_page_elements = new Register_Page_Elements1(driver);
		Thread.sleep(5000);
		Reg_page_elements.voucher_process();
		Thread.sleep(5000);
		Reg_page_elements.click_continue_on_reg_page2();
		System.out.println("3 of 6");
	}
	
	
	@Then("^I get a funded axate wallet with one pound in it$")
	@Test(priority=4)
	public void i_get_a_funded_axate_wallet_with_one_pound_in_it() throws Throwable {
		Wallet_Elements w1 = new Wallet_Elements(driver);
		w1.Click_On_popbitch_staging_agate_poster();
		String actual_current_balance=w1.current_balance();
		String expected_current_balance="10.00";
		Assert.assertEquals(actual_current_balance, expected_current_balance);
		System.out.println("4 of 6");
	}

	
	@Then("^navigated to the same article$")
	@Test(priority=5)
	public void navigated_to_the_same_article() throws Throwable {
	    String actual_url= driver.getCurrentUrl();
	    String expected_url = "https://www.thecricketer.com/Topics/premimum_features/englishman_in_antigua_johnny_grave_is_in_charge_of_west_indies_cricket_trying_to_lead_a_renaissance_and_beating_joe_roots_men_did_no_harm.html";
	    Assert.assertEquals(actual_url, expected_url);
	    System.out.println("5 of 6");
	}
	
	
	@Then("^finish notice appears$")
	@Test(priority=6)
	public void finish_notice_appears() throws Throwable {
		Finish_Notice_elements finish_notice = new Finish_Notice_elements(driver);
		Boolean Actual_result = finish_notice.Verify_finish_notice_appears();
		Boolean Expected_result= true;
		Assert.assertEquals(Actual_result, Expected_result);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		System.out.println("6 of 6");
		driver.quit();
	}
	

}