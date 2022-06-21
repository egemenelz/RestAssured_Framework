package step_definitions;

import io.cucumber.java.en.*;

import io.restassured.path.json.JsonPath;
import utilities.ReqNResSpec;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class userSteps {
    private JsonPath jsonPath;
    int rowSize;


    @Given("{string} user send a request to endpoint with {string} status code as {string}")
    public void user_send_a_request_to_endpoint_with(String userType, String endPoint, String stCode) {
        int statusCode = Integer.parseInt(stCode);
        jsonPath =
                given()
                        .spec(ReqNResSpec.getReqSpec(userType, statusCode))
                        .body("{\n" +
                                "    \"DETAILS\":\n" +
                                "        {\n" +
                                "            \n" +
                                "        }\n" +
                                "}").

                when()
                        .post(endPoint).
                then()
                        .spec(ReqNResSpec.getRespSpec(statusCode))
                        .extract().jsonPath()
        ;
    }

    @And("Message type on payload is {string}")
    public void message_type_on_payload_is(String message) {
        String messageType = jsonPath.getString("MESSAGE_TYPE");
        assertThat(messageType, is(message));
    }

    @When("User views the row on the payload")
    public void user_views_the_row_on_the_payload() {
        List<String> userName = new ArrayList<>();
        int size = jsonPath.getList("ROW").size();
        rowSize = size;
        for (int i = 0; i < size ; i++) {
            userName.add(jsonPath.getString("ROW["+i+"].USER_NAME"));
        }
    }

    @Then("ROWS_COUNT must be equal to ROW")
    public void rows_count_must_be_equal_to_row() {
        String rowsCount = jsonPath.getString("ROWS_COUNT");
        int rowCount = Integer.parseInt(rowsCount);
        assertThat(rowCount, is(equalTo(rowSize)));
    }
}
