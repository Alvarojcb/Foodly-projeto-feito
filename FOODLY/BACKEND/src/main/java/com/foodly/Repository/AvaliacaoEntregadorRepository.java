package com.foodly.Repository;

import com.foodly.Models.AvaliacaoEntregador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoEntregadorRepository extends JpaRepository<AvaliacaoEntregador, Integer> {
    List<AvaliacaoEntregador> findByEntregadorId(Integer entregadorId);
    List<AvaliacaoEntregador> findByClienteId(Integer clienteId);
}
