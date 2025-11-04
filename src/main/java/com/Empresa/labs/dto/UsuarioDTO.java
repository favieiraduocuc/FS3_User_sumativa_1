package com.Empresa.labs.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

    private Long idUsuario;
    private String nombre;
    private String email;
    private String rol;
    private String activo; // "S" o "N"
}
