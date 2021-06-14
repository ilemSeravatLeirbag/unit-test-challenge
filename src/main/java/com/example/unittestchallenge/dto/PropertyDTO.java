package com.example.unittestchallenge.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class PropertyDTO implements Serializable {

    private String name;

    private Double homeSize;

    private Double cost;

    private DependencyDTO biggestRoom;

    private List<DependencyDTO> dependencies;
}
