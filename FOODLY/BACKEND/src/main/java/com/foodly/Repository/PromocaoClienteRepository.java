package com.foodly.Repository;

import com.foodly.Models.PromocaoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromocaoClienteRepository extends JpaRepository<PromocaoCliente, Integer> {
    List<PromocaoCliente> findByClienteId(Integer clienteId);
    List<PromocaoCliente> findByPromocaoId(Integer promocaoId);
}
