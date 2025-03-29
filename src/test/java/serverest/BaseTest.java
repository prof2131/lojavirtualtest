package serverest;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class BaseTest {
    public static String TOKEN;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:3000";
        TOKEN = given()
                    .contentType(ContentType.JSON)
                    .body("{\"email\":\"fulano@qa.com\",\"password\":\"teste\"}")
                    .when()
                    .post("/login")
                    .then()
                    .statusCode(200)
                    .body("message", containsString("Login realizado com sucesso"))
                .extract().jsonPath().get("authorization");
    }
}
