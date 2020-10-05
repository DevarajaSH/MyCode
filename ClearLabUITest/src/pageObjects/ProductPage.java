package pageObjects;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilityFunctions.Utility;


public class ProductPage 
{
	WebDriver driver;
	WebDriverWait wait;

	public static String strProductAddedSuccessMessage = "";
	public static String strProductFullName = "";
	
	@FindBy(xpath = "//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li[3]")
	WebElement HeaderTShirtLink;
	
	@FindBy(xpath = "//a[contains(text(),'Faded Short Sleeve T-shirts')]")
	WebElement ProductName;
	
	@FindBy(xpath = "//span[contains(text(),'Add to cart')]")
	WebElement AddToCartButton;

	@FindBy(xpath = "//img[@id='bigpic']")
	WebElement TShirtImage;
	
	@FindBy(xpath = "//div[@class='layer_cart_product col-xs-12 col-md-6']//h2[1]")
	WebElement ProductSuccessfullyAddedMessage;
	
	@FindBy(xpath = "//span[@id='layer_cart_product_title']")
	WebElement ProductNameInPopup;
	
	@FindBy(xpath = "//span[@id='layer_cart_product_attributes']")
	WebElement ProductColorAndSize;
	
	@FindBy(xpath = "//span[@id='layer_cart_product_quantity']")
	WebElement ProductQuantityNumber;
	
	@FindBy(xpath = "//span[@id='layer_cart_product_price']")
	WebElement ProductPrice;
	
	@FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
	WebElement ProceedToCheckOut;
	
	
	public ProductPage(WebDriver driver)
	{
		this.driver = driver;
		wait = new WebDriverWait(driver,100);
	}
	
	public void SelectGivenTShirt(String strTShirtName) 
	{			
		try
		{
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			wait.until(ExpectedConditions.visibilityOf(HeaderTShirtLink));
			HeaderTShirtLink.click();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			wait.until(ExpectedConditions.visibilityOf(ProductName));
			String strValue = ProductName.getAttribute("title");
			if(strValue.equalsIgnoreCase(strTShirtName))
			{
				ProductName.click();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				wait.until(ExpectedConditions.visibilityOf(TShirtImage));
				AddToCartButton.click();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				wait.until(ExpectedConditions.visibilityOf(ProductSuccessfullyAddedMessage));
				strProductAddedSuccessMessage = ProductSuccessfullyAddedMessage.getText();
				System.out.println("strProductMessage: "+strProductAddedSuccessMessage);
				wait.until(ExpectedConditions.visibilityOf(ProductNameInPopup));
				strProductFullName = ProductNameInPopup.getText();
				String strProductColor = ProductColorAndSize.getText();
				String strProductQuantityNumber = ProductQuantityNumber.getText();
				String strProductPrice = ProductPrice.getText();
				System.out.println("strProductFullName: "+strProductFullName);
				System.out.println(""+strProductColor);
				System.out.println(""+strProductQuantityNumber);
				System.out.println(""+strProductPrice);
				wait.until(ExpectedConditions.visibilityOf(ProceedToCheckOut));
				ProceedToCheckOut.click();
			}
	    }
		catch(Exception e)
		{
			e.printStackTrace();
			Utility.TakeScreenShot(driver, "./Screenshots/Failure_Productpage"+System.currentTimeMillis()+".jpeg");
		}
	}
		
}
