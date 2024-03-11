package br.com.joaoaugusto.springbootapplication.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

// Dto serve como um schema de validações para o body de uma requisição

public class TransactionDto {

    @NotNull(message = "payer id ca not be null")
    private long payer_id;

    @NotNull(message = "payee id ca not be null")
    private long payee_id;

    @NotNull(message = "value can not be null")
    @DecimalMin(value = "0.01", message = "value must be higher than 0.01")
    private float value;

    // Getters e Setters -> Permite a leitura e edição de atributos privados

    public long getPayerId() {
        return payer_id;
    }

    public void setPayer(long payer_id) {
        this.payer_id = payer_id;
    }

    public long getPayeeId() {
        return payee_id;
    }

    public void setPayeeId(long payee_id) {
        this.payee_id = payee_id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
