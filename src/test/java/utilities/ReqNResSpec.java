package utilities;

import io.restassured.http.ContentType;
import io.restassured.specification.*;

import static io.restassured.RestAssured.*;


public class ReqNResSpec {
    /**
     * These two methods, basically same method but,
     * one of them is for Request and the other one is for Response
     * to Use that methods, instead of creating header on the request. will create
     * .spec(ReqNResSpec.getReqSpec(userType, statusCode))) for Request
     * .spec(ReqNResSpec.getRespSpec(statusCode)) for Response
     * if need to, can add additional method after specs
     *
     * @param userType to logged in to system with login credentials
     * @return
     * @author Egemen Eliz
     */

    private static Object SAT;
    private static Object RAT;
    private static RequestSpecification reqSpec;
    private static ResponseSpecification respSpec;

    public static RequestSpecification getReqSpec(String userType, int statusCode) {
        SAT = GetToken.login(userType, statusCode).getString("SESSION_AUTH_TOKEN");
        RAT = GetToken.login(userType, statusCode).getString("REFRESH_AUTH_TOKEN");

        reqSpec = given()
                .header(String.valueOf(Headers.SOURCE_REF), "1234")
                .header(String.valueOf(Headers.SESSION_AUTH_TOKEN), SAT)
                .header(String.valueOf(Headers.REFRESH_AUTH_TOKEN), RAT)
                .log().uri()
                .log().method()
                .log().headers()
                .contentType(ContentType.JSON)
        ;

        return reqSpec;
    }

    public static ResponseSpecification getRespSpec(int statusCode) {
        respSpec = expect().statusCode(statusCode)
                .contentType(ContentType.JSON)
                .log().body();
        return respSpec;
    }

}
