package com.br.apifood.demo.api.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.apifood.demo.api.dto.CozinhaPutDTO;
import com.br.apifood.demo.domain.model.Cozinha;
import com.br.apifood.demo.domain.service.CozinhaService;

@RestController
@RequestMapping("/api/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaService service;
	
	@GetMapping
	public Page<Cozinha> buscarTodos(Pageable pageable){
		return service.buscarTodos(pageable);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha criarCozinha(@RequestBody Cozinha cozinha) {
		return service.criarCozinha(cozinha);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarCozinha(@PathVariable("id") UUID id) {
		service.apagarCozinha(id);
	}
	
	@PutMapping("/{id}")
	public Cozinha atualizarCozinha(@PathVariable("id") UUID id, @RequestBody CozinhaPutDTO cozinhaputDTO) {
		return service.atualizarCozinha(id, cozinhaputDTO);
	}
	
	@PatchMapping("/{id}")
	public Cozinha atualizarParcialCozinha(@PathVariable("id") UUID id, @RequestBody Map<String, Object> campos) {
		return service.atualizarParcialCozinha(id, campos);
	}
}
