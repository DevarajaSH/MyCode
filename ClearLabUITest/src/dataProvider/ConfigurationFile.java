package dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationFile 
{
	Properties pro;
	
	public ConfigurationFile (String path) 
	{
		try 
		{
			pro = new Properties();
			pro.load(new FileInputStream(new File(path)));
		} 
		catch (Exception e) {
			System.out.println("Unable to locate file " + e.getMessage());
		}
	}
	
	public String getBrowser() 
	{
		return pro.getProperty("Browser");
	}

	public String getTimeOut() {
		return pro.getProperty("TimeOut");
	}
}
