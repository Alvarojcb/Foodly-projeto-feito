package com.foodly.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "carrinho_itens")
public class CarrinhoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private Integer carrinhoId;
    
    @Column(nullable = false)
    private Integer produtoId;
    
    @Column(nullable = false)
    private int quantidade;
    
    @Column(nullable = false)
    private double precoUnitario;

    public CarrinhoItem() {
    }

    public CarrinhoItem(Integer id, Integer carrinhoId, Integer produtoId, int quantidade, double precoUnitario) {
        this.id = id;
        this.carrinhoId = carrinhoId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    // Getters e Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getCarrinhoId() { return carrinhoId; }
    public void setCarrinhoId(Integer carrinhoId) { this.carrinhoId = carrinhoId; }

    public Integer getProdutoId() { return produtoId; }
    public void setProdutoId(Integer produtoId) { this.produtoId = produtoId; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(double precoUnitario) { this.precoUnitario = precoUnitario; }
}
