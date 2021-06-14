package com.example.unittestchallenge.controller;

import com.example.unittestchallenge.dto.CreatePropertyDTO;
import com.example.unittestchallenge.dto.PropertyDTO;
import com.example.unittestchallenge.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequiredArgsConstructor
@RequestMapping(path = "/calculator", produces = "application/json")
public class PropertyController {

    private final PropertyService service;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PropertyDTO calculate(@RequestBody @Valid CreatePropertyDTO dto){
        return this.service.calculate(dto);
    }


}
