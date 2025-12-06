package com.Empresa.labs.mapper;

import com.Empresa.labs.dto.*;
import com.Empresa.labs.entity.Usuario;

public class UsuarioMapper {

    private UsuarioMapper() {}

    // ----------------------
    //      ENTITY → DTO
    // ----------------------
    public static UsuarioDTO toDTO(Usuario e) {
        if (e == null)
            return null;

        UsuarioDTO d = new UsuarioDTO();
        d.setIdUsuario(e.getIdUsuario());
        d.setNombre(e.getNombre());
        d.setEmail(e.getEmail());
        d.setRol(e.getRol());
        d.setActivo(e.getActivo());
        d.setTelefono(e.getTelefono());   // 👈 TELEFONO AGREGADO
        return d;
    }

    // ----------------------
    //    CREATE DTO → ENTITY
    // ----------------------
    public static Usuario toEntity(UsuarioCreateDTO c) {
        if (c == null)
            return null;

        Usuario e = new Usuario();
        e.setNombre(c.getNombre());
        e.setEmail(c.getEmail());
        e.setPassword(c.getPassword());
        e.setRol(c.getRol());
        e.setActivo(c.getActivo());
        e.setTelefono(c.getTelefono());   // 👈 TELEFONO AGREGADO
        return e;
    }

    // ----------------------
    //   UPDATE DTO → ENTITY
    // ----------------------
    public static void apply(UsuarioUpdateDTO u, Usuario e) {
        if (u == null || e == null)
            return;

        if (u.getNombre() != null)
            e.setNombre(u.getNombre());

        if (u.getEmail() != null)
            e.setEmail(u.getEmail());

        if (u.getPassword() != null)
            e.setPassword(u.getPassword());

        if (u.getRol() != null)
            e.setRol(u.getRol());

        if (u.getActivo() != null)
            e.setActivo(u.getActivo());

        if (u.getTelefono() != null)
            e.setTelefono(u.getTelefono());   // 👈 TELEFONO AGREGADO
    }
}
