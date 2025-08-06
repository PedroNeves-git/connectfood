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
public class RestauranteControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeAll
    static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void devePermitirBuscarRestaurantePorId() {
        given()
                .basePath("/restaurantes/{id}")
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
    void devePermitirListarTodosRestaurantes() {
        given()
                .basePath("/restaurantes")
                .port(port)
                .when()
                .get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", not(empty())); // espera que tenha pelo menos 1 restaurante
    }

    @Test
    void devePermitirSalvarRestauranteComSucesso() {
        var payload = """
        {
          "nome": "Restaurante XPTO",
          "endereco": "Rua Nova, 100",
          "tipoCozinha": "Italiana",
          "horarioFuncionamento": "09:00-21:00",
          "idDono": 1
        }
        """;

        given()
                .basePath("/restaurantes")
                .port(port)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post()
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("nome", equalTo("Restaurante XPTO"))
                .body("idDono", equalTo(1));
    }

    @Test
    void devePermitirAtualizarRestauranteComSucesso() {
        var payload = """
        {
          "id": 1,
          "nome": "Restaurante Atualizado",
          "endereco": "Av. Nova, 321",
          "tipoCozinha": "Japonesa",
          "horarioFuncionamento": "10:00-22:00",
          "dono": {
            "id": 1
          }
        }
        """;

        given()
                .basePath("/restaurantes/{id}")
                .port(port)
                .contentType(ContentType.JSON)
                .pathParam("id", 1)
                .body(payload)
                .when()
                .put()
                .then()
                .statusCode(200)
                .body("nome", equalTo("Restaurante Atualizado"));
    }

    @Test
    void devePermitirDeletarRestauranteComSucesso() {
        given()
                .basePath("/restaurantes/{id}")
                .port(port)
                .pathParam("id", 1)
                .when()
                .delete()
                .then()
                .statusCode(204);
    }


}
