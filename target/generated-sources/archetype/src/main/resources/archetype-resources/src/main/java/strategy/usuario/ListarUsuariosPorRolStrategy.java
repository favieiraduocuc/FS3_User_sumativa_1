#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.strategy.usuario;

import ${package}.dto.UsuarioDTO;
import ${package}.entity.Usuario;
import ${package}.mapper.UsuarioMapper;
import ${package}.repository.UsuarioRepository;
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
