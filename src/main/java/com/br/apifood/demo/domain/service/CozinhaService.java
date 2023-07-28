package com.br.apifood.demo.domain.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.apifood.demo.api.dto.CozinhaPutDTO;
import com.br.apifood.demo.domain.model.Cozinha;


public interface CozinhaService {

	Page<Cozinha> buscarTodos(Pageable pageable);
	
	List<Cozinha> buscarTodos();
	
	Cozinha criarCozinha(Cozinha cozinha);
	
	void apagarCozinha(UUID cozinhaId);
	
	Cozinha buscarCozinhaPeloId(UUID cozinhaId);
	
	Cozinha atualizarCozinha(UUID cozinhaId, CozinhaPutDTO cozinhaputDTO);

	Cozinha atualizarParcialCozinha(UUID id, Map<String, Object> campos);

}
