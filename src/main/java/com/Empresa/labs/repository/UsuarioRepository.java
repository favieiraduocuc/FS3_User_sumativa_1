package com.Empresa.labs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Empresa.labs.entity.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByEmailIgnoreCase(String email);
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}
