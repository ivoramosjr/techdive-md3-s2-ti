package br.com.senai.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.techdive.dto.AlunoDTO;
import org.techdive.dto.CursoDTO;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.notNullValue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CursosTeste {

    private ObjectMapper mapper = new ObjectMapper();

    private static String codigo;

    @BeforeAll
    public static void preCondicao(){
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/m03s01-exerc-1.0-SNAPSHOT/api/cursos";
    }

    @Test
    @Order(1)
    public void inserir() throws JsonProcessingException {
        CursoDTO curso = new CursoDTO();
        curso.setCodigo("1");
        curso.setAssunto("Assunto");
        curso.setDuracaoEmDias(10);

        String json = mapper.writeValueAsString(curso);
        codigo = given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post()
                .then()
                .statusCode(201)
                .body("codigo", notNullValue())
                .extract()
                .path("codigo");
    }
}
