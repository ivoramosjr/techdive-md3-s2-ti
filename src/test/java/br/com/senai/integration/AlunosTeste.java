package br.com.senai.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.techdive.dto.AlunoDTO;

import static io.restassured.RestAssured.*;

public class AlunosTeste {

    private ObjectMapper mapper = new ObjectMapper();

    private static AlunoDTO aluno = null;

    @BeforeAll
    public static void preCondicao(){
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/m03s01-exerc-1.0-SNAPSHOT/api";
    }

    @Test
    public void inserir() throws JsonProcessingException {
        aluno = new AlunoDTO();
        aluno.setMatricula(1);
        aluno.setNome("Jorgin");
        String json = mapper.writeValueAsString(aluno);

        aluno = given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/alunos")
                .then()
                .statusCode(201)
                .extract().as(AlunoDTO.class);
    }

}
