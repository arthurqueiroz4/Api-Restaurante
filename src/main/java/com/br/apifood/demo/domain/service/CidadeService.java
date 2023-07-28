package com.br.apifood.demo.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.apifood.demo.domain.model.Cidade;

public interface CidadeService {

	Cidade criarCidade(Cidade cidade);
	
	List<Cidade> buscarTodos();
	
	Page<Cidade> buscarTodos(Pageable pageable);
	
	Cidade buscarCidadePeloId(UUID id);
	
	Cidade buscarCidadePeloNome(String nome);
	
	void apagarCidade(UUID id);
}
