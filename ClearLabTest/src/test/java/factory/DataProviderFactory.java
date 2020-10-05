package factory;

import dataProvider.ConfigurationFile;
import dataProvider.ExcelConfig;

public class DataProviderFactory 
{
	public static ConfigurationFile getProperty() 
	{
		ConfigurationFile config = new ConfigurationFile("./src/test/resources/Configuration/project.properties");
		return config;
	}
	
	public static ExcelConfig getMasterData() 
	{
		ExcelConfig config = new ExcelConfig("./TestData/MasterData.xls");
		return config;
	}
}
