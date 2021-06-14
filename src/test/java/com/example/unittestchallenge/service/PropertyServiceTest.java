package com.example.unittestchallenge.service;


import com.example.unittestchallenge.dto.CreateDependencyDTO;
import com.example.unittestchallenge.dto.CreatePropertyDTO;
import com.example.unittestchallenge.dto.DependencyDTO;
import com.example.unittestchallenge.dto.PropertyDTO;
import com.example.unittestchallenge.exception.NeighborhoodNotFound;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class PropertyServiceTest {

    private CreatePropertyDTO create;

    private PropertyDTO expectedDTO;

    private final PropertyService service = new PropertyService();

    @BeforeEach
    public void setup() {
        create = CreatePropertyDTO.builder().prop_name("Casa Amarela")
                .dependencies(Arrays.asList(
                        new CreateDependencyDTO("quarto", 3.0,2.0),
                        new CreateDependencyDTO("banheiro", 1.25,2.0),
                        new CreateDependencyDTO("sala",3.0,4.0)))
                .build();
        expectedDTO = PropertyDTO.builder().name("Casa Amarela").homeSize(20.5).biggestRoom(new DependencyDTO("sala", 12.0))
                .dependencies(Arrays.asList(
                        new DependencyDTO("quarto", 6.0),
                        new DependencyDTO("banheiro", 2.5),
                        new DependencyDTO("sala", 12.0)))
                .build();
    }

    @Test
    public void isHomeSizeCorrect() {
        create.setProp_district("Vila Mariana");
        PropertyDTO resultedDTO = this.service.calculate(create);
        Assertions.assertEquals(expectedDTO.getHomeSize(),resultedDTO.getHomeSize());
    }

    @Test
    public void isNeighborhoodCorrect() {
        create.setProp_district("Vila Mariana");
        Assertions.assertDoesNotThrow(() -> this.service.calculate(create));
    }

    @Test()
    public void isNeighborhoodIncorrect() {
        create.setProp_district("Vila Maria");
        Assertions.assertThrows(NeighborhoodNotFound.class, () -> this.service.calculate(create));
    }

    @Test
    public void isTheBiggestDependency() {
        create.setProp_district("Vila Mariana");
        PropertyDTO resultedDTO = this.service.calculate(create);
        Assertions.assertEquals(expectedDTO.getBiggestRoom().getSize(),resultedDTO.getBiggestRoom().getSize());
        Assertions.assertEquals(expectedDTO.getBiggestRoom().getName(),resultedDTO.getBiggestRoom().getName());
    }

    @Test
    public void isDepenciesSizeCorrect() {
        create.setProp_district("Vila Mariana");
        PropertyDTO resultedDTO = this.service.calculate(create);
        assertThat(resultedDTO.getDependencies(),is(expectedDTO.getDependencies()));
    }

}