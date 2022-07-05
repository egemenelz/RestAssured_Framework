package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.ConfigReader;


import static io.restassured.RestAssured.*;

public class hook {

    @Before
    public void init() {
        baseURI = "http://" + ConfigReader.readProperty("apiBase") + ":9064";
        System.err.println("-----------------Start of Scenario-----------------");
    }

    @After
    public void teardown() {
        System.err.println("-----------------End of Scenario-----------------");
        reset();
    }


}
