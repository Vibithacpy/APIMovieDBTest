package test;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import utility.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class VerifyMoviesListedAfter2019 extends ReUsableMethods{
	@Test
	public void VerifyMoviesList()
	{
		
		RestAssured.baseURI= "https://api.themoviedb.org/3";
		
		String response=	given().contentType(ContentType.JSON).log().all().queryParam("api_key", "acc8dc6e3d4a22bd4fa00d589d0123d1")
				.when().get("/movie/top_rated")
				.then().extract().response().asString();
		
		JsonPath js1=rawToJson(response);
		int resultSize = js1.getInt("results.size()");
		ArrayList<String> expectedMovies = new ArrayList<String>();
		expectedMovies.add("Impossible Things");
		expectedMovies.add("Gabriel's Inferno");
		expectedMovies.add("Gabriel's Inferno: Part II");
		expectedMovies.add("Your Eyes Tell");
		expectedMovies.add("Gabriel's Inferno: Part III");
		expectedMovies.add("Violet Evergarden: The Movie");
		ArrayList<String> actualMovies = new ArrayList<String>();
		for(int i=0; i<resultSize; i++)
		{
			if( null != js1.get("results["+i+"].release_date"))
			{
				String releaseDate =  js1.get("results["+i+"].release_date");
				int yearOfRelease = Integer.parseInt(releaseDate.split("-")[0]);
				if( yearOfRelease > 2019 )
				{
					actualMovies.add(js1.get("results["+i+"].title").toString());
					System.out.println(js1.get("results["+i+"].title").toString());
				}
			}
		}
		Assert.assertTrue(VerifyMovies(expectedMovies, actualMovies), "Failure reason: Actual and expected movie names differs.");
	}

	public static boolean VerifyMovies(ArrayList<String> expectedMovies, ArrayList<String> actualMovies )
	{
        boolean result = true;
        if(!(expectedMovies.equals(actualMovies)))
    	{
            result = false;
    	}
        return result;
    }
}
