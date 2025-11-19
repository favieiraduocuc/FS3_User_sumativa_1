package com.Empresa.labs.strategy.usuario;

import com.Empresa.labs.dto.UsuarioDTO;
import java.util.List;

public interface UsuarioListStrategy {

    List<UsuarioDTO> listar(UsuarioListFilter filtro);
}
