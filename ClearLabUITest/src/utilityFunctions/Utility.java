package utilityFunctions;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

public class Utility 
{
	public static void TakeScreenShot(WebDriver driver1, String strFilePath)
	{
        TakesScreenshot scrShot =((TakesScreenshot)driver1);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(strFilePath);
        try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
