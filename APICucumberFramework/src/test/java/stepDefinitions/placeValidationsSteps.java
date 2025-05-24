package stepDefinitions;

import java.io.IOException;
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
import resources.TestDataBuild;
import resources.utils;

public class placeValidationsSteps extends utils{
	RequestSpecification response;
	ResponseSpecification resspec;
	Response getResponse;
	TestDataBuild tb = new TestDataBuild();

	@Given("Add Place Playload")
	public void add_place_playload() throws IOException {
		response = given().spec(requestSpecification()).body(tb.addPayload());
	}

	@When("User calls {string} with post http request")
	public void user_calls_with_post_http_request(String apiName) {
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
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
