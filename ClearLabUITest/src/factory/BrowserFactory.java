package factory;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserFactory 
{
	public static WebDriver getBrowser(String browserName) throws MalformedURLException 
	{
		WebDriver driver = null;
		if (browserName.equalsIgnoreCase("Firefox")) 
		{
			System.setProperty("webdriver.gecko.driver", "./Configuration/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		if (browserName.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./Configuration/chromedriver.exe");
			DesiredCapabilities capabilities = new DesiredCapabilities().chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-web-security");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability("requireWindowFocus", true);
			capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE); 
			driver = new ChromeDriver(capabilities);
		}
		if (browserName.equalsIgnoreCase("MSEdge")) 
		{
			System.setProperty("webdriver.edge.driver", "./Configuration/msedgedriver.exe");
			driver = new EdgeDriver();
			String strURl = "http://Automationpractice.com/";
			driver.get(strURl);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}

	public static void quitApplication(WebDriver driver) 
	{
		driver.quit();
	}
}
