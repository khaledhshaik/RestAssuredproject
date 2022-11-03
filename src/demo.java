import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Reusablemethods;
import files.payload;
public class demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//validate if Add place API is working as expected
		
		//given-all input details
		//when-submit the API
		//Then-validate the response
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123")
		.body(payload.Addplace()).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		JsonPath js=new JsonPath(response);  // for parsing Json
		String placeId=js.getString("place_id");
		System.out.println(placeId);
		
		//Add place --->update place with new address --->Get place to validate if new address is present in response code
		String newaddress="70 Summer walk, USA";
		given().log().all().queryParam("key", "qaclick123")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newaddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}")
		.when().put("/maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		String getPlaceResponse=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", "3d29339f860bcdb4b2cfa4e76c8466ed")
		.when().get("/maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1=Reusablemethods.rawToJson(getPlaceResponse);
		String ActualAddress=js1.getString("address");
		System.out.println(ActualAddress);
		
		Assert.assertEquals(ActualAddress, newaddress);

	}

}
