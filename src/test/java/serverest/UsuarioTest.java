package serverest;

import dto.UsuarioReqDTO;
import dto.UsuarioRespDTO;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        UsuarioReqDTO usuarioReqDTO = new UsuarioReqDTO("true");
        given()
                .contentType(ContentType.JSON)
                .body(usuarioReqDTO)
                .when()
                .post("/usuarios")
                .then().log().all()
                .statusCode(HttpStatus.SC_CREATED)
                .body("message", containsString("Cadastro realizado com sucesso"));
    }
    @Test
    public void deveCadastrarNovoUsuarioEListar() {
        UsuarioReqDTO usuarioReqDTO = new UsuarioReqDTO("true");
        String id = given()
                .contentType(ContentType.JSON)
                .body(usuarioReqDTO)
                .when()
                .post("/usuarios")
                .then().log().all()
                .statusCode(HttpStatus.SC_CREATED)
                .body("message", containsString("Cadastro realizado com sucesso"))
                .extract().jsonPath().get("_id");
        System.out.println(id);
        UsuarioRespDTO usuarioResponse = new UsuarioRespDTO();
        usuarioResponse = given()
                .contentType(ContentType.JSON)
                .when().log().all()
                .get("/usuarios/"+id)
                .then()
                .statusCode(200)
                .extract().as(UsuarioRespDTO.class);
        System.out.println(usuarioResponse.getEmail());
        Assertions.assertEquals(usuarioReqDTO.getNome(),usuarioResponse.getNome());
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

