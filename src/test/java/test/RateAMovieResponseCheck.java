package test;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utility.ReUsableMethods;

public class RateAMovieResponseCheck extends ReUsableMethods{
	
	String movieID,postString;
	Map<String, Object>  jsonAsMap = new HashMap<>();
	
	@Test
	public  void topRatedMovieCheckStatuscode201() throws IOException
	
	{
		RestAssured.baseURI= "https://api.themoviedb.org/3";		
		jsonAsMap.put("value", 8.5);
		movieID="278";		
		postString="/movie/"+movieID+"/rating";		
		
		
		Response response=	given().contentType(ContentType.JSON).log().all().
				queryParam("api_key", "acc8dc6e3d4a22bd4fa00d589d0123d1").
				queryParam("session_id","cc5776c2051b1afb2a627d50e6457277275ca71c").body(jsonAsMap)
				.when().post(postString)
				.then().extract().response();
		
		int statuscode = response.getStatusCode();
		
		Assert.assertEquals(statuscode,201,"Failure reason: Status code is not 201");

		
	}
	
	@Test
	public  void topRatedMovieCheckStatuscode401() throws IOException
	
	{
		RestAssured.baseURI= "https://api.themoviedb.org/3";
		
		jsonAsMap.put("value", 8.5);
		movieID="278";		
		postString="/movie/"+movieID+"/rating";		
		
		
		Response response=	given().contentType(ContentType.JSON).log().all().
				queryParam("api_key", "acc8dc6e3d4a22bd4fa00d589d0123d1_junk").
				queryParam("session_id","cc5776c2051b1afb2a627d50e6457277275ca71c").body(jsonAsMap)
				.when().post(postString)
				.then().extract().response();
		
		int statuscode = response.getStatusCode();
		
		Assert.assertEquals(statuscode,401,"Failure reason: Status code is not 401");

		
	}
	
	@Test
	public  void topRatedMovieCheckStatuscode404() throws IOException
	
	{
		RestAssured.baseURI= "https://api.themoviedb.org/3";
		
		jsonAsMap.put("value", 8.5);
		movieID="junk";		
		postString="/movie/"+movieID+"/rating";		
		
		
		Response response=	given().contentType(ContentType.JSON).log().all().
				queryParam("api_key", "acc8dc6e3d4a22bd4fa00d589d0123d1").
				queryParam("session_id","cc5776c2051b1afb2a627d50e6457277275ca71c").body(jsonAsMap)
				.when().post(postString)
				.then().extract().response();
		
		int statuscode = response.getStatusCode();
		
		Assert.assertEquals(statuscode,404,"Failure reason: Status code is not 404");

		
	}
	
}