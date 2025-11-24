package com.foodly.Repository;

import com.foodly.Models.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {
    Optional<Restaurante> findByUsuarioId(Integer usuarioId);
    Optional<Restaurante> findByCnpj(String cnpj);
    List<Restaurante> findByAtivo(boolean ativo);
}
