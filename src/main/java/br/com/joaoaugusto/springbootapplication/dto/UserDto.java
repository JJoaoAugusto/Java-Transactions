package br.com.joaoaugusto.springbootapplication.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// Dto serve como um schema de validações para o body de uma requisição

public class UserDto {

    @NotEmpty(message = "name can not be empty")
    @Size(max = 92, message = "name must be lower than 32 characters long")
    private String name;

    @NotEmpty(message = "cpf can not be empty")
    @Size(min = 11, max = 11, message = "cpf must be equal than 11 characters long")
    private String cpf;

    @NotEmpty(message="email can not be empty")
    @Size(max = 62, message = "email must be lower than 62 characteres")
    private String email;

    @NotEmpty(message = "password can not be empty")
    @NotNull(message="password can not be null")
    private String password;

    @NotEmpty(message = "type can not be empty")
    @Pattern(regexp = "(COMMON|SELLER)", message = "user type must be COMMON or SELLER")
    private String type;

    // Getters e Setters -> Permite a leitura e edição de atributos privados
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }


}
