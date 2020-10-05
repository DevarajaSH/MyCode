package testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import factory.BrowserFactory;
import pageObjects.LoginPage;
import pageObjects.LogoutPage;
import pageObjects.ProductPage;
import utilityFunctions.Utility;

public class TC01_LoginAndAddProductToCart 
{
	public WebDriver driver;
	public WebDriverWait dwait;
	public LoginPage objLoginPage;
	public LogoutPage objLogoutPage;
	Utility objUtility;
	
	public ProductPage objProductPage;
	public static String Logfilename;

	
	@BeforeTest
	@Parameters("browser")
	public void setup(String strbrowser) throws Exception 
	{
		System.out.println("****************");
		driver = BrowserFactory.getBrowser(strbrowser);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String strURl = "http://Automationpractice.com/";
		driver.get(strURl);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		objLoginPage = PageFactory.initElements(driver, LoginPage.class);
		dwait = new WebDriverWait(driver, 100);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		String uname = "jetblue@grr.la";
		String passwd = "jetblue";
		if (uname.isEmpty() || passwd.isEmpty()) 
		{
			BrowserFactory.quitApplication(driver);
			Assert.fail("Test Case Failed due to Username or Passwd is missing !!");
		} 
		else 
		{
			objLoginPage.loginApplication(uname, passwd);
			Utility.TakeScreenShot(driver, "./Screenshots/Successful_Login"+System.currentTimeMillis()+".jpeg");
		}
	}

	@AfterClass
	public void tearDown() throws Exception 
	{
		try
		{
			objLogoutPage = PageFactory.initElements(driver, LogoutPage.class);
			objLogoutPage.logOutApplication();
			driver.close();
			driver.quit();
		}
		catch(UnhandledAlertException e)
		{
			Utility.TakeScreenShot(driver, "./Screenshots/Failure_AfterClass"+System.currentTimeMillis()+".jpeg");
			BrowserFactory.quitApplication(driver);
			Assert.fail("Test Case Failed due to Excpetion in Logout Application!!");
		}
	}

	@Test 
	public void VerifyLogin () throws InterruptedException, IOException 
	{
		System.out.println("Product Page");
		objProductPage = PageFactory.initElements(driver, ProductPage.class);
		String strTShirt = "Faded Short Sleeve T-shirts";
		objProductPage.SelectGivenTShirt(strTShirt);
		Assert.assertEquals(ProductPage.strProductAddedSuccessMessage, "Product successfully added to your shopping cart");	
	}
}
