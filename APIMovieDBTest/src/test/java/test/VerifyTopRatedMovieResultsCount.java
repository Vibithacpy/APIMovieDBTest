package test;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import utility.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
public class VerifyTopRatedMovieResultsCount {

	@Test
	public  void verifyTopRatedMovies()
{
		RestAssured.baseURI= "https://api.themoviedb.org/3";
		
		String response=	given().contentType(ContentType.JSON).log().all().queryParam("api_key", "acc8dc6e3d4a22bd4fa00d589d0123d1")
				.when().get("/movie/top_rated")
				.then().extract().response().asString();
		int expectedCount = 20;
		JsonPath js1=ReUsableMethods.rawToJson(response);
		int resultSize = js1.getInt("results.size()");
		System.out.println("Actual movie results count is : " + resultSize);
		System.out.println("Expected movie results count is : " + expectedCount);
		Assert.assertEquals(resultSize, expectedCount);
		Assert.assertTrue(VerifyCountMatches(resultSize, expectedCount), "Failure reason: Actual and expected results count differs.");
	}

	public static boolean VerifyCountMatches(int actual, int expected)
	{
        boolean result = false;
        if(actual==expected){
            result = true;
        }
        return result;
    }
}
