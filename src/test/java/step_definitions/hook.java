package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.ConfigReader;


import static io.restassured.RestAssured.*;

public class hook {

    @Before
    public void init() {
        baseURI = "http://" + ConfigReader.readProperty("apiBase") + ":9064";

    }

    @After
    public void teardown() {
        reset();
    }


}
