package com.br.apifood.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.apifood.demo.domain.model.Restaurante;
import com.br.apifood.demo.domain.service.RestauranteService;

@RestController
@RequestMapping("api/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante criarRestaurante(@RequestBody Restaurante restaurante) {
		return service.criarRestaurante(restaurante);
	}
	
	@GetMapping
	public Page<Restaurante> buscarTodos(Pageable pageable){
		return service.buscarTodos(pageable);
	}
	
	@DeleteMapping("/deletarTodos")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarTodos() {
		service.deletarTodos();
	}
	

	
}
