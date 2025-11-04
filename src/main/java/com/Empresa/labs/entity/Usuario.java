package com.Empresa.labs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USUARIO", schema = "LABS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    @NotBlank
    @Size(max = 80)
    @Column(name = "NOMBRE", nullable = false, length = 80)
    private String nombre;

    @NotBlank
    @Email
    @Size(max = 100)
    @Column(name = "EMAIL", nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank
    @Size(max = 120)
    @Column(name = "PASSWORD_HASH", nullable = false, length = 120)
    private String password;

    @Size(max = 20)
    @Column(name = "ROL", length = 20)
    private String rol;

    @Column(name = "ACTIVO", length = 1)
    private String activo = "S";
}
