package com.foodly.Repository;

import com.foodly.Models.CarrinhoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrinhoItemRepository extends JpaRepository<CarrinhoItem, Integer> {
    List<CarrinhoItem> findByCarrinhoId(Integer carrinhoId);
}
