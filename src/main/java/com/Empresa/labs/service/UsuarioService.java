package com.Empresa.labs.service;

import com.Empresa.labs.dto.*;
import java.util.List;

public interface UsuarioService {
	UsuarioDTO crear(UsuarioCreateDTO dto);

	UsuarioDTO actualizar(Long id, UsuarioUpdateDTO dto);

	UsuarioDTO obtenerPorId(Long id);

	List<UsuarioDTO> listar();

	void eliminar(Long id);
	
	UsuarioDTO desactivar(Long id);
	
	UsuarioDTO activar(Long id);
}
