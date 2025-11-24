package com.foodly.Repository;

import com.foodly.Models.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Integer> {
    Optional<Entrega> findByPedidoId(Integer pedidoId);
    List<Entrega> findByEntregadorId(Integer entregadorId);
    List<Entrega> findByStatus(String status);
}
