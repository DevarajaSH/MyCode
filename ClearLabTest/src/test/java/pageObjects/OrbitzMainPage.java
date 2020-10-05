package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OrbitzMainPage 
{
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(css = "#tab-flight-tab-hp")
	WebElement FlightTab;
	
	@FindBy(css = "#flight-origin-hp-flight")
	WebElement FlightFlyingFrom;
	
	@FindBy(css = "#flight-destination-hp-flight")
	WebElement FlightFlyingTo;
	
	@FindBy(css = "#flight-departing-hp-flight")
	WebElement FlightDepartingDate;

	@FindBy(css = "#flight-returning-hp-flight")
	WebElement FlightReturningDate;
	
	@FindBy(xpath = "//button[@class='datepicker-close-btn close btn-text']")
	WebElement CloseButtonDateSearch;
	
	@FindBy(css="#flight-advanced-options-hp-flight")
	WebElement AdvancedOptionsHyperlink;
	
	@FindBy(css = "#flight-adults-hp-flight")
	WebElement AdultsDropDown;
	
	@FindBy(css = "button.btn-primary btn-action gcw-submit ")
	WebElement SearchButton;
	
	@FindBy(css = "#wizardSearch")
	WebElement WizardSearch;

	public OrbitzMainPage(WebDriver driver)
	{
		this.driver = driver;
		wait = new WebDriverWait(driver,100);
	}
	
	public void loginApplication() 
	{			
		try
		{
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			wait.until(ExpectedConditions.visibilityOf(FlightTab));
			FlightTab.click();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			wait.until(ExpectedConditions.visibilityOf(FlightFlyingFrom));
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			FlightFlyingFrom.click();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			wait.until(ExpectedConditions.visibilityOf(FlightFlyingFrom));
			FlightFlyingFrom.sendKeys("San Francisco");
			FlightFlyingFrom.submit();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			wait.until(ExpectedConditions.visibilityOf(FlightFlyingTo));
			FlightFlyingTo.sendKeys("San Diego");
			FlightFlyingTo.submit();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			FlightDepartingDate.clear();
			wait.until(ExpectedConditions.visibilityOf(FlightDepartingDate));
			FlightDepartingDate.sendKeys("10/10/2020");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(FlightReturningDate));
			Thread.sleep(1000);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("flight-returning-hp-flight")));
			FlightReturningDate.clear();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		    FlightReturningDate.sendKeys("10/30/2020");
			wait.until(ExpectedConditions.visibilityOf(CloseButtonDateSearch));
			CloseButtonDateSearch.click();
			System.out.println("******Close Button********");
			Thread.sleep(1000);
			/*
			driver.switchTo().defaultContent();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			System.out.println("**************");
			wait.until(ExpectedConditions.visibilityOf(AdultsDropDown));
			System.out.println("***Adults**********");
			AdultsDropDown.click();
			wait.until(ExpectedConditions.visibilityOf(AdvancedOptionsHyperlink));
			System.out.println("***Advance**********");
			AdvancedOptionsHyperlink.click();
			*/
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			wait.until(ExpectedConditions.visibilityOf(SearchButton));
			System.out.println("*******Search Page******");
			SearchButton.click();
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			System.out.println("*******Search Page after click******");
			WizardSearch.click();
			System.out.println("*******Wizard Search Page******");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	    }
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
		
}
