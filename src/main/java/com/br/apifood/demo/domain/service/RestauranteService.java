package com.br.apifood.demo.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.apifood.demo.domain.model.Endereco;
import com.br.apifood.demo.domain.model.Restaurante;

public interface RestauranteService {
	
	Restaurante criarRestaurante(Restaurante restaurante);
	
	List<Restaurante> buscarTodos();
	
	Page<Restaurante> buscarTodos(Pageable pageable);
	
	Restaurante buscarRestaurantePeloId(UUID id);
	
	Restaurante buscarRestaurantePeloNome(String nome);
	
	Restaurante buscarRestaurantePeloEndereco(Endereco endereco);
	
	void apagarRestaurante(Restaurante restaurante);

	void deletarTodos();

}
