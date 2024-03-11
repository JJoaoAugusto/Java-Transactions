package br.com.joaoaugusto.springbootapplication.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

// Dto serve como um schema de validações para o body de uma requisição

public class DeposityDto {
    
    @NotNull(message="value can not be null")
    @DecimalMin(value="0.01", message="value must be higher than 0.01")
    private float value;

    // Getters e Setters -> Permite a leitura e edição de atributos privados

    public float getValue() {
        return value;
    }

    public void setValue(float value){
        this.value = value;
    }
}
