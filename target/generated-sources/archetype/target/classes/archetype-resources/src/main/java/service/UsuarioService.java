#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.dto.*;
import java.util.List;

public interface UsuarioService {
	UsuarioDTO crear(UsuarioCreateDTO dto);

	UsuarioDTO actualizar(Long id, UsuarioUpdateDTO dto);

	UsuarioDTO obtenerPorId(Long id);

	List<UsuarioDTO> listar();

	void eliminar(Long id);
}
