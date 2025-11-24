package com.foodly.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "entregas")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private Integer pedidoId;
    
    private Integer entregadorId;
    
    @Column(nullable = false)
    private String status;
    
    @Column(columnDefinition = "TEXT")
    private String rotaSugerida;
    
    private Integer tempoEstimadoMin;
    
    private Double distanciaKm;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;
    
    private LocalDateTime atualizadoEm;

    public Entrega() {
    }

    public Entrega(Integer id,
                   Integer pedidoId,
                   Integer entregadorId,
                   String status,
                   String rotaSugerida,
                   Integer tempoEstimadoMin,
                   Double distanciaKm,
                   LocalDateTime criadoEm,
                   LocalDateTime atualizadoEm) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.entregadorId = entregadorId;
        this.status = status;
        this.rotaSugerida = rotaSugerida;
        this.tempoEstimadoMin = tempoEstimadoMin;
        this.distanciaKm = distanciaKm;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
    }

    // Getters e Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getPedidoId() { return pedidoId; }
    public void setPedidoId(Integer pedidoId) { this.pedidoId = pedidoId; }

    public Integer getEntregadorId() { return entregadorId; }
    public void setEntregadorId(Integer entregadorId) { this.entregadorId = entregadorId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRotaSugerida() { return rotaSugerida; }
    public void setRotaSugerida(String rotaSugerida) { this.rotaSugerida = rotaSugerida; }

    public Integer getTempoEstimadoMin() { return tempoEstimadoMin; }
    public void setTempoEstimadoMin(Integer tempoEstimadoMin) { this.tempoEstimadoMin = tempoEstimadoMin; }

    public Double getDistanciaKm() { return distanciaKm; }
    public void setDistanciaKm(Double distanciaKm) { this.distanciaKm = distanciaKm; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }

    public LocalDateTime getAtualizadoEm() { return atualizadoEm; }
    public void setAtualizadoEm(LocalDateTime atualizadoEm) { this.atualizadoEm = atualizadoEm; }
}
