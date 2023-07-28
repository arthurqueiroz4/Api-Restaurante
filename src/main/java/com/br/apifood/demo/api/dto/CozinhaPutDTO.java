package com.br.apifood.demo.api.dto;

import javax.validation.constraints.NotNull;

import com.br.apifood.demo.domain.model.Estado;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CozinhaPutDTO {
	
	@NotNull(message = "Campo 'nome' não pode ser nulo")
	private String nome;

	@NotNull(message = "Campo 'estado' não pode ser nulo.")
	private Estado estado;

}
