package com.br.apifood.demo.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.apifood.demo.domain.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, UUID>{

	Optional<Cidade> findByNome(String nome);

}
