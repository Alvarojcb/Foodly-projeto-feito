package com.foodly.Repository;

import com.foodly.Models.SuporteAtendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuporteAtendimentoRepository extends JpaRepository<SuporteAtendimento, Integer> {
    List<SuporteAtendimento> findByUsuarioId(Integer usuarioId);
    List<SuporteAtendimento> findByStatus(String status);
}
