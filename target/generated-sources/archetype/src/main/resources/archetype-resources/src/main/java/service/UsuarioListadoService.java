#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.dto.UsuarioDTO;
import ${package}.strategy.usuario.*;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioListadoService {

    private final Map<TipoListadoUsuario, UsuarioListStrategy> strategies =
            new EnumMap<>(TipoListadoUsuario.class);

    public UsuarioListadoService(
            ListarTodosUsuariosStrategy listarTodosUsuariosStrategy,
            ListarUsuariosActivosStrategy listarUsuariosActivosStrategy,
            ListarUsuariosPorRolStrategy listarUsuariosPorRolStrategy // si no usas POR_ROL, bórralo
    ) {
        strategies.put(TipoListadoUsuario.TODOS, listarTodosUsuariosStrategy);
        strategies.put(TipoListadoUsuario.ACTIVOS, listarUsuariosActivosStrategy);
        strategies.put(TipoListadoUsuario.POR_ROL, listarUsuariosPorRolStrategy);
    }

    public List<UsuarioDTO> listar(TipoListadoUsuario tipo, UsuarioListFilter filtro) {
        UsuarioListStrategy strategy = strategies.get(tipo);

        if (strategy == null) {
            throw new IllegalArgumentException("Tipo de listado no soportado: " + tipo);
        }

        return strategy.listar(filtro);
    }
}
