#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.strategy.usuario;

import ${package}.dto.UsuarioDTO;
import java.util.List;

public interface UsuarioListStrategy {

    List<UsuarioDTO> listar(UsuarioListFilter filtro);
}
