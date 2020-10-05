package factory;

import dataProvider.ExcelConfig;

public class DataProviderFactory 
{
	public static ExcelConfig getMasterData() 
	{
		ExcelConfig config = new ExcelConfig("./TestData/MasterData.xls");
		return config;
	}
}
