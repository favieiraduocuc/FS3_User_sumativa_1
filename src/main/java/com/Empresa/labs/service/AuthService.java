package com.Empresa.labs.service;

import com.Empresa.labs.dto.LoginRequestDTO;
import com.Empresa.labs.dto.LoginResponseDTO;
import com.Empresa.labs.entity.Usuario;
import com.Empresa.labs.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginResponseDTO login(LoginRequestDTO request) {

        // 1) Buscar usuario por email (ignorando may/min)
        Usuario usuario = usuarioRepository
                .findByEmailIgnoreCase(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario o contraseña inválidos"));

        // 2) Verificar que esté activo (ACTIVO = 'S')
        if (!"S".equalsIgnoreCase(usuario.getActivo())) {
            throw new RuntimeException("Usuario inactivo");
        }

        // 3) Comparar contraseña plana con el hash de la BD (PASSWORD_HASH)
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Usuario o contraseña inválidos");
        }

        // 4) Armar respuesta segura
        LoginResponseDTO resp = new LoginResponseDTO();
        resp.setIdUsuario(usuario.getIdUsuario());
        resp.setNombre(usuario.getNombre());
        resp.setEmail(usuario.getEmail());
        resp.setRol(usuario.getRol());
        resp.setActivo(true);
        resp.setTelefono(usuario.getTelefono());

        return resp;
    }
}
