package step_definitions;

import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;

import utilities.GetToken;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class loginSteps {
    private JsonPath jsonPath;


    @Given("Users enters {string} credentials status code as {string}")
    public void users_enters_login_credentials(String user, String statusCode) {
        int statusC = Integer.parseInt(statusCode);
        jsonPath = GetToken.login(user, statusC);
    }

    @When("user has this profile {string}")
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

}
