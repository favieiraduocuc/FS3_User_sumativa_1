package com.Empresa.labs.strategy.usuario;

import com.Empresa.labs.dto.UsuarioDTO;
import com.Empresa.labs.entity.Usuario;
import com.Empresa.labs.mapper.UsuarioMapper;
import com.Empresa.labs.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListarTodosUsuariosStrategy implements UsuarioListStrategy {

    private final UsuarioRepository usuarioRepository;

    public ListarTodosUsuariosStrategy(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<UsuarioDTO> listar(UsuarioListFilter filtro) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }
}
