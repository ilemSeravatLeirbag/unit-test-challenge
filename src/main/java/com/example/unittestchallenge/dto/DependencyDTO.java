package com.example.unittestchallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class DependencyDTO implements Serializable {

    private String name;

    private Double size;

}
