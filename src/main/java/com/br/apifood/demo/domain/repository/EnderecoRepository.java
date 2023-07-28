package com.br.apifood.demo.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.apifood.demo.domain.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID>{
	
	boolean existsByRuaAndNumero(String rua, String numero);

}
