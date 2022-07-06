package test;
import java.io.File;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;


import io.restassured.specification.RequestSpecification;
import utility.ReUsableMethods;

public class TopRatedMovieResponse extends ReUsableMethods{
	
	@Test
	public  void topRatedMovieResponseCheck() throws IOException
	
	{
		RestAssured.baseURI= "https://api.themoviedb.org/3";
		RequestSpecification httpRequest=RestAssured.given();
		
		String response = httpRequest.request(Method.GET,"/movie/top_rated?api_key=acc8dc6e3d4a22bd4fa00d589d0123d1&language=en-US&page=1").asString();
		JsonPath js1=rawToJson(response);
		
		File f1 = new File("");
		String FilePath = f1.getAbsolutePath().concat("\\src\\test\\java\\utility\\TestData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(FilePath);
        XSSFSheet s1 = wb.getSheetAt(0);
        int totalNoOfRows  = s1.getLastRowNum();

        String responseField,dataType;
        for(int i = 2; i <=totalNoOfRows ; i++)
        {
        	responseField=s1.getRow(i).getCell(0).toString();
        	
        	if(js1.get("results").toString().contains(responseField))
        	{
        		Assert.assertTrue(true,"Pass: Response contains "+responseField);
        	}
        	
        	else
        	{
        		Assert.assertTrue(false,"Failure message: Response do not contain "+responseField);
        	}
        	
        }
        
		
	}

	

}
