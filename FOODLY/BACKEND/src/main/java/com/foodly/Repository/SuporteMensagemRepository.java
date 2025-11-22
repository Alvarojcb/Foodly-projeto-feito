package com.foodly.Repository;

import com.foodly.Models.SuporteMensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuporteMensagemRepository extends JpaRepository<SuporteMensagem, Integer> {
    List<SuporteMensagem> findByAtendimentoId(Integer atendimentoId);
}
