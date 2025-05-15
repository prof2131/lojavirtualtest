package serverest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeAll;

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
