package step_definitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;

import io.restassured.path.json.JsonPath;

import utilities.GetToken;
import utilities.PoJos.Details;
import utilities.PoJos.DetailsPOJO;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class loginSteps {
    private JsonPath jsonPath;
    private String username;
    private String password;


    @Given("Users enters {string} credentials status code as {string}")
    public void users_enters_login_credentials(String user, String statusCode) {
        int statusC = Integer.parseInt(statusCode);
        jsonPath = GetToken.login(user, statusC);
    }

    @When("Users expected profile matches with the actual {string}")
    public void user_has_investor_profile(String profiles) {
        String profile = jsonPath.getString("PROFILE");
        assertThat(profile, is(profiles));
    }


    @Then("user has the following permissions {string}")
    public void user_has_the_following_permissions(String permissions) {
        List<String> getPermissionList = jsonPath.getList("PERMISSION");
        String[] arr = permissions.split(",");
        List<String> permList = new ArrayList<>(Arrays.asList(arr));
        assertThat(getPermissionList, is(permList));
    }

    @Given("Users enters {string} and {string} credentials")
    public void users_enters_and_wrong_password_credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @When("User send a request to {string} route")
    public void user_send_a_request_to_route(String url) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        Details details = new Details();
        details.setUSER_NAME(username);
        details.setPASSWORD(password);

        DetailsPOJO detailsPOJO = new DetailsPOJO();
        detailsPOJO.setDETAILS(details);
        try {
            jsonPath =
                    given()
                    .header("SOURCE_REF", "1234")
                    .contentType(ContentType.JSON)
                    .body(ow.writeValueAsString(detailsPOJO)).
            when()
                    .post(url).
            then()
                    .extract().jsonPath();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("The system response the request with {string} message")
    public void the_system_response_the_request_with_message(String expectedErrorMessage) {
        String actualErrorMessage = jsonPath.getString("ERROR[0].CODE");
        assertThat(actualErrorMessage, is(expectedErrorMessage));
    }


}
