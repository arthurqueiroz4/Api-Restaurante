package com.br.apifood.demo.infrastructure;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.apifood.demo.domain.exception.RegraDeNegocio;
import com.br.apifood.demo.domain.model.Estado;
import com.br.apifood.demo.domain.repository.EstadoRepository;
import com.br.apifood.demo.domain.service.EstadoService;

@Service
public class EstadoServiceImpl implements EstadoService{
	//TODO terminar service
	
	@Autowired
	private EstadoRepository repository;
	
	@Override
	public List<Estado> buscarTodos() {
		
		return repository.findAll();
	}

	@Override
	public Estado criarEstado(Estado estado) {
		if (repository.existsByNome(estado.getNome()))
			throw new RegraDeNegocio("Estado já cadastrado");
		return repository.save(estado);
	}

	@Override
	public Page<Estado> buscarTodos(Pageable pageable) {
		
		return repository.findAll(pageable);
	}

	@Override
	public Estado buscarEstadoPeloId(UUID id) {

		return repository.findById(id)
				.orElseThrow(() -> new RegraDeNegocio("Estado não encontrado"));
	}

	@Override
	public void apagarEstado(UUID id) {
		
		var estadoEncontrado = buscarEstadoPeloId(id);
		repository.delete(estadoEncontrado);
		
	}

	@Override
	public Estado atualizarEstado(UUID id, Estado estado) {
		Estado estadoEncontrado = buscarEstadoPeloId(id);
		
		BeanUtils.copyProperties(estado, estadoEncontrado, "id");
		
		return estadoEncontrado;
	}
	
}
