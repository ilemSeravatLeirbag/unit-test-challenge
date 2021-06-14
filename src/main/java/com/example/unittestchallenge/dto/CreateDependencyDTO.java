package com.example.unittestchallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
public class CreateDependencyDTO {

    @NotEmpty(message = "O campo não pode estar vazio.")
    @Pattern(regexp = "^[A-Z][a-zA-Z0-9]*$", message = "O nome do cômodo deve começar com uma letra maiúscula.")
    @Size(max = 30, message = "O comprimento do cômodo não pode exceder 30 caracteres.")
    private String room_name;

    @NotNull(message = "A largura do cômodo não pode estar vazia.")
    @DecimalMax(value = "25", message = "A largura máxima permitida por cômodo é de 25 metros.")
    private Double room_width;

    @NotNull(message = "O comprimento do cômodo não pode estar vazio.")
    @DecimalMax(value = "33", message = "O comprimento máximo permitido por cômodo é de 33 metros.")
    private Double room_length;




}
