package com.br.apifood.demo.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.apifood.demo.domain.model.Estado;

public interface EstadoService {
	
	Page<Estado> buscarTodos(Pageable pageable);
	
	List<Estado> buscarTodos();

	Estado criarEstado(Estado estado);
	
	Estado buscarEstadoPeloId(UUID id);
	
	void apagarEstado(UUID id);

	Estado atualizarEstado(UUID id, Estado estado);
}
