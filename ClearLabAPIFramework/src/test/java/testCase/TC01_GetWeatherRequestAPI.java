package testCase;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import factory.DataProviderFactory;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import testUtilityFunctions.Utility;

public class TC01_GetWeatherRequestAPI 
{
       public static String strURL;
       public static String strMainURL;       
       public static String strClearLabAPIToken;
       public static String strDevarajaAPIToken;
       public static String strLocationName;
       public static String strStationID;
       public static String strStationName;
       
       public static String inputJsonFolder="jsonFileInput/";
       public static String outputJsonFolder="jsonFileOutput/";
     
       private String strStationFileName;

       @BeforeClass
       public void setup() 
       {
    	   System.out.println("*********Before Setup******************");
    	   strURL = DataProviderFactory.getEnvironmentDetails().getData("EnvironmentDetails", 1, 1);
    	   strMainURL = DataProviderFactory.getEnvironmentDetails().getData("EnvironmentDetails", 2, 1);
    	   strClearLabAPIToken = DataProviderFactory.getEnvironmentDetails().getData("EnvironmentDetails", 3, 1);
    	   strDevarajaAPIToken = DataProviderFactory.getEnvironmentDetails().getData("EnvironmentDetails", 4, 1);
    	   strLocationName = DataProviderFactory.getEnvironmentDetails().getData("EnvironmentDetails", 5, 1);
      	   System.out.println("*********After Setup******************");
        }       
      
       @Test (priority=1)
       public void GetStation () throws InterruptedException 
       {  
    	   System.out.println("**********************Clear Lab- Before Get Station Details**************************************************");
    	   try
    	   {
    		   HashMap<String, String> ToGetQueryparam = new HashMap<String, String>();
        	   ToGetQueryparam.put("q", strLocationName);
        	   ToGetQueryparam.put("APPID", strClearLabAPIToken);
        	   			given()
        	   			.queryParams(ToGetQueryparam)
         	   			.get("https://"+strURL+"/data/2.5/weather")
         			    .then()
         			    .statusCode(200)
         			    .body("coord.lat", is(51.51f))
         			    .log().all();
    		   System.out.println("**********************Clear Lab- After Get Station Details**************************************************");
    	   }
    	   catch(Exception e)
    	   {
    		   e.printStackTrace();
    	   }
       }
    
       @Test (priority=2)
       public void CreateStation () throws InterruptedException 
       {  
    	   System.out.println("**********************Test- Before Create Station**************************************************");
    	   try
    	   {
    		   HashMap<String, String> queryparam = new HashMap<String, String>();
    		   queryparam.put("APPID", strDevarajaAPIToken);
    		   String strStation_Name = "APIAutomation-"+Utility.getRandomString(5);
    		   strStationFileName =  inputJsonFolder + "StationInput.json";
    		   String jsonBody =  getJsonFromFile(strStationFileName, strStation_Name, "<Station_Name>"); 
    		   
    		   RestAssured.baseURI = "http://"+strMainURL+"/data/3.0";
    		   given()
    		   .queryParams(queryparam)
    		   .header("Content-Type", "application/json")
    		   .contentType(ContentType.JSON)
    		   .accept(ContentType.JSON)
    		   .body(jsonBody)
    		   .post("/stations")
 			   .then()
 			   .statusCode(201)
 			   .log().all()
 			   .extract().response();
    		   System.out.println("**********************Test- After Create Station**************************************************");
    	   }
    	   catch(Exception e)
    	   {
    		   e.printStackTrace();
    	   }
       }
       
       @Test (priority=3)
       public void GetAllWeatherStations () throws InterruptedException 
       {   
           System.out.println("**********************Get Weather Stations**************************************************");
           try
      	   {
      		   HashMap<String, String> queryparam = new HashMap<String, String>();
      		   queryparam.put("APPID", strDevarajaAPIToken);  
      		   Response objGetStationResponse = given().accept("application/json")
      		   .queryParams(queryparam)
       		   .get("https://"+strMainURL+"/data/3.0/stations")
   			   .then().log().all()
			   .statusCode(200)
			   .extract().response();
      		    strStationID = "5f8f3067cca8ce000188baf1";
      		    strStationID = "<StationName>";
      		   System.out.println("jsonPath : "+objGetStationResponse.getBody().toString());
    		   System.out.println("Station ID : "+strStationID+ "StationName : "+strStationName);
      		   System.out.println("**********************After Get Station**************************************************");
      	   }
      	   catch(Exception e)
      	   {
      		   e.printStackTrace();
      	   }
       }
       
       @Test (priority=4)
       public void UpdateStation () throws InterruptedException 
       {  
         System.out.println("**********************Update Weather Station**************************************************");
         try
    	   {
    		   HashMap<String, String> queryparam = new HashMap<String, String>();
    		   queryparam.put("appid", strDevarajaAPIToken);  
    		   
    		   String strStation_Update = "APIAutomationUpdate-"+Utility.getRandomString(5);
    		   strStationFileName =  inputJsonFolder + "StationInput.json";
    		   String jsonBody =  getJsonFromFile(strStationFileName, strStation_Update, "<Station_Name>"); 
    		   
    		   given()
    		   .header("Content-Type", "application/json")
    		   .contentType(ContentType.JSON)
    		   .accept(ContentType.JSON)
    		   .queryParams(queryparam)
    		   .body(jsonBody)
     		   .put("https://"+strMainURL+"/data/3.0/stations/"+strStationID)
 			   .then().log().all();
    		   System.out.println("**********************After Get Station**************************************************");
    	   }
    	   catch(Exception e)
    	   {
    		   e.printStackTrace();
    	   }
        }
              
       public String getJsonFromFile(String LocationFileName, String id1, String searchId1) 
	   { 
			String jsonBody = null;
			try 
			{
				jsonBody = new String(Files.readAllBytes(Paths.get(LocationFileName)));   		   
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return jsonBody.replace(searchId1, id1);
		}
}

