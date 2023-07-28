package com.br.apifood.demo.api.controller;

import java.util.Map;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.apifood.demo.domain.model.Endereco;
import com.br.apifood.demo.domain.service.EnderecoService;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoService service;
	
	@GetMapping
	public Page<Endereco> buscarTodos(Pageable pageable){
		return service.buscarTodos(pageable);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Endereco criarEndereco(@RequestBody Endereco endereco) {
		return service.criarEndereco(endereco);
	}
	
	@PutMapping("/{id}")
	public Endereco atualizarEndereco(@PathVariable UUID id, @RequestBody Endereco endereco) {
		return service.atualizarEndereco(id, endereco);
	}
	
	@PatchMapping("/{id}")
	public Endereco atualizarParcilEndereco(@PathVariable UUID id, 
			@RequestBody Map<String, Object> campos) {
		return service.atualizarParcialEndereco(id, campos);
	}
	

}
