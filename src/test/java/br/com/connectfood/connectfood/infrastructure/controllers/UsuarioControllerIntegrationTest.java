package br.com.connectfood.connectfood.infrastructure.controllers;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/schema.sql", "/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class UsuarioControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeAll
    static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void devePermitirBuscarUsuarioPorId() {
        given()
                .basePath("/usuarios/{id}")
                .port(port)
                .pathParam("id", 1)
                .when()
                .get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(1))
                .body("nome", notNullValue());
    }

    @Test
    void devePermitirSalvarUsuarioComSucesso() {
        var payload = """
            {
              "nome": "Pedro Henrique",
              "email": "pedro@gmail.com",
              "senha": "senha1234",
              "endereco": "Rua X, 123",
              "login": "pedro1234",
              "tipoUsuarioId": 1
            }
            """;

        given()
                .basePath("/usuarios")
                .port(port)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post()
                .then()
                .statusCode(201);
    }

    @Test
    void devePermitirAtualizarUsuarioComSucesso() {
        var payload = """
            {
              "nome": "Pedro Atualizado",
              "email": "pedroatualizado@gmail.com",
              "senha": "novaSenha123",
              "endereco": "Rua Y, 456",
              "login": "pedroAtualizado",
              "tipoUsuario": {
                  "id": 1
              }
            }
            """;

        given()
                .basePath("/usuarios/{id}")
                .port(port)
                .contentType(ContentType.JSON)
                .pathParam("id", 1)
                .body(payload)
                .when()
                .put()
                .then()
                .statusCode(204);
    }

    @Test
    void devePermitirDeletarUsuarioComSucesso() {
        given()
                .basePath("/usuarios/{id}")
                .port(port)
                .pathParam("id", 1)
                .when()
                .delete()
                .then()
                .statusCode(200);
    }

    @Test
    void devePermitirTrocarSenhaComSucesso() {
        var payload = """
            {
              "senhaAntiga": "senha123",
              "novaSenha": "novaSenha321"
            }
            """;

        given()
                .basePath("/usuarios/{id}/senha")
                .port(port)
                .contentType(ContentType.JSON)
                .pathParam("id", 1)
                .body(payload)
                .when()
                .put()
                .then()
                .statusCode(204);
    }

    @Test
    void devePermitirRealizarLoginComSucesso() {
        var payload = """
            {
              "login": "pedro123",
              "senha": "senha123"
            }
            """;

        given()
                .basePath("/usuarios/login")
                .port(port)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue());
    }

}

