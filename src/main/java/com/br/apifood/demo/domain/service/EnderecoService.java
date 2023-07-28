package com.br.apifood.demo.domain.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.apifood.demo.domain.model.Endereco;

public interface EnderecoService {

	Page<Endereco> buscarTodos(Pageable pageable);
	
	List<Endereco> buscarTodos();

	Endereco criarEndereco(Endereco endereco);
	
	Endereco buscarEnderecoPeloId(UUID id);
	
	Endereco atualizarEndereco(UUID id, Endereco endereco);
	
	Endereco atualizarParcialEndereco(UUID id, Map<String, Object> campos);
}
