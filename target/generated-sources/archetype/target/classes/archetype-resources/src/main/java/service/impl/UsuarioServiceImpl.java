#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

import ${package}.dto.*;
import ${package}.entity.Usuario;
import ${package}.exceptions.NotFoundException;
import ${package}.mapper.UsuarioMapper;
import ${package}.repository.UsuarioRepository;
import ${package}.service.UsuarioService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repo;
    private final PasswordEncoder passwordEncoder; 
    
    public UsuarioServiceImpl(UsuarioRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    // Crear usuario
    @Override
    public UsuarioDTO crear(UsuarioCreateDTO dto) {
        String email = dto.getEmail().trim().toLowerCase();
        log.info("Intentando crear nuevo usuario con email: {}", email);

        if (repo.existsByEmail(email)) {
            log.warn("Creación fallida: ya existe un usuario con el email {}", email);
            throw new IllegalArgumentException("Ya existe un usuario con el email: " + email);
        }

        Usuario e = UsuarioMapper.toEntity(dto);
        e.setEmail(email);

        //Hashear contraseña antes de guardar
        e.setPassword(passwordEncoder.encode(dto.getPassword()));

        Usuario guardado = repo.save(e);
        log.debug("Usuario creado exitosamente con ID: {} y email: {}", guardado.getIdUsuario(), guardado.getEmail());
        return UsuarioMapper.toDTO(guardado);
    }

    // Actualizar usuario
    @Override
    public UsuarioDTO actualizar(Long id, UsuarioUpdateDTO dto) {
        log.info("Actualizando usuario con ID: {}", id);
        Usuario e = repo.findById(id)
                .orElseThrow(() -> {
                    log.error("Error al actualizar: usuario con ID {} no encontrado", id);
                    return new NotFoundException("Usuario no encontrado");
                });

        // Si se envía una nueva contraseña
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            log.debug("Actualizando contraseña del usuario ID {}", id);
            e.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        UsuarioMapper.apply(dto, e);
        Usuario actualizado = repo.save(e);
        log.debug("Usuario actualizado correctamente: ID {} - email {}", actualizado.getIdUsuario(), actualizado.getEmail());
        return UsuarioMapper.toDTO(actualizado);
    }

    // Obtener usuario por ID
    @Transactional(readOnly = true)
    @Override
    public UsuarioDTO obtenerPorId(Long id) {
        log.info("Buscando usuario por ID: {}", id);
        return repo.findById(id)
                .map(u -> {
                    log.debug("Usuario encontrado: ID {} - email {}", u.getIdUsuario(), u.getEmail());
                    return UsuarioMapper.toDTO(u);
                })
                .orElseThrow(() -> {
                    log.warn("Usuario con ID {} no encontrado", id);
                    return new NotFoundException("Usuario no encontrado");
                });
    }

    // Listar todos los usuarios
    @Transactional(readOnly = true)
    @Override
    public List<UsuarioDTO> listar() {
        log.info("Listando todos los usuarios registrados");
        List<UsuarioDTO> lista = repo.findAll().stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
        log.debug("Cantidad total de usuarios encontrados: {}", lista.size());
        return lista;
    }

    // Eliminar (físico)
    @Override
    public void eliminar(Long id) {
        log.info("Eliminando usuario con ID: {}", id);
        if (!repo.existsById(id)) {
            log.error("Error al eliminar: usuario con ID {} no existe", id);
            throw new NotFoundException("Usuario no encontrado");
        }
        repo.deleteById(id);
        log.debug("Usuario eliminado exitosamente con ID {}", id);
    }
}
