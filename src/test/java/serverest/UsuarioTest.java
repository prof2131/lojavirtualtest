package serverest;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UsuarioTest extends BaseTest {

    @Test
    public void deveListarUsuarios() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/usuarios")
                .then()
                .statusCode(200)
                .body("usuarios", not(empty()));
    }

    @Disabled
    @Test
    public void deveCadastrarNovoUsuario() {
        String usuario = "{\"nome\":\"João Silva\",\"email\":\"joao_silva@example.com\",\"password\":\"senha123\",\"administrador\":\"true\"}";
        given()
                .contentType(ContentType.JSON)
                .body(usuario)
                .when()
                .post("/usuarios")
                .then().log().all()
                .statusCode(HttpStatus.SC_CREATED)
                .body("message", containsString("Cadastro realizado com sucesso"));
    }


    @Test
    public void naoDeveCadastrarUsuarioComEmailDuplicado() {
        String usuarioExistente = "{\"nome\":\"Fulano da Silva\",\"email\":\"fulano@qa.com\",\"password\":\"teste\",\"administrador\":\"false\"}";

        given()
                .contentType(ContentType.JSON)
                .body(usuarioExistente)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(400)
                .body("message", containsString("Este email já está sendo usado"));
    }
}

