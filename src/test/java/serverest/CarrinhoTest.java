package serverest;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class CarrinhoTest extends BaseTest{

    @Test
    public void deveListarCarrinhos() {
        when()
                .get("/carrinhos")
                .then()
                .statusCode(200)
                .body("carrinhos", not(empty())).log().all();
    }

    @Disabled
    @Test
    public void naoDeveCadastrarCarrinhoSemProdutos() {
        String carrinhoVazio = "{\"produtos\":[]}";

        RequestSpecification teste = given()
                .contentType(ContentType.JSON)
                .header("Authorization", TOKEN)
                .body(carrinhoVazio).log().all();

                when()
                .post("/carrinhos")
                .then()
                .statusCode(400).log().all()
                .body("produtos", containsString("produtos não contém 1 valor obrigatório"))
                .log().all();
    }
}
