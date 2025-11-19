package com.Empresa.labs.controller;

import com.Empresa.labs.dto.*;
import com.Empresa.labs.service.UsuarioService;
import com.Empresa.labs.service.UsuarioListadoService;
import com.Empresa.labs.strategy.usuario.TipoListadoUsuario;
import com.Empresa.labs.strategy.usuario.UsuarioListFilter;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioListadoService listadoService;

    public UsuarioController(UsuarioService service,
                             UsuarioListadoService listadoService) {
        this.service = service;
        this.listadoService = listadoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO crear(@Valid @RequestBody UsuarioCreateDTO dto) {
        return service.crear(dto);
    }

    // 🔥 NUEVA IMPLEMENTACIÓN CON PATRÓN STRATEGY
    @GetMapping
    public List<UsuarioDTO> listar(
            @RequestParam(name = "tipo", required = false, defaultValue = "TODOS")
            TipoListadoUsuario tipo,
            @RequestParam(name = "rol", required = false)
            String rol
    ) {
        UsuarioListFilter filtro = new UsuarioListFilter();
        filtro.setRol(rol);

        return listadoService.listar(tipo, filtro);
    }

    /* 
    ⛔ IMPLEMENTACIÓN ANTERIOR — SE MANTIENE COMENTADA PARA LA EVALUACIÓN
    @GetMapping
    public List<UsuarioDTO> listar() {
        return service.listar();
    }
    */

    @GetMapping("/{id}")
    public UsuarioDTO obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public UsuarioDTO actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioUpdateDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
