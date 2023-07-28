package com.br.apifood.demo.domain.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@NotNull(message = "Campo 'rua' não pode ser nulo.")
	private String rua;
	
	@NotNull(message = "Campo 'numero' não pode ser nulo.")
	private String numero;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Cidade cidade;


}
