package com.foodly.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "entrega_respostas")
public class EntregaResposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private Integer entregaId;
    
    @Column(nullable = false)
    private Integer entregadorId;
    
    @Column(nullable = false)
    private String resposta;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    public EntregaResposta() {
    }

    public EntregaResposta(Integer id,
                           Integer entregaId,
                           Integer entregadorId,
                           String resposta,
                           LocalDateTime criadoEm) {
        this.id = id;
        this.entregaId = entregaId;
        this.entregadorId = entregadorId;
        this.resposta = resposta;
        this.criadoEm = criadoEm;
    }

    // Getters e Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getEntregaId() { return entregaId; }
    public void setEntregaId(Integer entregaId) { this.entregaId = entregaId; }

    public Integer getEntregadorId() { return entregadorId; }
    public void setEntregadorId(Integer entregadorId) { this.entregadorId = entregadorId; }

    public String getResposta() { return resposta; }
    public void setResposta(String resposta) { this.resposta = resposta; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}
