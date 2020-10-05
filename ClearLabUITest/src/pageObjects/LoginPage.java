package pageObjects;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage 
{
	WebDriver driver;
	WebDriverWait wait;
	
	//@FindBy(xpath = "//a[@class='login']")
	@FindBy(css = "a.login")
	WebElement SignInlink;
	
	@FindBy(id = "email")
	WebElement username;
	
	@FindBy(id = "passwd")
	WebElement password;

	@FindBy(xpath = "//p[@class='submit']//span[1]")
	WebElement SignInButton;
	
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		wait = new WebDriverWait(driver,100);
	}
	
	public void loginApplication(String strUsername, String strPassword) 
	{			
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(SignInlink));
			SignInlink.click();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			username.sendKeys(strUsername);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
			password.sendKeys(strPassword);	
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
			wait.until(ExpectedConditions.elementToBeClickable(SignInButton));
			SignInButton.click();
	    }
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
		
}
