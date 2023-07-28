package com.br.apifood.demo.api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.apifood.demo.domain.model.Estado;
import com.br.apifood.demo.domain.service.EstadoService;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

	@Autowired
	private EstadoService service;
	
	@GetMapping
	public Page<Estado> buscarTodos(Pageable pageable){
		return service.buscarTodos(pageable);
	}
	
	@PostMapping
	public Estado criarEstado(@RequestBody Estado estado) {
		return service.criarEstado(estado);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarEstado(@PathVariable("id") UUID id) {
		service.apagarEstado(id);
	}
	
	@PutMapping("/{id}")
	public Estado atualizarEstado(@PathVariable("id") UUID id, @RequestBody Estado estado) {
		return service.atualizarEstado(id, estado);
	}
}
