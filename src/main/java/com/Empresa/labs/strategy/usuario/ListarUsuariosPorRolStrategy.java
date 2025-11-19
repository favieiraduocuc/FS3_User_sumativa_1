package com.Empresa.labs.strategy.usuario;

import com.Empresa.labs.dto.UsuarioDTO;
import com.Empresa.labs.entity.Usuario;
import com.Empresa.labs.mapper.UsuarioMapper;
import com.Empresa.labs.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListarUsuariosPorRolStrategy implements UsuarioListStrategy {

    private final UsuarioRepository usuarioRepository;

    public ListarUsuariosPorRolStrategy(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<UsuarioDTO> listar(UsuarioListFilter filtro) {
        if (filtro.getRol() == null || filtro.getRol().isBlank()) {
            throw new IllegalArgumentException("El rol es obligatorio para listar POR_ROL");
        }

        List<Usuario> usuarios = usuarioRepository.findByRolIgnoreCase(filtro.getRol());
        return usuarios.stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }
}
