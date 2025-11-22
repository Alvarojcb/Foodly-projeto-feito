package com.foodly.Repository;

import com.foodly.Models.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer> {
    Optional<Carrinho> findByClienteId(Integer clienteId);
    Optional<Carrinho> findByClienteIdAndStatus(Integer clienteId, String status);
}
