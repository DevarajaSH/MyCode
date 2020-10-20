package factory;

import dataProvider.ExcelConfig;

public class DataProviderFactory 
{
	public static ExcelConfig getEnvironmentDetails() 
	{
		ExcelConfig config = new ExcelConfig("./Configuration/ClearLabEnvironmentDetails.xls");
		return config;
	}
}
