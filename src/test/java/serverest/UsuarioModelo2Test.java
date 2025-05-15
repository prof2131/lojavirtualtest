package serverest;

import dto.UsuarioReqDTO;
import dto.UsuarioRespDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UsuarioModelo2Test extends BaseTest {

    static UsuarioReqDTO usuarioReqDTO;
    static String idUsuarioCadastrado;

    @BeforeAll
    static void gerarMassaDados(){
        usuarioReqDTO = new UsuarioReqDTO("true");
        idUsuarioCadastrado =
                doPost(usuarioReqDTO, "/usuarios", HttpStatus.SC_CREATED)
                        .body("message", containsString(MSG_CADASTRO_USER_OK))
                        .extract().jsonPath().get("_id");
    }

    @Test
    public void deveListarUsuarios() {
        doGet("/usuarios", HttpStatus.SC_OK)
                .body("usuarios", not(empty()));
    }

    @Test
    public void deveCadastrarNovoUsuario() {
        UsuarioReqDTO usuarioReqDTO = new UsuarioReqDTO("true");
        doPost(usuarioReqDTO,"/usuarios",HttpStatus.SC_CREATED)
                .body("message", containsString(MSG_CADASTRO_USER_OK));
    }
    @Test
    public void deveListarUsuarioPorId() {
        String path = "/usuarios/"+idUsuarioCadastrado;
        UsuarioRespDTO usuarioResponse = doGet( path, HttpStatus.SC_OK)
                .extract().as(UsuarioRespDTO.class);

        Assertions.assertEquals(usuarioReqDTO.getNome(),usuarioResponse.getNome());
    }
    @Test
    public void naoDeveCadastrarUsuarioComEmailDuplicado() {
        doPost(usuarioReqDTO, "/usuarios", HttpStatus.SC_BAD_REQUEST)
                .body("message", containsString(MSG_EMAIL_DUPLICADO));
    }

}

