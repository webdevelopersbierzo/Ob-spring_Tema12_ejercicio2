package com.example.Ejercicio2Sesion101112.controller;

import com.example.Ejercicio2Sesion101112.entity.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @LocalServerPort
    private int port;
    @BeforeEach
    void setUp(){
        restTemplateBuilder =restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }
    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/laptops",Laptop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());

        List<Laptop> laptops = Arrays.asList(response.getBody());
        System.out.println(laptops.size());
    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json= """
                {
                "company": "PruebaTest",
                "model": "test",
                "memory": 19,
                "operatingSistem": "testOs"            
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json,headers);

        ResponseEntity<Laptop> response = testRestTemplate.exchange("/laptops", HttpMethod.POST, request,Laptop.class);
        Laptop result = response.getBody();
        // Todo modificar el id para comprobar
        assertEquals(12L,result.getId());
        assertEquals("PruebaTest", result.getCompany());
    }

    @Test
    void findOnebyId() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/laptops/8",Laptop.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    void update() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                "id":2,
                "company": "update",
                "model": "test",
                "memory": 19,
                "operatingSistem": "testOs"            
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);


        ResponseEntity<Laptop> response = testRestTemplate.exchange("/laptops", HttpMethod.PUT, request, Laptop.class);
        Laptop result = response.getBody();

        assertEquals(2L, result.getId());
        assertEquals("update",result.getCompany());
    }
    @Test
    void deleteById() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/laptop/8",Laptop.class);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());


    }

    @Test
    void deleteAll() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/laptop", Laptop.class);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }
}