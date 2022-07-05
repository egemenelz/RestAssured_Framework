package utilities;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TestIssuance {
    public static void main(String[] args) {
        baseURI = "http://" + ConfigReader.readProperty("hsbcIssuance") + ":9064";

        String body = "{\n" +
                "    \"MESSAGE_TYPE\": \"EVENT_NEW_ISSUANCE_DATA\",\n" +
                "    \"SERVICE_NAME\": \"ISSUANCE_EVENT_HANDLER\",\n" +
                "    \"ISSUANCEDETAILS\": {\n" +
                "        \"DATASOURCE_ID\": \"BBG\",\n" +
                "        \"ISSUANCE_DATA_KEY\": \"ZXZXTEST43\",\n" +
                "        \"ISSUER_TICKER\": \"ZXZXTEST43\",\n" +
                "        \"ISSUER_NAME\": \"ZXZXZXTEST43\",\n" +
                "        \"MATURITY_DATE\": \"2063805120000\",\n" +
                "        \"CURRENCY_CODE\": \"USD\",\n" +
                "        \"ISSUER_COUNTRY\": \"USA\",\n" +
                "        \"TRANCHE_CURRENCY\": \"USD\",\n" +
                "        \"ISSUER_RATING\": \"A+//\",\n" +
                "        \"IPTS\": \"SM+11\",\n" +
                "        \"DEAL_STATUS\": \"Cancelled\",\n" +
                "        \"BOOK_STATUS\": \"\",\n" +
                "        \"BOND_SENIORITY\": \"B.Secured\",\n" +
                "        \"TOTAL_ISSUED_AMOUNT\": \"1,000,000,000\",\n" +
                "        \"TRANCHE_SETTLEMENT_DATE\": \"1653649569000\",\n" +
                "        \"FIRST_CALL_DATE\": \"1809169569000\",\n" +
                "        \"IS_PERPETUAL\": true\n" +
                "    }\n" +
                "}";

        given()
                .spec(ReqNResSpec.getReqSpec("SUPER_ADMIN",200)).
                body(body).
                when()
                .post("/event_new_issuance_data").
                then()
                .spec(ReqNResSpec.getRespSpec(200))
        ;
    }
}
