package dto;

import com.github.javafaker.Faker;

public class UsuarioReqDTO {

    private String nome;
    private String email;
    private String password;
    private String administrador;

    public UsuarioReqDTO(String administrador) {
        Faker faker = new Faker();
        this.nome = faker.name().name();
        this.email = faker.internet().emailAddress();
        this.password = faker.internet().password();
        this.administrador = administrador;
    }

    public UsuarioReqDTO(String nome, String email, String password, String administrador) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.administrador = administrador;
    }
    public UsuarioReqDTO() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }
}
