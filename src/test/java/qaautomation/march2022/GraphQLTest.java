package qaautomation.march2022;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import qaautomation.march2022.utils.GraphQLRequestBuilder;

public class GraphQLTest {

	@Test
	public void checkCEO() {
		GraphQLRequestBuilder gql = new GraphQLRequestBuilder();
		String query = "{  company {    ceo    coo    cto    cto_propulsion  }}";
		gql.setQuery(query);
		String jsonStringQuery = gql.getJSONString();

		Response response = given().contentType(ContentType.JSON).body(jsonStringQuery).when()
				.post("https://api.spacex.land/graphql/");
		String ceoName = response.jsonPath().get("data.company.ceo");
		assertEquals(ceoName, "Elon Musk");

	}

	@Test
	public void launchRocketWithVariable() {
		JSONObject variables = new JSONObject();
		variables.put("limit", 5);

		GraphQLRequestBuilder gql = new GraphQLRequestBuilder();
		String query = "query getLaunches($limit:Int!){  launches(limit: $limit) {    mission_name  }}";
		gql.setQuery(query);
		gql.setVariables(variables.toString());
		String jsonStringQuery = gql.getJSONString();

		Response response = given().contentType(ContentType.JSON).body(jsonStringQuery).when()
				.post("https://api.spacex.land/graphql/");
		String missionName = response.jsonPath().get("data.launches[0].mission_name");
		System.out.println(missionName);
		assertEquals(missionName, "Thaicom 6");

	}
}
