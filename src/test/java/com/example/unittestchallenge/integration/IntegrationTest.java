package com.example.unittestchallenge.integration;

import com.example.unittestchallenge.dto.CreateDependencyDTO;
import com.example.unittestchallenge.dto.CreatePropertyDTO;
import com.example.unittestchallenge.dto.PropertyDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private CreatePropertyDTO createPropertyDTO;

    @BeforeEach()
    public void setup(){
        createPropertyDTO = CreatePropertyDTO.builder().prop_name("Casa").prop_district("Vila Mariana")
                .dependencies(Arrays.asList(
                        CreateDependencyDTO.builder().room_name("Quarto").room_length(3.0).room_width(3.0).build(),
                        CreateDependencyDTO.builder().room_name("Banheiro").room_length(2.0).room_width(2.0)
                                .build())).build();
    }

    @Test
    public void isHomeAnalyzedSuccesfully() {
        ResponseEntity<PropertyDTO> propertyResponse = restTemplate.postForEntity("/calculator", createPropertyDTO, PropertyDTO.class);
        PropertyDTO property = propertyResponse.getBody();
        Assertions.assertNotNull(property);
        Assertions.assertEquals(13.0, property.getHomeSize());
        Assertions.assertEquals(247.0,property.getCost());
        Assertions.assertEquals(9.0,property.getBiggestRoom().getSize());
        Assertions.assertEquals(HttpStatus.CREATED,propertyResponse.getStatusCode());
    }

    @Test
    public void isAInvalidDTO() {
        createPropertyDTO.setProp_name("casa");
        ResponseEntity<PropertyDTO> propertyResponse = restTemplate.postForEntity("/calculator", createPropertyDTO, PropertyDTO.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,propertyResponse.getStatusCode());
        Assertions.assertNotNull(propertyResponse);
    }

}
