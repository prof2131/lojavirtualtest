package serverest;

import dto.UsuarioReqDTO;
import dto.UsuarioRespDTO;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UsuarioTest extends BaseTest {

    static UsuarioReqDTO usuarioReqDTO;
    static String idUsuarioCadastrado;

    @BeforeAll
    static void gerarMassaDados(){
        usuarioReqDTO = new UsuarioReqDTO("true");
        idUsuarioCadastrado = given()
                .contentType(ContentType.JSON)
                .body(usuarioReqDTO).log().all()
                .when()
                .post("/usuarios")
                .then().log().all()
                .statusCode(HttpStatus.SC_CREATED).log().all()
//                .body("message", containsString("Cadastro realizado com sucesso"))
                .extract().jsonPath().get("_id");
    }

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

        UsuarioRespDTO usuarioResponse;
        usuarioResponse = given()
                .contentType(ContentType.JSON)
                .when().log().all()
                .get("/usuarios/"+idUsuarioCadastrado)
                .then()
                .statusCode(200)
                .extract().as(UsuarioRespDTO.class);
        Assertions.assertEquals(usuarioReqDTO.getNome(),usuarioResponse.getNome());
    }
    @Test
    public void naoDeveCadastrarUsuarioComEmailDuplicado() {

        given()
                .contentType(ContentType.JSON)
                .body(usuarioReqDTO)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", containsString("Este email já está sendo usado"));
    }
}

