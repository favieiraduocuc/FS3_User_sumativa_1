#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

    private Long idUsuario;
    private String nombre;
    private String email;
    private String rol;
    private String activo; // "S" o "N"
}
