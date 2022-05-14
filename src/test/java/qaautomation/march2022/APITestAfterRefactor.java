package qaautomation.march2022;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import qaautomation.march2022.utils.DataUtility;

public class APITestAfterRefactor extends BaseAPITest {

	@Test
	public void testDashboardAPI() {
		Response responseDashboard = given().spec(loginJsonSpec).param("status", "completed").when()
				.get("/build_cards");

		responseDashboard.then().assertThat()
				.body(matchesJsonSchema(DataUtility.getDataFromExcel("Schemas", "DashboardAPISchema")));

		assertEquals(responseDashboard.getStatusCode(), 200);

	}

	@Test
	public void testDashboardWithoutToken() {
		Response responseDashboard = given().spec(commonJsonSpec).param("status", "completed").when()
				.get("/build_cards");

		responseDashboard.then().assertThat()
				.body(matchesJsonSchema(DataUtility.getDataFromExcel("Schemas", "DashboardNoTokenSchema")));

	}

	@Test
	public void testDashboardWithoutParam() {
		Response responseDashboard = given().spec(loginJsonSpec).when().get("/build_cards");

		responseDashboard.then().assertThat()
				.body(matchesJsonSchema(DataUtility.getDataFromExcel("Schemas", "DashboardNoParamSchema")));

	}

}
