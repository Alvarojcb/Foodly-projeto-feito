package com.foodly.Repository;

import com.foodly.Models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByClienteId(Integer clienteId);
    List<Pedido> findByRestauranteId(Integer restauranteId);
    List<Pedido> findByStatus(String status);
}
