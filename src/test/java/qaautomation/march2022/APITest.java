package qaautomation.march2022;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APITest {

	String token = "";

	@Test(priority = 1)
	public void testLoginAPI() {
		RestAssured.baseURI = "https://api-staging-builder.engineer.ai";
		// String loginPayload = "{email: \"jogidemo321@gmail.com\", password:
		// \"builder123\"}";
		String loginPayload = "{\"email\": \"jogidemo321@gmail.com\", \"password\": \"builder123\"}";
		Response responseLogin = RestAssured.given().contentType("application/json").body(loginPayload).when()
				.post("/users/sign_in");
		token = responseLogin.jsonPath().get("user.authtoken");
		System.out.println(token);
	}

	@Test(priority = 2)
	public void testDashboardAPI() {
		RestAssured.baseURI = "https://api-staging-builder.engineer.ai";
		Response responseDashboard = RestAssured.given().contentType("application/json").header("authtoken", token)
				.param("status", "completed").when().get("/build_cards");
		assertEquals(responseDashboard.getStatusCode(), 200);

	}

}
