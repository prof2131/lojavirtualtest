package serverest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class ProdutoTest extends BaseTest{
    @Test
    public void deveListarProdutos() {
        when()
                .get("/produtos")
                .then()
                .statusCode(200)
                .body("produtos", not(empty()));
    }

    @Test
    public void naoDeveCadastrarProdutoSemToken() {
        String produto = "{\"nome\":\"Teclado\",\"preco\":200,\"descricao\":\"Teclado mecânico RGB\",\"quantidade\":5}";

        given()
                .contentType(ContentType.JSON)
                .body(produto)
                .when()
                .post("/produtos")
                .then()
                .statusCode(401)
                .body("message", containsString("Token de acesso ausente"));
    }
}
