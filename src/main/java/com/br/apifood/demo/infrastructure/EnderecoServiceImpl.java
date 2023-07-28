package com.br.apifood.demo.infrastructure;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.apifood.demo.domain.exception.RecursoNaoEncontrado;
import com.br.apifood.demo.domain.exception.RegraDeNegocio;
import com.br.apifood.demo.domain.model.Endereco;
import com.br.apifood.demo.domain.repository.EnderecoRepository;
import com.br.apifood.demo.domain.service.CidadeService;
import com.br.apifood.demo.domain.service.EnderecoService;
import com.br.apifood.demo.helper.Conversor;

@Service
public class EnderecoServiceImpl implements EnderecoService{
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private CidadeService cidadeService;

	@Override
	public Page<Endereco> buscarTodos(Pageable pageable) {
		
		return enderecoRepository.findAll(pageable);
	}

	@Override
	public List<Endereco> buscarTodos() {
		
		return enderecoRepository.findAll();
	}

	@Override
	public Endereco criarEndereco(Endereco endereco) {
		
		if (Objects.isNull(endereco.getCidade()))
			throw new RegraDeNegocio("Campo 'cidade' não pode ser nulo.");
		
		if (enderecoRepository.existsByRuaAndNumero(endereco.getRua(), endereco.getNumero()))
			throw new RegraDeNegocio("Há um recurso cadastrado nesse endereço.");
		
		var cidade = cidadeService.buscarCidadePeloId(endereco.getCidade().getId());
		
		endereco.setCidade(cidade);

		
		return enderecoRepository.save(endereco);
	}
	
	public Endereco buscarEnderecoPeloId(UUID id) {
		return enderecoRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontrado("Recurso 'endereco' não encontrado."));
	}
	
	public Endereco atualizarEndereco(UUID id, Endereco endereco) {
		var enderecoEncontrado = buscarEnderecoPeloId(id);
		var cidadeEndereco = cidadeService.buscarCidadePeloId(endereco.getCidade().getId());
		endereco.setCidade(cidadeEndereco);
		
		BeanUtils.copyProperties(endereco, enderecoEncontrado, "id");
		return enderecoRepository.save(enderecoEncontrado);
	}
	
	public Endereco atualizarParcialEndereco(UUID id, Map<String, Object> campos) {
		var enderecoAntigo = buscarEnderecoPeloId(id);
		System.out.println(enderecoAntigo);
		
		var enderecoNovo = Conversor.converterDados(campos, enderecoAntigo);
		
		System.out.println(enderecoNovo);
		
		return enderecoRepository.save(enderecoNovo);
		
	}

	
	

}
