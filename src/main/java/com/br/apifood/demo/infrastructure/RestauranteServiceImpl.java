package com.br.apifood.demo.infrastructure;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.apifood.demo.domain.exception.RegraDeNegocio;
import com.br.apifood.demo.domain.model.Cozinha;
import com.br.apifood.demo.domain.model.Endereco;
import com.br.apifood.demo.domain.model.Restaurante;
import com.br.apifood.demo.domain.repository.RestauranteRepository;
import com.br.apifood.demo.domain.service.CozinhaService;
import com.br.apifood.demo.domain.service.EnderecoService;
import com.br.apifood.demo.domain.service.RestauranteService;

@Service
public class RestauranteServiceImpl implements RestauranteService{
	@Autowired
	private RestauranteRepository repository;
	@Autowired
	private EnderecoService enderecoService;
	@Autowired
	private CozinhaService cozinhaService;

	@Override
	public Restaurante criarRestaurante(Restaurante restaurante) {
		Restaurante restauranteBuscado = buscarRestaurantePeloNome(restaurante.getNome());
		
		if (Objects.nonNull(restauranteBuscado))
			throw new RegraDeNegocio("Já possui restaurante com esse nome.");
		
		verificaEnderecoECozinha(restaurante.getEndereco(), restaurante.getCozinha());
		
		var enderecoId= restaurante.getEndereco().getId();
		var cozinhaId= restaurante.getCozinha().getId();
		
		var enderecoRestaurante = enderecoService.buscarEnderecoPeloId(enderecoId);
		var cozinhaRestaurante = cozinhaService.buscarCozinhaPeloId(cozinhaId);
		
		restaurante.setCozinha(cozinhaRestaurante);
		restaurante.setEndereco(enderecoRestaurante);
		
		return repository.save(restaurante);
		
	}

	private void verificaEnderecoECozinha(Endereco endereco, Cozinha cozinha) {
		if (endereco == null && cozinha == null) {
			throw new RegraDeNegocio("Campos 'cozinha' e 'endereco' não podem ser nulos.");
		} else if (endereco == null) {
			throw new RegraDeNegocio("Campo 'endereco' não pode ser nulo.");
		} else if (cozinha == null) {
			throw new RegraDeNegocio("Campo 'cozinha' não pode ser nulo.");
		}
		
	}

	@Override
	public List<Restaurante> buscarTodos() {

		return repository.findAll();
	}
	
	@Override
	public Page<Restaurante> buscarTodos(Pageable pageable) {

		return repository.findAll(pageable);
	}

	@Override
	public Restaurante buscarRestaurantePeloId(UUID id) {
		
		return repository.getReferenceById(id);
		
	}

	@Override
	public Restaurante buscarRestaurantePeloNome(String nome) {
		//TODO lançar exceção de nao encontrado (404 ou 400?)
		
		return repository.findByNome(nome)
				.orElse(null);
	}

	@Override
	public Restaurante buscarRestaurantePeloEndereco(Endereco endereco) {
		
		return null;
	}

	@Override
	public void apagarRestaurante(Restaurante restaurante) {
		
		
	}

	@Override
	public void deletarTodos() {
		repository.deleteAll();
		
	}

	
}
