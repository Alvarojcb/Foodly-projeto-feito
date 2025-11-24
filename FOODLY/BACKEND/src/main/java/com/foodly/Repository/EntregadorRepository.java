package com.foodly.Repository;

import com.foodly.Models.Entregador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntregadorRepository extends JpaRepository<Entregador, Integer> {
    Optional<Entregador> findByUsuarioId(Integer usuarioId);
    Optional<Entregador> findByDocumento(String documento);
}
