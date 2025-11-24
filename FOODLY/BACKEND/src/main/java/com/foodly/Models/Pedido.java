package com.foodly.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private Integer clienteId;
    
    @Column(nullable = false)
    private Integer restauranteId;
    
    @Column(nullable = false)
    private Integer carrinhoId;
    
    @Column(nullable = false)
    private double valorTotal;
    
    @Column(nullable = false)
    private String status;
    
    @Column(nullable = false)
    private String enderecoEntrega;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;
    
    private LocalDateTime atualizadoEm;

    public Pedido() {
    }

    public Pedido(Integer id, Integer clienteId, Integer restauranteId, Integer carrinhoId, 
                  double valorTotal, String status, String enderecoEntrega,
                  LocalDateTime criadoEm, LocalDateTime atualizadoEm) {

        this.id = id;
        this.clienteId = clienteId;
        this.restauranteId = restauranteId;
        this.carrinhoId = carrinhoId;
        this.valorTotal = valorTotal;
        this.status = status;
        this.enderecoEntrega = enderecoEntrega;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
    }

    // Getters e Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getClienteId() { return clienteId; }
    public void setClienteId(Integer clienteId) { this.clienteId = clienteId; }

    public Integer getRestauranteId() { return restauranteId; }
    public void setRestauranteId(Integer restauranteId) { this.restauranteId = restauranteId; }

    public Integer getCarrinhoId() { return carrinhoId; }
    public void setCarrinhoId(Integer carrinhoId) { this.carrinhoId = carrinhoId; }

    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getEnderecoEntrega() { return enderecoEntrega; }
    public void setEnderecoEntrega(String enderecoEntrega) { this.enderecoEntrega = enderecoEntrega; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }

    public LocalDateTime getAtualizadoEm() { return atualizadoEm; }
    public void setAtualizadoEm(LocalDateTime atualizadoEm) { this.atualizadoEm = atualizadoEm; }
}
