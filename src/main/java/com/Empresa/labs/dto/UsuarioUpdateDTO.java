package com.Empresa.labs.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UsuarioUpdateDTO {

    @Size(max = 80)
    private String nombre;

    @Email
    @Size(max = 100)
    private String email;

    @Size(max = 120)
    private String password;

    @Size(max = 20)
    private String rol;

    @Pattern(regexp = "[SN]", message = "El campo activo debe ser 'S' o 'N'")
    private String activo;
    
    @Size(max = 15)
    private String telefono;
}
