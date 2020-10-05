package factory;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserFactory 
{
	@SuppressWarnings("deprecation")
	public static WebDriver getBrowser(String browserName) throws MalformedURLException 
	{
		WebDriver driver = null;
		
		if (browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./src/test/resources/Configuration/chromedriver.exe");
			@SuppressWarnings("static-access")
			DesiredCapabilities capabilities = new DesiredCapabilities().chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-web-security");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability("requireWindowFocus", true);
			capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE); // Accept
																													// unexpected
																													// alerts
			driver = new ChromeDriver(capabilities);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	public static void quitApplication(WebDriver driver) {
		driver.quit();
	}
}
