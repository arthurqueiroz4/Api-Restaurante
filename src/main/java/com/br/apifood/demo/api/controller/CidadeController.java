package com.br.apifood.demo.api.controller;



import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.apifood.demo.domain.model.Cidade;
import com.br.apifood.demo.domain.service.CidadeService;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping
	public Page<Cidade> buscarTodos(Pageable pageable){
		return cidadeService.buscarTodos(pageable);
	}
	
	@PostMapping
	public Cidade criarCidade(@RequestBody Cidade cidade) {
		return cidadeService.criarCidade(cidade);
	}
	
	@DeleteMapping("/{id}")
	public void apagarCidade(@PathVariable UUID id) {
		cidadeService.apagarCidade(id);
	}
}
