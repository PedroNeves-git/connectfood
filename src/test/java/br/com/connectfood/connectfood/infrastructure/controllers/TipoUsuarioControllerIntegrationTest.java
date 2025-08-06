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
public class TipoUsuarioControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeAll
    static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void devePermitirBuscarTodosTiposUsuarios() {
        given()
                .basePath("/tipos-usuarios")
                .port(port)
                .when()
                .get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", not(empty()));
    }

    @Test
    void devePermitirBuscarTipoUsuarioPorId() {
        given()
                .basePath("/tipos-usuarios/{id}")
                .port(port)
                .pathParam("id", 1)
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("nome", notNullValue());
    }

    @Test
    void devePermitirSalvarTipoUsuario() {
        var payload = """
            {
              "id": null,
              "nome": "Gerente"
            }
            """;

        given()
                .basePath("/tipos-usuarios")
                .port(port)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post()
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("nome", equalTo("Gerente"));
    }

    @Test
    void devePermitirAtualizarTipoUsuario() {
        var payload = """
            {
              "id": 1,
              "nome": "Administrador"
            }
            """;

        given()
                .basePath("/tipos-usuarios/{id}")
                .port(port)
                .contentType(ContentType.JSON)
                .pathParam("id", 1)
                .body(payload)
                .when()
                .put()
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("nome", equalTo("Administrador"));
    }

    @Test
    void devePermitirDeletarTipoUsuario() {
        given()
                .basePath("/tipos-usuarios/{id}")
                .port(port)
                .pathParam("id", 1)
                .when()
                .delete()
                .then()
                .statusCode(204);
    }
}
