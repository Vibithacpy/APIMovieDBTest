package test;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class VerifyStatuscode 

{
@Test
	public  void checkStatuscode200()
	{
		
		RestAssured.baseURI= "https://api.themoviedb.org/3";
		
		Response response=	given().contentType(ContentType.JSON).log().all().queryParam("api_key", "acc8dc6e3d4a22bd4fa00d589d0123d1")
				.when().get("/movie/top_rated")
				.then().extract().response();
		int statuscode=response.statusCode();
		Assert.assertEquals(statuscode,200,"Failure reason: Status code is not 200");
		
	}
	@Test
	public  void checkStatuscode401()
	{
		
		RestAssured.baseURI= "https://api.themoviedb.org/3";
		
		Response response=	given().contentType(ContentType.JSON).log().all().queryParam("api_key", "acc8dc6e3d4a22bd4fa00d589d0123d10")
				.when().get("/movie/top_rated")
				.then().extract().response();
		int statuscode=response.statusCode();
		Assert.assertEquals(statuscode,401,"Failure reason: Status code is not 401");
		
	}
	
	
}
