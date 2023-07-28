package com.br.apifood.demo.infrastructure;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.apifood.demo.domain.exception.RecursoNaoEncontrado;
import com.br.apifood.demo.domain.exception.RegraDeNegocio;
import com.br.apifood.demo.domain.model.Cidade;
import com.br.apifood.demo.domain.repository.CidadeRepository;
import com.br.apifood.demo.domain.service.CidadeService;

@Service
public class CidadeServiceImpl implements CidadeService{
	//TODO terminar service
	
	@Autowired
	private CidadeRepository repository;

	@Override
	public Cidade criarCidade(Cidade cidade) {
		
		verificarNaoNuloEstado(cidade);
		
		return repository.save(cidade);
	}
	
	private void verificarNaoNuloEstado(Cidade cidade) {
		if (Objects.isNull(cidade.getEstado()))
			throw new RegraDeNegocio("Campo 'estado' não pode ser nulo.");
	}

	@Override
	public List<Cidade> buscarTodos() {
		
		return repository.findAll();
	}

	@Override
	public Page<Cidade> buscarTodos(Pageable pageable) {
		
		return repository.findAll(pageable);
	}

	@Override
	public Cidade buscarCidadePeloId(UUID id) {
		
		return repository.findById(id).orElseThrow(() -> new RecursoNaoEncontrado("Recurso 'cidade' não encontrado."));
	}

	@Override
	public Cidade buscarCidadePeloNome(String nome) {
		
		return repository.findByNome(nome).orElseThrow(() -> new RecursoNaoEncontrado("Recurso 'cidade' não encontrado."));
		
	}

	@Override
	public void apagarCidade(UUID id) {
		buscarCidadePeloId(id);
		repository.deleteById(id);
		
	}
}
