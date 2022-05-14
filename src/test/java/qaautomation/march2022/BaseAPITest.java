package qaautomation.march2022;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeMethod;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import qaautomation.march2022.utils.DataUtility;

public class BaseAPITest {
	RequestSpecification commonJsonSpec = new RequestSpecBuilder().setBaseUri("https://api-staging-builder.engineer.ai")
			.setContentType(ContentType.JSON).build().log().all();
	RequestSpecification loginJsonSpec;
	String token;

	@BeforeMethod
	public void login() {
		// String loginPayload = "{\"email\": \"jogidemo321@gmail.com\", \"password\":
		// \"builder123\"}";
		String loginPayload = DataUtility.getDataFromExcel("Payloads", "LoginPayload");
		Response responseLogin = given().spec(commonJsonSpec).body(loginPayload).when().post("/users/sign_in");
		token = responseLogin.jsonPath().get("user.authtoken");
		loginJsonSpec = new RequestSpecBuilder().setBaseUri(DataUtility.getDataFromExcel("Config", "BaseUrlAPI"))
				.setContentType(ContentType.JSON).addHeader("authtoken", token).build().log().all();
		System.out.println(token);
	}

}
