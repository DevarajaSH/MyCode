package StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import factory.BrowserFactory;
import factory.DataProviderFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.OrbitzMainPage;

public class LoginSteps 
{
	public WebDriver driver;
	public WebDriverWait dwait;
	public OrbitzMainPage objOrbitzPage;
	
	@Given("Open Chrome Browser")
	public void open_chrome_browser() {
		try
		{
			driver = BrowserFactory.getBrowser(DataProviderFactory.getProperty().getBrowser());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@When("Naviagate to given URL")
	public void naviagate_to_given_url() 
	{
		try
		{
			String strURl = "https://www.orbitz.com/";
			driver.get(strURl);
			objOrbitzPage = PageFactory.initElements(driver, OrbitzMainPage.class);
			objOrbitzPage.loginApplication();
		}
		catch(Exception E)
		{
			E.printStackTrace();
		}
	}

	@Then("Verify Orbitz Home Page displayed")
	public void verify_orbitz_home_page_displayed() 
	{
		try
		{		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
