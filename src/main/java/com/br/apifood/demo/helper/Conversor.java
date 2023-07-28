package com.br.apifood.demo.helper;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import com.br.apifood.demo.domain.model.Cozinha;
import com.br.apifood.demo.domain.model.Endereco;

public class Conversor {

	@Autowired
	private static ModelMapper modelMapper;
	
	public Conversor(){
		modelMapper = new ModelMapper();
	}

	public static Cozinha converterDados(Map<String, Object> origem, Cozinha cozinhaDestino) {
		Cozinha cozinhaOrigem = modelMapper.map(origem, Cozinha.class);

		origem.forEach((chave, valor) -> {
			Field field = ReflectionUtils.findField(Cozinha.class, chave);

			if (Objects.nonNull(field)) {
				field.setAccessible(true);

				Object novoValor = ReflectionUtils.getField(field, cozinhaOrigem);

				if (Objects.nonNull(novoValor)) {
					ReflectionUtils.setField(field, cozinhaDestino, novoValor);
				}
			}
		});
		return cozinhaDestino;
	}

	public static Endereco converterDados(Map<String, Object> campos, Endereco enderecoDestino) {

		var enderecoAntigo = modelMapper.map(campos, Endereco.class);

		campos.forEach((chave, valor) -> {
			Field field = ReflectionUtils.findField(Endereco.class, chave);
			if (Objects.nonNull(field)) {
				field.setAccessible(true);

				Object novoValor = ReflectionUtils.getField(field, enderecoAntigo);

				if (Objects.nonNull(novoValor))
					ReflectionUtils.setField(field, enderecoDestino, novoValor);

			}
		});

		return enderecoDestino;
	}
}
