package br.com.senai.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.techdive.dto.AlunoDTO;
import org.techdive.model.Aluno;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.notNullValue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AlunosTeste {

    private ObjectMapper mapper = new ObjectMapper();

    private static Integer matricula;

    @BeforeAll
    public static void preCondicao(){
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/m03s01-exerc-1.0-SNAPSHOT/api/alunos";
    }

    @Test
    @Order(1)
    public void inserir() throws JsonProcessingException {
        AlunoDTO aluno = new AlunoDTO();
        aluno.setMatricula(1);
        aluno.setNome("Jorgin");
        String json = mapper.writeValueAsString(aluno);

        aluno = given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post()
                .then()
                .statusCode(201)
                .extract().as(AlunoDTO.class);
        matricula = aluno.getMatricula();
    }

    @Test
    @Order(2)
    public void obter() throws JsonProcessingException {

        AlunoDTO aluno = given()
                .contentType(ContentType.JSON)
                .pathParam("matricula", matricula)
                .when()
                .get("/{matricula}")
                .then()
                .statusCode(200)
                .extract()
                .as(AlunoDTO.class);
    }

    @Test
    @Order(3)
    public void remover(){
        given().contentType(ContentType.JSON)
                .pathParam("matricula", matricula)
                .when()
                .delete("/{matricula}")
                .then()
                .statusCode(204);
    }

}
