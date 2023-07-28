package com.br.apifood.demo.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.apifood.demo.domain.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, UUID>{
	
	boolean existsByNome(String nome);

}
