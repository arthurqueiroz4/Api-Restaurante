package com.br.apifood.demo.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.apifood.demo.domain.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, UUID>{

	Optional<Restaurante> findByNome(String nome);
}
