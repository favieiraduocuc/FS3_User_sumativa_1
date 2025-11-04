package com.Empresa.labs.security;

import com.Empresa.labs.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Configuration
public class CustomUserDetailsConfig {

    @Bean
    public UserDetailsService userDetailsService(UsuarioRepository repo) {
        return username -> repo.findByEmail(username.trim().toLowerCase())
            .map(u -> User.withUsername(u.getEmail())
                // IMPORTANT: aquí va el HASH guardado en PASSWORD_HASH
                .password(u.getPassword())  // o u.getPasswordHash() si ese es tu getter
                // si tu columna ROL guarda "ADMIN"/"USER"/"PACIENTE" sin prefijo:
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_" + u.getRol())))
                .build())
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
