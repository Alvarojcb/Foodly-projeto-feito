package com.foodly.Repository;

import com.foodly.Models.AvaliacaoRestaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRestauranteRepository extends JpaRepository<AvaliacaoRestaurante, Integer> {
    List<AvaliacaoRestaurante> findByRestauranteId(Integer restauranteId);
    List<AvaliacaoRestaurante> findByClienteId(Integer clienteId);
}
