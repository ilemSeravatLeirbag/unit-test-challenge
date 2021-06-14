package com.example.unittestchallenge.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
public class CreatePropertyDTO {

    @NotEmpty(message = "O nome da propriedade não pode estar vazio.")
    @Pattern(regexp = "^[A-Z][a-zA-Z0-9]*$", message = "O nome da propriedade deve começar com uma letra maiúscula.")
    @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracteres.")
    private String prop_name;

    @NotEmpty(message = "O bairro não pode estar vazio.")
    @Size(max = 45, message = "O comprimento do bairro não pode exceder 45 caracteres.")
    private String prop_district;

    @Valid
    private List<CreateDependencyDTO> dependencies;

}
