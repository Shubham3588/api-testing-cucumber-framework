package stepDefinitions;

import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.getMaps;
import pojo.location;

public class placeValidationsSteps {
	RequestSpecification response;
	ResponseSpecification resspec;
	Response getResponse;

	@Given("Add Place Playload")
	public void add_place_playload() {
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		getMaps gm = new getMaps();
		gm.setAccuracy(62);
		gm.setAddreess("Kaverappa Layout");
		gm.setLanguage("Hindi");
		gm.setPhoneNumber("7070707070");
		gm.setName("Ankit");
		gm.setWebsite("www.facebook.com");
		List<String> myList = new ArrayList<String>();
		myList.add("Shoe Park");
		myList.add("Shoe");
		gm.setTypes(myList);
		location loc = new location();
		loc.setLat(-24.589);
		loc.setLng(58.3654);
		gm.setLocation(loc);

		RequestSpecification req = new RequestSpecBuilder()
			.setBaseUri("https://rahulshettyacademy.com")
			.addQueryParam("key", "qaclick123")
			.setContentType(ContentType.JSON).build();

		resspec = new ResponseSpecBuilder()
			.expectStatusCode(200)
			.expectContentType(ContentType.JSON).build();

		response = given().spec(req).body(gm);
	}

	@When("User calls {string} with post http request")
	public void user_calls_with_post_http_request(String apiName) {
		getResponse = response.when()
			.post("/maps/api/place/add/json")
			.then().assertThat().spec(resspec)
			.extract().response();
	}

	@Then("the API call is success with status code {string}")
	public void the_api_call_is_success_with_status_code(String code) {
		assertEquals(Integer.parseInt(code), getResponse.getStatusCode());
	}

	@And("the {string} in response body is {string}")
	public void the_in_response_body_is(String key, String expectedValue) {
		String resp = getResponse.asString();
		System.out.println("Full response: " + resp);
		JsonPath js = new JsonPath(resp);
		Object actualValue = js.get(key);

		if (actualValue == null) {
			throw new AssertionError("Key '" + key + "' not found in response");
		}

		assertEquals(expectedValue, actualValue.toString());
	}
}
