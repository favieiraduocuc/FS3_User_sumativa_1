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
public class ListarUsuariosActivosStrategy implements UsuarioListStrategy {

    private final UsuarioRepository usuarioRepository;

    public ListarUsuariosActivosStrategy(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<UsuarioDTO> listar(UsuarioListFilter filtro) {
        List<Usuario> usuariosActivos = usuarioRepository.findByActivo("S");
        return usuariosActivos.stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }
}
