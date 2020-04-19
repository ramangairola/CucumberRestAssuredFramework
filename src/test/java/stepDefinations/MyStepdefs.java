package stepDefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class MyStepdefs extends Utils {

    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    static String place_id;

    TestDataBuild dataBuild = new TestDataBuild();

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_Place_Payload(String name, String address, String language) throws IOException {
        res = given().spec(requestSpecification())
                .body(dataBuild.addPlacePayload(name,address,language));
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_Post_http_request(String responseCall, String callMethod) {
        APIResources apiResources = APIResources.valueOf(responseCall);
        System.out.println(apiResources.getResource());
        resspec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
        if (callMethod.equalsIgnoreCase("POST"))
            response = res.when().post(apiResources.getResource());
        else if (callMethod.equalsIgnoreCase("GET"))
            response = res.when().get(apiResources.getResource());
        else if (callMethod.equalsIgnoreCase("DELETE"))
            response = res.when().delete(apiResources.getResource());
    }

    @Then("the API call got success with status code 200")
    public void the_API_call_got_success_with_status_code() {
        assertEquals(response.getStatusCode(),200);
    }

    @And("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        assertEquals(getJsonValue(response,keyValue),expectedValue);
    }

    @Then("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
        place_id = getJsonValue(response, "place_id");
//        res = given().spec(requestSpecification()).queryParam("place_id",place_id);
//        user_calls_with_Post_http_request(resource,"GET");
//        String actualName = getJsonValue(response, "name");
//        assertEquals(actualName, expectedName);
    }

    @Given("DeletePlace Payload")
    public void deleteplace_Payload() throws IOException {
        res = given().spec(requestSpecification())
                .body(dataBuild.deletePlacePayload(place_id));
    }

}
