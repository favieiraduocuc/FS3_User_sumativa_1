package com.Empresa.labs.controller;

import com.Empresa.labs.dto.*;
import com.Empresa.labs.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	private final UsuarioService service;

	public UsuarioController(UsuarioService s) {
		this.service = s;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioDTO crear(@Valid @RequestBody UsuarioCreateDTO dto) {
		return service.crear(dto);
	}
	
	@GetMapping
	public List<UsuarioDTO> listar() {
		return service.listar();
	}

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
