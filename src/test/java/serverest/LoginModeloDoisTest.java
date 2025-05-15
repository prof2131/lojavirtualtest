package serverest;

import dto.LoginDTO;
import dto.UsuarioReqDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.containsString;

public class LoginModeloDoisTest extends  BaseTest{

    static UsuarioReqDTO usuarioReqDTO;

    @BeforeAll
    static void gerarMassaDados(){
        usuarioReqDTO = new UsuarioReqDTO("true");
        doPost(usuarioReqDTO,"/usuarios",HttpStatus.SC_CREATED)
                        .body("message", containsString(MSG_CADASTRO_USER_OK));
    }

    @Test
    public void deveFazerLoginComSucesso() {
        LoginDTO loginDTO = new LoginDTO(usuarioReqDTO.getEmail(), usuarioReqDTO.getPassword());
        doPost(loginDTO,"/login", HttpStatus.SC_OK)
                .body("message", containsString(MSG_LOGIN_OK)).log().all();
    }
    @Test
    public void deveFalharLoginComSenhaInvalida() {
        LoginDTO loginDTO = new LoginDTO(usuarioReqDTO.getEmail(), "00020320");
        doPost(loginDTO, "/login", HttpStatus.SC_UNAUTHORIZED)
                .body("message", containsString(MSG_LOGIN_NOK)).log().all();
    }
}
