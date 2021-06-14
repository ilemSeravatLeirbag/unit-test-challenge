package com.example.unittestchallenge.controller;

import com.example.unittestchallenge.dto.CreateDependencyDTO;
import com.example.unittestchallenge.dto.CreatePropertyDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;
import java.util.Set;


class PropertyControllerTest {

    private Validator validator;

    @BeforeEach
    public void configure() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void allEntryCorrect() {
        CreatePropertyDTO dto = CreatePropertyDTO.builder().prop_name("Casa").prop_district("Rua")
                .dependencies(Collections.singletonList(CreateDependencyDTO.builder().room_name("Quarto")
                        .room_width(20.0).room_length(20.0).build())).build();
        Set<ConstraintViolation<CreatePropertyDTO>> violations = validator.validate(dto);
        Assertions.assertEquals(0,violations.size());
    }

    @Test
    void nameIsNotCapitalized() {
        CreatePropertyDTO dto = CreatePropertyDTO.builder().prop_name("casa").prop_district("Rua")
                .dependencies(Collections.singletonList(CreateDependencyDTO.builder().room_name("quarto")
                        .room_width(20.0).room_length(20.0).build())).build();
        Set<ConstraintViolation<CreatePropertyDTO>> violations = validator.validate(dto);
        Assertions.assertEquals(2,violations.size());
    }

    @Test
    void nameExceedTheLimit() {
        CreatePropertyDTO dto = CreatePropertyDTO.builder().prop_name("ESSETESTEEHMUITOGRANDEPARAPASSARNOVALIDADORPASSARPARAGRANDEMUITTOEHTESTEESSE").prop_district("Rua")
                .dependencies(Collections.singletonList(CreateDependencyDTO.builder().room_name("ESSETESTEEHMUITOGRANDEPARAPASSARNOVALIDADORPASSARPARAGRANDEMUITTOEHTESTEESSE")
                        .room_width(20.0).room_length(20.0).build())).build();
        Set<ConstraintViolation<CreatePropertyDTO>> violations = validator.validate(dto);
        Assertions.assertEquals(2,violations.size());
    }

    @Test
    void roomExceedTheLimitSize(){
        CreatePropertyDTO dto = CreatePropertyDTO.builder().prop_name("Casa").prop_district("Rua")
                .dependencies(Collections.singletonList(CreateDependencyDTO.builder().room_name("Quarto")
                        .room_width(55.0).room_length(55.0).build())).build();
        Set<ConstraintViolation<CreatePropertyDTO>> violations = validator.validate(dto);
        Assertions.assertEquals(2,violations.size());

    }

}