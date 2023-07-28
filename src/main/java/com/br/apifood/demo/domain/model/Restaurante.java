package com.br.apifood.demo.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
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
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@NotNull(message = "Campo 'nome' não pode ser nulo.")
	@Column(length = 20, unique = true)
	private String nome;

	@NotNull(message = "Campo 'taxaFrete' não pode ser nulo.")
	private BigDecimal taxaFrete;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Endereco endereco;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Cozinha cozinha;

}
