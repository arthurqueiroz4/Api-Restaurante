package com.br.apifood.demo.infrastructure;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.apifood.demo.api.dto.CozinhaPutDTO;
import com.br.apifood.demo.domain.exception.RecursoNaoEncontrado;
import com.br.apifood.demo.domain.exception.RegraDeNegocio;
import com.br.apifood.demo.domain.model.Cozinha;
import com.br.apifood.demo.domain.model.Estado;
import com.br.apifood.demo.domain.repository.CozinhaRepository;
import com.br.apifood.demo.domain.repository.EstadoRepository;
import com.br.apifood.demo.domain.service.CozinhaService;
import com.br.apifood.demo.helper.Conversor;

@Service
public class CozinhaServiceImpl implements CozinhaService {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private EstadoServiceImpl estadoService;

	@Override
	public Cozinha criarCozinha(Cozinha cozinha) {
		
		verificaNaoNuloEstadoDeCozinha(cozinha);

		Estado estado = estadoService.buscarEstadoPeloId(cozinha.getEstado().getId());

		if (existeEstadoComId(estado.getId()) && naoExisteCozinhaComNome(cozinha.getNome())) {
			associarCozinhaComEstado(cozinha, estado);
			return cozinhaRepository.save(cozinha);
		}

		throw new RegraDeNegocio("Houve um erro ao cadastrar");
	}

	private boolean existeEstadoComId(UUID idEstado) {

		Boolean resultado = estadoRepository.existsById(idEstado);
		if (resultado.equals(Boolean.FALSE)) {
			throw new RegraDeNegocio("Estado não cadastrado");
		}
		return resultado;
	}

	private boolean naoExisteCozinhaComNome(String nomeCozinha) {

		Boolean resultado = cozinhaRepository.existsByNome(nomeCozinha);
		if (resultado.equals(Boolean.TRUE)) {
			throw new RegraDeNegocio("Já existe cozinha com esse nome.");
		}
		return !resultado;
	}

	private void associarCozinhaComEstado(Cozinha cozinha, Estado estado) {

		var estadoEncontrado = estadoService.buscarEstadoPeloId(estado.getId());
		cozinha.setEstado(estadoEncontrado);
	}
	
	private void verificaNaoNuloEstadoDeCozinha(Cozinha cozinha) {
		if (Objects.isNull(cozinha.getEstado()))
			throw new RegraDeNegocio("O campo 'estado' não pode ser nulo.");
	}

	@Override
	public Page<Cozinha> buscarTodos(Pageable pageable) {

		return cozinhaRepository.findAll(pageable);
	}

	@Override
	public Cozinha buscarCozinhaPeloId(UUID cozinhaId) {

		return cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new RecursoNaoEncontrado("Recurso 'cozinha' não encontrado"));
	}

	@Override
	public List<Cozinha> buscarTodos() {

		return cozinhaRepository.findAll();

	}

	@Override
	public void apagarCozinha(UUID cozinhaId) {

		var cozinha = buscarCozinhaPeloId(cozinhaId);
		cozinhaRepository.delete(cozinha);

	}

	@Override
	public Cozinha atualizarCozinha(UUID cozinhaId, CozinhaPutDTO cozinhaputDTO) {

		
		verificaNaoNuloEstadoDeCozinha(modelMapper.map(cozinhaputDTO, Cozinha.class));
		
		var cozinhaAntiga = buscarCozinhaPeloId(cozinhaId);
		Estado estado = estadoService.buscarEstadoPeloId(cozinhaputDTO.getEstado().getId());

		BeanUtils.copyProperties(cozinhaputDTO, cozinhaAntiga, "id");
		cozinhaAntiga.setEstado(estado);

		return cozinhaRepository.save(cozinhaAntiga);

	}

	@Override
	public Cozinha atualizarParcialCozinha(UUID id, Map<String, Object> campos) {

		Cozinha cozinhaAntiga = buscarCozinhaPeloId(id);

		Cozinha cozinhaConvertida = Conversor.converterDados(campos, cozinhaAntiga);
		
		return cozinhaRepository.save(cozinhaConvertida);
	}
}
