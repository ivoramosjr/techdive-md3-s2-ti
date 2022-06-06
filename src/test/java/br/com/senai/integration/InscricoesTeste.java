package br.com.senai.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.techdive.dto.InscricaoReqDTO;
import org.techdive.dto.InscricaoRespDTO;

import static io.restassured.RestAssured.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InscricoesTeste {

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    public static void preCondicao(){
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/m03s01-exerc-1.0-SNAPSHOT/api/inscricoes";
    }

    @Test
    @Order(1)
    public void inserir() throws JsonProcessingException {
        InscricaoReqDTO inscricao = new InscricaoReqDTO(1,"1");

        String json = mapper.writeValueAsString(inscricao);

        InscricaoRespDTO result =
                given()
                .contentType(ContentType.JSON)
                        .body(json)
                        .when()
                        .post()
                        .then()
                        .statusCode(201)
                        .extract()
                        .as(InscricaoRespDTO.class);
    }
}
