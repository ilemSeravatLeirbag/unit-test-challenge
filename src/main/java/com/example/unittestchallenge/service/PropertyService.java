package com.example.unittestchallenge.service;

import com.example.unittestchallenge.dto.CreateDependencyDTO;
import com.example.unittestchallenge.dto.CreatePropertyDTO;
import com.example.unittestchallenge.dto.DependencyDTO;
import com.example.unittestchallenge.dto.PropertyDTO;
import com.example.unittestchallenge.exception.NeighborhoodNotFound;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PropertyService {

    private HashMap<String, Double> neighborhood = new HashMap<>();

    private DependencyDTO biggestDependecy;

    private double homeSize;

    private List<DependencyDTO> calculatedDependencies;

    public PropertyService() {
        homeSize = 0.0;
        neighborhood.put("Vila Mariana", 19.0);
        neighborhood.put("Vila Prudente", 12.0);
        neighborhood.put("Pinheiro", 27.5);
        neighborhood.put("Itaim Bibi", 23.0);
        calculatedDependencies = new ArrayList<>();
        biggestDependecy = new DependencyDTO("empty", 0.0);
    }

    public PropertyDTO calculate(CreatePropertyDTO dto) {
        List<CreateDependencyDTO> dependencies = dto.getDependencies();
        validAndGetNeighborhood(dto.getProp_district());
        double price = validAndGetNeighborhood(dto.getProp_district());

        this.analyseDependecies(dependencies);

        return PropertyDTO.builder().biggestRoom(biggestDependecy).cost(homeSize * price)
                .dependencies(calculatedDependencies).homeSize(homeSize).name(dto.getProp_name()).build();
    }

    private void analyseDependecies(List<CreateDependencyDTO> dependencies) {
        for(CreateDependencyDTO d : dependencies) {
            double dependencySize = d.getRoom_length() * d.getRoom_width();
            homeSize += dependencySize;
            DependencyDTO dp = new DependencyDTO(d.getRoom_name(),dependencySize);
            this.shouldAddBiggestDependecy(dp);
            calculatedDependencies.add(dp);
        }
    }


    private double validAndGetNeighborhood(String neighboorhood){
        Double price = neighborhood.get(neighboorhood);
        if (price == null) {
            throw new NeighborhoodNotFound(neighboorhood);
        }
        return price;
    }

    private void shouldAddBiggestDependecy(DependencyDTO d) {
        if (d.getSize() > biggestDependecy.getSize()) {
            biggestDependecy.setName(d.getName());
            biggestDependecy.setSize(d.getSize());
        }
    }

}
