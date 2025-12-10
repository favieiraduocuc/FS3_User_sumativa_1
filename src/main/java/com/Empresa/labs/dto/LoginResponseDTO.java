package com.Empresa.labs.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private Long   idUsuario;
    private String nombre;
    private String email;
    private String rol;
    private Boolean activo;
    private String telefono;
}
