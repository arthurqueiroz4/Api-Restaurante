package com.br.apifood.demo.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.apifood.demo.domain.model.Cozinha;

public interface CozinhaRepository extends JpaRepository<Cozinha, UUID>{

	Optional<Cozinha> findByNome(String nome);
	
	Boolean existsByNome(String nome);
	
	
}
