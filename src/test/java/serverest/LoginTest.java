package serverest;
import dto.LoginDTO;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LoginTest extends  BaseTest{

    @Test
    public void deveFazerLoginComSucesso() {
        given()
                .contentType(ContentType.JSON)
                .body(new LoginDTO("fulano@qa.com","teste"))
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .body("message", containsString("Login realizado com sucesso")).log().all();
    }
    @Test
    public void deveFalharLoginComSenhaInvalida() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"email\":\"fulano@qa.com\",\"password\":\"senhaInvalida\"}")
                .when()
                .post("/login")
                .then()
                .statusCode(401).log().all()
                .body("message", containsString("Email e/ou senha inválidos"))
                .body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath("schemas/login-NOK-schema.json"));;
    }
}
