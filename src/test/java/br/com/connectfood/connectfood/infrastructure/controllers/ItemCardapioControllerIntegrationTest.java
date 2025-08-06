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
public class ItemCardapioControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeAll
    static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void devePermitirBuscarTodosItensCardapio() {
        given()
                .basePath("/itens-cardapio")
                .port(port)
                .when()
                .get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", not(empty()));
    }

    @Test
    void devePermitirBuscarItemCardapioPorId() {
        given()
                .basePath("/itens-cardapio/{id}")
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
    void devePermitirSalvarItemCardapio() {
        var payload = """
            {
              "nome": "Pizza Margherita",
              "descricao": "Pizza clássica com molho de tomate e muçarela",
              "preco": 45.00,
              "somenteLocal": false,
              "caminhoFoto": "/imagens/pizza-margherita.jpg",
              "restauranteId": 1
            }
            """;

        given()
                .basePath("/itens-cardapio")
                .port(port)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post()
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("nome", equalTo("Pizza Margherita"));
    }

    @Test
    void devePermitirAtualizarItemCardapio() {
        var payload = """
            {
              "id": 1,
              "nome": "Pizza Calabresa",
              "descricao": "Pizza com calabresa fatiada e cebola",
              "preco": 48.00,
              "somenteLocal": false,
              "caminhoFoto": "/imagens/pizza-calabresa.jpg",
              "restauranteId": 1
            }
            """;

        given()
                .basePath("/itens-cardapio/{id}")
                .port(port)
                .contentType(ContentType.JSON)
                .pathParam("id", 1)
                .body(payload)
                .when()
                .put()
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("nome", equalTo("Pizza Calabresa"));
    }

    @Test
    void devePermitirDeletarItemCardapio() {
        given()
                .basePath("/itens-cardapio/{id}")
                .port(port)
                .pathParam("id", 1)
                .when()
                .delete()
                .then()
                .statusCode(204);
    }
}
