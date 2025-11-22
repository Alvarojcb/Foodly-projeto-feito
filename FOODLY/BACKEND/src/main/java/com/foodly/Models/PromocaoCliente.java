package com.foodly.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "promocoes_cliente")
public class PromocaoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private Integer promocaoId;
    
    @Column(nullable = false)
    private Integer clienteId;
    
    @Column(nullable = false)
    private boolean resgatada;
    
    private LocalDateTime resgatadaEm;

    public PromocaoCliente() {
        this.resgatada = false;
    }

    public PromocaoCliente(Integer id,
                           Integer promocaoId,
                           Integer clienteId,
                           boolean resgatada,
                           LocalDateTime resgatadaEm) {
        this.id = id;
        this.promocaoId = promocaoId;
        this.clienteId = clienteId;
        this.resgatada = resgatada;
        this.resgatadaEm = resgatadaEm;
    }

    // Getters e Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getPromocaoId() { return promocaoId; }
    public void setPromocaoId(Integer promocaoId) { this.promocaoId = promocaoId; }

    public Integer getClienteId() { return clienteId; }
    public void setClienteId(Integer clienteId) { this.clienteId = clienteId; }

    public boolean isResgatada() { return resgatada; }
    public void setResgatada(boolean resgatada) { this.resgatada = resgatada; }

    public LocalDateTime getResgatadaEm() { return resgatadaEm; }
    public void setResgatadaEm(LocalDateTime resgatadaEm) { this.resgatadaEm = resgatadaEm; }
}
