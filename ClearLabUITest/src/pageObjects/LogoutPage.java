package pageObjects;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LogoutPage 
{
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(css = "a.logout")
	WebElement LogOutlink;
	
		
	public LogoutPage(WebDriver driver)
	{
		this.driver = driver;
		wait = new WebDriverWait(driver,100);
	}
	
	public void logOutApplication() throws InterruptedException 
	{
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(LogOutlink));
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		LogOutlink.click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Thread.sleep(2500);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		System.out.println("LogoutPage: Logout Sucessfull");
	}
}
