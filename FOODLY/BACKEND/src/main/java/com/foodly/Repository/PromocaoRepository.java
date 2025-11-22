package com.foodly.Repository;

import com.foodly.Models.Promocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromocaoRepository extends JpaRepository<Promocao, Integer> {
    List<Promocao> findByRestauranteId(Integer restauranteId);
    List<Promocao> findByAtivo(boolean ativo);
}
