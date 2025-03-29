package serverest;

import com.github.javafaker.Faker;
import dto.UsuarioDTO;
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

    @Test
    public void deveCadastrarNovoUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO("true");
        given()
                .contentType(ContentType.JSON)
                .body(usuarioDTO)
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

